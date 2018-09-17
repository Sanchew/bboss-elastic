package org.frameworkset.elasticsearch.client;

import org.apache.http.client.ResponseHandler;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.entity.*;
import org.frameworkset.elasticsearch.entity.suggest.CompleteRestResponse;
import org.frameworkset.elasticsearch.entity.suggest.PhraseRestResponse;
import org.frameworkset.elasticsearch.entity.suggest.TermRestResponse;
import org.frameworkset.elasticsearch.handler.ESAggBucketHandle;
import org.frameworkset.elasticsearch.scroll.ScrollHandler;
import org.frameworkset.elasticsearch.serial.ESTypeReferences;
import org.frameworkset.util.annotations.ThreadSafe;

import java.util.List;
import java.util.Map;

/**
 * @see https://my.oschina.net/bboss/blog/1556866
 */
@ThreadSafe
public interface ClientInterface {
	public final String HTTP_GET = "get";
	public final String HTTP_POST = "post";
	public final String HTTP_DELETE = "delete";
	public final String HTTP_PUT = "put";
	public final String HTTP_HEAD = "head";
	public final String VERSION_TYPE_INTERNAL = "internal";
	public final String VERSION_TYPE_EXTERNAL = "external";
	public final String VERSION_TYPE_EXTERNAL_GT = "external_gt";
	public final String VERSION_TYPE_EXTERNAL_GTE = "external_gte";

	/**
	 * 获取动态索引表名称
	 * @param indexName
	 * @return
	 */
	public String getDynamicIndexName(String indexName);
	public CompleteRestResponse complateSuggest(String path, String entity) throws ElasticSearchException;

	public CompleteRestResponse complateSuggest(String path, String templateName,Map params) throws ElasticSearchException;

	public CompleteRestResponse complateSuggest(String path, String templateName,Object params) throws ElasticSearchException;
	/**
	 * 创建索引文档，根据elasticsearch.xml中指定的日期时间格式，生成对应时间段的索引表名称
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @return
	 * @throws ElasticSearchException
	 */
	public String addDocumentWithParentId(String indexName, String indexType, Object bean,Object parentId) throws ElasticSearchException;

	public String addDocumentWithParentId(String indexName, String indexType, Object bean,Object parentId,String refreshOption) throws ElasticSearchException;

	public String addDateDocumentWithParentId(String indexName, String indexType, Object bean,Object parentId) throws ElasticSearchException;

	public String addDateDocumentWithParentId(String indexName, String indexType, Object bean,Object parentId,String refreshOption) throws ElasticSearchException;
	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param path test/_doc/1
	 *             test/_doc/1/_update
	 *
	 *
	 * @param entity
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateByPath(String path,String entity) throws ElasticSearchException;

	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param path test/_doc/1
	 *             test/_doc/1/_update
	 *
	 *
	 * @param templateName
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateByPath(String path,String templateName,Map params) throws ElasticSearchException;

	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param path test/_doc/1
	 *             test/_doc/1/_update
	 *
	 *
	 * @param templateName
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateByPath(String path,String templateName,Object params) throws ElasticSearchException;

	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param type
	 * @param id
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String type,Object id,Map params) throws ElasticSearchException;

	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param type
	 * @param id
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String type,Object id,Object params) throws ElasticSearchException;


	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param type
	 * @param id
	 * @param params
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String type,Object id,Map params,String refreshOption) throws ElasticSearchException;

	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param type
	 * @param id
	 * @param params
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String type,Object id,Object params,String refreshOption) throws ElasticSearchException;
	/**
	 * 删除索引文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete.html
	 * @param path /twitter/_doc/1
	 *             /twitter/_doc/1?routing=kimchy
	 *             /twitter/_doc/1?timeout=5m
	 * @return
	 */
	public String deleteByPath(String path) throws ElasticSearchException;

	/**
	 *
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete-by-query.html
	 * @param path twitter/_delete_by_query?routing=1
	 *             twitter/_doc/_delete_by_query?conflicts=proceed
	 *             twitter/_delete_by_query
	 *             twitter/_delete_by_query?scroll_size=5000
	 *
	 * @param entity
	 * @return
	 * @throws ElasticSearchException
	 */
	public String deleteByQuery(String path,String entity) throws ElasticSearchException;
	/**
	 *
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete-by-query.html
	 * @param path twitter/_delete_by_query?routing=1
	 *             twitter/_doc/_delete_by_query?conflicts=proceed
	 *             twitter/_delete_by_query
	 *             twitter/_delete_by_query?scroll_size=5000
	 *
	 * @param templateName
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String deleteByQuery(String path,String templateName,Map params) throws ElasticSearchException;
	/**
	 *
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete-by-query.html
	 * @param path twitter/_delete_by_query?routing=1
	 *             twitter/_doc/_delete_by_query?conflicts=proceed
	 *             twitter/_delete_by_query
	 *             twitter/_delete_by_query?scroll_size=5000
	 *
	 * @param templateName
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String deleteByQuery(String path,String templateName,Object params) throws ElasticSearchException;

	public String deleteDocuments(String indexName, String indexType, String... ids) throws ElasticSearchException;
	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.

	 * @param ids
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String deleteDocumentsWithrefreshOption(String indexName, String indexType, String refreshOption,String... ids) throws ElasticSearchException;
	public <T> T getIndexMapping(String index,ResponseHandler<T> responseHandler) throws ElasticSearchException;
	public <T> T getIndexMapping(String index,boolean pretty,ResponseHandler<T> responseHandler) throws ElasticSearchException;

	/**
	 * 判断索引是否存在
	 * @param indiceName
	 * @return
	 * @throws ElasticSearchException
	 */
	public boolean existIndice(String indiceName) throws ElasticSearchException;

	/**
	 * 判断所引类型是否存在
	 * @param indiceName
	 * @param type
	 * @return
	 * @throws ElasticSearchException
	 */
	public boolean existIndiceType(String indiceName,String type) throws ElasticSearchException;
	/**
	 * 获取索引表
	 * @param index
	 * @return
	 * @throws ElasticSearchException
	 */
	public List<IndexField> getIndexMappingFields(String index,String indexType) throws ElasticSearchException;

	/***************************读取模板文件添加或者修改文档开始************************************/
	/**
	 * 批量创建索引
	 * @param indexName
	 * @param indexType
	 * @param addTemplate
	 * @param beans
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocuments(String indexName, String indexType,String addTemplate, List<?> beans,String refreshOption) throws ElasticSearchException;
	public abstract String addDocuments(String indexName, String indexType,String addTemplate, List<?> beans) throws ElasticSearchException;

	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param addTemplate
	 * @param bean
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocument(String indexName, String indexType,String addTemplate, Object bean) throws ElasticSearchException;

	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param addTemplate
	 * @param bean
	 * @param refreshOption
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocument(String indexName, String indexType,String addTemplate, Object bean,String refreshOption) throws ElasticSearchException;

	public abstract String updateDocuments(String indexName, String indexType,String updateTemplate, List<?> beans) throws ElasticSearchException;

	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param updateTemplate
	 * @param beans
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String updateDocuments(String indexName, String indexType,String updateTemplate, List<?> beans,String refreshOption) throws ElasticSearchException;

	/***************************读取模板文件添加或者修改文档结束************************************/

	/***************************添加或者修改文档开始************************************/
	/**
	 * 批量创建索引
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocuments(String indexName, String indexType, List<?> beans,String refreshOption) throws ElasticSearchException;
	public abstract String addDocuments(String indexName, String indexType,  List<?> beans) throws ElasticSearchException;
	public abstract String addDocumentsWithDocIdKey(String indexName, String indexType,  List<?> beans, String docIdKey) throws ElasticSearchException;
	public abstract String addDocumentsWithDocIdKey(String indexName, String indexType,  List<?> beans, String docIdKey, String refreshOption) throws ElasticSearchException;


	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocument(String indexName, String indexType, Object bean) throws ElasticSearchException;
	public abstract String addDocumentWithDocIdKey(String indexName, String indexType, Object bean, String docIdKey) throws ElasticSearchException;
	public abstract String addDocumentWithDocIdKey(String indexName, String indexType, Object bean, String docIdKey, String refreshOption) throws ElasticSearchException;

	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocument(String indexName, String indexType, Object bean,String refreshOption) throws ElasticSearchException;

	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param docId
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocumentWithId(String indexName, String indexType, Object bean,Object docId) throws ElasticSearchException;

	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param docId
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocumentWithId(String indexName, String indexType, Object bean,Object docId,Object parentId) throws ElasticSearchException;

	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param docId
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocument(String indexName, String indexType, Object bean,Object docId,Object parentId,String refreshOption) throws ElasticSearchException;

	/**
	 * 创建或者更新索引文档
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param docId
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDocument(String indexName, String indexType, Object bean,Object docId,String refreshOption) throws ElasticSearchException;

	public abstract String updateDocuments(String indexName, String indexType, List<?> beans) throws ElasticSearchException;
	public abstract String updateDocumentsWithIdKey(String indexName, String indexType, List<Map> beans,String docIdKey) throws ElasticSearchException;
	public abstract String updateDocumentsWithIdKey(String indexName, String indexType, List<Map> beans,String docIdKey,String parentIdKey) throws ElasticSearchException;
	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String updateDocuments(String indexName, String indexType, List<?> beans,String refreshOption) throws ElasticSearchException;
	public abstract String updateDocuments(String indexName, String indexType, List<Map> beans,String docIdKey,String refreshOption) throws ElasticSearchException;
	public abstract String updateDocuments(String indexName, String indexType, List<Map> beans,String docIdKey,String parentIdKey,String refreshOption) throws ElasticSearchException;

	/***************************添加或者修改文档结束************************************/
	/**
	 * 获取json格式文档
	 * @param indexName
	 * @param indexType
	 * @param documentId
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String getDocument(String indexName, String indexType,String documentId) throws ElasticSearchException;
	/**
	 * 获取json格式文档，通过options设置获取文档的参数
	 * @param indexName
	 * @param indexType
	 * @param documentId
	 * @param options
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String getDocument(String indexName, String indexType,String documentId,Map<String,Object> options) throws ElasticSearchException;

	/**
	 * 获取json格式文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html
	 * @param path twitter/_doc/0
	 *             twitter/_doc/0?_source=false
	 *             twitter/_doc/0?_source_include=*.id&_source_exclude=entities
	 *             twitter/_doc/0?_source=*.id,retweeted
	 *             twitter/_doc/1?stored_fields=tags,counter
	 *             twitter/_doc/2?routing=user1&stored_fields=tags,counter
	 *             GET twitter/_doc/2?routing=user1
	 *
	 * @param path
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String getDocumentByPath(String path) throws ElasticSearchException;


	/**
	 * 获取json格式文档,不带索引元数据
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html
	 * @param path twitter/_doc/1/_source 其中1为文档id
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String getDocumentSource(String path) throws ElasticSearchException;

	/**
	 * 获取文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html
	 * @param path twitter/_doc/0
	 *             twitter/_doc/0?_source=false
	 *             twitter/_doc/0?_source_include=*.id&_source_exclude=entities
	 *             twitter/_doc/0?_source=*.id,retweeted
	 *             twitter/_doc/1?stored_fields=tags,counter
	 *             twitter/_doc/2?routing=user1&stored_fields=tags,counter
	 *             GET twitter/_doc/2?routing=user1
	 *
	 * @param path
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T getDocumentByPath(String path,Class<T> beanType) throws ElasticSearchException;


	/**
	 * 获取文档Source对象，不带索引元数据
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html
	 * @param path twitter/_doc/1/_source 其中1为文档id
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T getDocumentSource(String path,Class<T> beanType) throws ElasticSearchException;

	/**
	 * 获取文档,返回类型可以继承ESBaseData(这样方法自动将索引的元数据信息设置到T对象中)和ESId（方法自动将索引文档id设置到对象中）
	 * @param indexName
	 * @param indexType
	 * @param documentId
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T getDocument(String indexName, String indexType,String documentId,Class<T> beanType) throws ElasticSearchException;

	/**
	 * 获取文档，通过options设置获取文档的参数，返回类型可以继承ESBaseData(这样方法自动将索引的元数据信息设置到T对象中)和ESId（方法自动将索引文档id设置到对象中）
	 * @param indexName
	 * @param indexType
	 * @param documentId
	 * @param options
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T getDocument(String indexName, String indexType,String documentId,Map<String,Object> options,Class<T> beanType) throws ElasticSearchException;



	/**
	 * 获取文档MapSearchHit对象，封装了索引文档的所有属性数据
	 * @param indexName
	 * @param indexType
	 * @param documentId
	 * @param options
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract MapSearchHit getDocumentHit(String indexName, String indexType,String documentId,Map<String,Object> options) throws ElasticSearchException;

	/**
	 * 获取文档MapSearchHit对象，封装了索引文档的所有属性数据
	 * @param indexName
	 * @param indexType
	 * @param documentId
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract MapSearchHit getDocumentHit(String indexName, String indexType,String documentId) throws ElasticSearchException;

	/**************************************创建或者修改文档开始**************************************************************/
	/**
	 * 创建索引文档，根据elasticsearch.xml中指定的日期时间格式，生成对应时间段的索引表名称
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocument(String indexName, String indexType, Object bean) throws ElasticSearchException;

	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocument(String indexName, String indexType, Object bean,String refreshOption) throws ElasticSearchException;

	/**
	 * 创建索引文档，根据elasticsearch.xml中指定的日期时间格式，生成对应时间段的索引表名称
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocumentWithId(String indexName, String indexType, Object bean,Object docId) throws ElasticSearchException;

	/**
	 * 创建索引文档，根据elasticsearch.xml中指定的日期时间格式，生成对应时间段的索引表名称
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocumentWithId(String indexName, String indexType, Object bean,Object docId,Object parentId) throws ElasticSearchException;

	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocument(String indexName, String indexType, Object bean,Object docId,String refreshOption) throws ElasticSearchException;

	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param bean
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocument(String indexName, String indexType, Object bean,Object docId,Object parentId,String refreshOption) throws ElasticSearchException;

	/**
	 * 批量创建索引,根据时间格式建立新的索引表
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocuments(String indexName, String indexType, List<?> beans) throws ElasticSearchException;



	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocuments(String indexName, String indexType, List<?> beans,String refreshOption) throws ElasticSearchException;

	/**
	 * 批量创建索引,根据时间格式建立新的索引表
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @param docIdKey map中作为文档id的Key
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocuments(String indexName, String indexType, List<Map> beans,String docIdKey,String refreshOption) throws ElasticSearchException;
	/**
	 * 批量创建索引,根据时间格式建立新的索引表
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @param docIdKey map中作为文档id的Key
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocumentsWithIdKey(String indexName, String indexType, List<Map> beans,String docIdKey) throws ElasticSearchException;
	public abstract String addDocuments(String indexName, String indexType, List<Map> beans,String docIdKey,String refreshOption) throws ElasticSearchException;
	public abstract String addDocumentsWithIdKey(String indexName, String indexType,  List<Map> beans,String docIdKey) throws ElasticSearchException;


	/**********************/
	/**
	 * 批量创建索引,根据时间格式建立新的索引表
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @param docIdKey map中作为文档id的Key
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocuments(String indexName, String indexType, List<Map> beans,String docIdKey,String parentIdKey,String refreshOption) throws ElasticSearchException;
	/**
	 * 批量创建索引,根据时间格式建立新的索引表
	 * @param indexName
	 * @param indexType
	 * @param beans
	 * @param docIdKey map中作为文档id的Key
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocumentsWithIdKey(String indexName, String indexType, List<Map> beans,String docIdKey,String parentIdKey) throws ElasticSearchException;
	public abstract String addDocuments(String indexName, String indexType, List<Map> beans,String docIdKey,String parentIdKey,String refreshOption) throws ElasticSearchException;
	public abstract String addDocumentsWithIdKey(String indexName, String indexType,  List<Map> beans,String docIdKey,String parentIdKey) throws ElasticSearchException;


	/**************************************创建或者修改文档结束**************************************************************/


	/**************************************基于query dsl配置文件脚本创建或者修改文档开始**************************************************************/
	/**
	 * 创建索引文档，根据elasticsearch.xml中指定的日期时间格式，生成对应时间段的索引表名称
	 * @param indexName
	 * @param indexType
	 * @param addTemplate
	 * @param bean
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocument(String indexName, String indexType,String addTemplate, Object bean) throws ElasticSearchException;

	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param addTemplate
	 * @param bean
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocument(String indexName, String indexType,String addTemplate, Object bean,String refreshOption) throws ElasticSearchException;
	/**
	 * 批量创建索引,根据时间格式建立新的索引表
	 * @param indexName
	 * @param indexType
	 * @param addTemplate
	 * @param beans
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocuments(String indexName, String indexType,String addTemplate, List<?> beans) throws ElasticSearchException;

	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param addTemplate
	 * @param beans
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String addDateDocuments(String indexName, String indexType,String addTemplate, List<?> beans,String refreshOption) throws ElasticSearchException;

	/**************************************基于query dsl配置文件脚本创建或者修改文档结束**************************************************************/

	public abstract String deleteDocument(String indexName, String indexType, String id) throws ElasticSearchException;

	/**
	 *
	 * @param indexName
	 * @param indexType
	 * @param id
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String deleteDocument(String indexName, String indexType, String id,String refreshOption) throws ElasticSearchException;
	 
	 

	/**
	 * @param path
	 * @param entity
	 * @return
	 */
	public abstract Object executeRequest(String path, String entity) throws ElasticSearchException;


	/**
	 * @param path
	 * @return
	 */
	public abstract Object executeRequest(String path) throws ElasticSearchException;

	/**
	 * 发送es restful请求，获取String类型json报文
	 * @param path
	 * @param action
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String executeHttp(String path, String action) throws ElasticSearchException;

	/**
	 * 发送es restful请求，获取返回值，返回值类型由ResponseHandler决定
	 * @param path
	 * @param action
	 * @param responseHandler
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T executeHttp(String path, String action,ResponseHandler<T> responseHandler) throws ElasticSearchException ;

	public abstract <T> T discover(String path, String action,ResponseHandler<T> responseHandler) throws ElasticSearchException ;

	/**
	 * 发送es restful请求，获取返回值，返回值类型由ResponseHandler决定
	 * @param path
	 * @param entity
	 * @param action
	 * @param responseHandler
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T  executeHttp(String path, String entity,String action,ResponseHandler<T> responseHandler) throws ElasticSearchException ;

		/**
		 * 发送es restful请求，获取String类型json报文
		 * @param path
		 * @param entity 请求报文
		 * @param action get,post,put,delete
		 * @return
		 * @throws ElasticSearchException
		 */
	public abstract String executeHttp(String path, String entity, String action) throws ElasticSearchException;

	/**
	 * 发送es restful请求，获取返回值，返回值类型由ResponseHandler决定
	 * @param path
	 * @param entity
	 * @param action
	 * @param responseHandler
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T  executeHttp(String path, String entity,String action,Map params,ResponseHandler<T> responseHandler) throws ElasticSearchException ;

	/**
	 * 发送es restful请求，获取String类型json报文
	 * @param path
	 * @param entity 请求报文
	 * @param action get,post,put,delete
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String executeHttp(String path, String entity,Map params, String action) throws ElasticSearchException;
	/**
	 * 发送es restful请求，获取返回值，返回值类型由ResponseHandler决定
	 * @param path
	 * @param entity
	 * @param action
	 * @param responseHandler
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> T  executeHttp(String path, String entity,String action,Object bean,ResponseHandler<T> responseHandler) throws ElasticSearchException ;

	/**
	 * 发送es restful请求，获取String类型json报文
	 * @param path
	 * @param entity 请求报文
	 * @param action get,post,put,delete
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String executeHttp(String path, String entity,Object bean, String action) throws ElasticSearchException;


	public abstract String getIndexMapping(String index) throws ElasticSearchException;

	public abstract String getIndexMapping(String index,boolean pretty) throws ElasticSearchException;

	public abstract String executeRequest(String path, String templateName, Map params) throws ElasticSearchException;


	public abstract String executeRequest(String path, String templateName, Object params) throws ElasticSearchException;


	public abstract <T> T executeRequest(String path, String entity, ResponseHandler<T> responseHandler) throws ElasticSearchException;

	public abstract <T> T executeRequest(String path, String templateName, Map params, ResponseHandler<T> responseHandler) throws ElasticSearchException;


	public abstract <T> T executeRequest(String path, String templateName, Object params, ResponseHandler<T> responseHandler) throws ElasticSearchException;

	public abstract MapRestResponse search(String path, String templateName, Map params) throws ElasticSearchException;


	public abstract MapRestResponse search(String path, String templateName, Object params) throws ElasticSearchException;

	/**
	 * @param path
	 * @param entity
	 * @return
	 */
	public abstract MapRestResponse search(String path, String entity) throws ElasticSearchException;
	/**
	 * @param path
	 * @param entity
	 * @return
	 */
	public abstract TermRestResponse termSuggest(String path, String entity) throws ElasticSearchException;

	/**
	 * @param path
	 * @param entity
	 * @return
	 */
	public abstract PhraseRestResponse phraseSuggest(String path, String entity) throws ElasticSearchException;

	/**
	 * @param path
	 * @return
	 */
	public abstract TermRestResponse termSuggest(String path, String templateName, Object params) throws ElasticSearchException;

	/**
	 * @param path
	 * @return
	 */
	public abstract PhraseRestResponse phraseSuggest(String path, String templateName, Object params) throws ElasticSearchException;

	/**
	 * @param path
	 * @return
	 */
	public abstract TermRestResponse termSuggest(String path, String templateName, Map params) throws ElasticSearchException;

	/**
	 * @param path
	 * @return
	 */
	public abstract PhraseRestResponse phraseSuggest(String path, String templateName, Map params) throws ElasticSearchException;

	/**
	 * @param path
	 * @param entity
	 * @return
	 */
	public abstract CompleteRestResponse complateSuggest(String path, String entity,Class<?> type) throws ElasticSearchException;

	/**
	 * @param path
	 * @return
	 */
	public abstract CompleteRestResponse complateSuggest(String path, String templateName, Object params,Class<?> type) throws ElasticSearchException;

	/**
	 * @param path
	 * @return
	 */
	public abstract CompleteRestResponse complateSuggest(String path, String templateName, Map params,Class<?> type) throws ElasticSearchException;

	public abstract Map<String, Object> searchMap(String path, String templateName, Map params) throws ElasticSearchException;


	public abstract Map<String, Object> searchMap(String path, String templateName, Object params) throws ElasticSearchException;

	/**
	 * @param path
	 * @param entity
	 * @return
	 */
	public abstract Map<String, Object> searchMap(String path, String entity) throws ElasticSearchException;


	/**
	 * 获取索引定义
	 *
	 * @param index
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String getIndice(String index) throws ElasticSearchException;

	/**
	 * 删除索引定义
	 *
	 * @param index
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String dropIndice(String index) throws ElasticSearchException;

	/**
	 * 更新索引定义
	 *
	 * @param indexMapping
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String updateIndiceMapping(String action, String indexMapping) throws ElasticSearchException;

	/**
	 * 创建索引定义
	 * curl -XPUT 'localhost:9200/test?pretty' -H 'Content-Type: application/json' -d'
	 * {
	 * "settings" : {
	 * "number_of_shards" : 1
	 * },
	 * "mappings" : {
	 * "type1" : {
	 * "properties" : {
	 * "field1" : { "type" : "text" }
	 * }
	 * }
	 * }
	 * }
	 * @param indexName 索引表名称
	 * @param indexMapping 索引mapping dsl名称
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String createIndiceMapping(String indexName, String indexMapping) throws ElasticSearchException;


	/**
	 * 更新索引定义
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String updateIndiceMapping(String action, String templateName, Object parameter) throws ElasticSearchException;

	/**
	 * 创建索引定义
	 * curl -XPUT 'localhost:9200/test?pretty' -H 'Content-Type: application/json' -d'
	 * {
	 * "settings" : {
	 * "number_of_shards" : 1
	 * },
	 * "mappings" : {
	 * "type1" : {
	 * "properties" : {
	 * "field1" : { "type" : "text" }
	 * }
	 * }
	 * }
	 * }
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String createIndiceMapping(String indexName, String templateName, Object parameter) throws ElasticSearchException;

	/**
	 * 更新索引定义
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String updateIndiceMapping(String action, String templateName, Map parameter) throws ElasticSearchException;

	/**
	 * 创建索引定义
	 * curl -XPUT 'localhost:9200/test?pretty' -H 'Content-Type: application/json' -d'
	 * {
	 * "settings" : {
	 * "number_of_shards" : 1
	 * },
	 * "mappings" : {
	 * "type1" : {
	 * "properties" : {
	 * "field1" : { "type" : "text" }
	 * }
	 * }
	 * }
	 * }
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String createIndiceMapping(String indexName, String templateName, Map parameter) throws ElasticSearchException;

	public abstract List<ESIndice> getIndexes() throws ElasticSearchException;

	public abstract String refreshIndexInterval(String indexName, int interval) throws ElasticSearchException;

	public abstract String refreshIndexInterval(String indexName, String indexType, int interval) throws ElasticSearchException;

	public abstract String refreshIndexInterval(int interval, boolean preserveExisting) throws ElasticSearchException;

	public abstract String refreshIndexInterval(int interval) throws ElasticSearchException;

	public abstract RestResponse search(String path, String templateName, Map params, Class<?> type) throws ElasticSearchException;


	public abstract RestResponse search(String path, String templateName, Object params, Class<?> type) throws ElasticSearchException;

	public abstract RestResponse search(String path, String entity, Class<?> type) throws ElasticSearchException;


	public abstract RestResponse search(String path, String templateName, Map params, ESTypeReferences type) throws ElasticSearchException;

	public abstract RestResponse search(String path, String templateName, Object params, ESTypeReferences type) throws ElasticSearchException;

	public abstract RestResponse search(String path, String entity, ESTypeReferences type) throws ElasticSearchException;

	public abstract <T> ESDatas<T> searchList(String path, String templateName, Map params, Class<T> type) throws ElasticSearchException;

	/**
	 * 检索索引所有数据
	 * @param index
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> searchAll(String index,  Class<T> type) throws ElasticSearchException;

	/**
	 * scroll search
	 * https://www.elastic.co/guide/en/elasticsearch/reference/6.2/search-request-scroll.html
	 * @param scroll
	 * @param scrollId
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> searchScroll(String scroll,String scrollId ,Class<T> type) throws ElasticSearchException;

	/**
	 * scroll检索,每次检索结果交给scrollHandler回调函数处理
	 * @param path
	 * @param dslTemplate
	 * @param scroll
	 * @param type
	 * @param scrollHandler
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> scroll(String path,String dslTemplate,String scroll,Object params,Class<T> type,ScrollHandler<T> scrollHandler) throws ElasticSearchException;
	/**
	 * 一次性返回scroll检索结果
	 * @param path
	 * @param dslTemplate
	 * @param scroll
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> scroll(String path,String dslTemplate,String scroll,Object params,Class<T> type) throws ElasticSearchException;


	/**
	 * 一次性返回scroll检索结果
	 * @param path
	 * @param dslTemplate
	 * @param scroll
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> scroll(String path,String dslTemplate,String scroll,Map params,Class<T> type) throws ElasticSearchException;




	/**
	 * scroll检索,每次检索结果交给scrollHandler回调函数处理
	 * @param path
	 * @param dslTemplate
	 * @param scroll
	 * @param type
	 * @param scrollHandler
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> scroll(String path,String dslTemplate,String scroll,Map params,Class<T> type,ScrollHandler<T> scrollHandler) throws ElasticSearchException;


	/**
	 * slice scroll并行检索，每次检索结果列表交给scrollHandler回调函数处理
	 * @param path
	 * @param dslTemplate here is a example dsltemplate: must contain sliceId,sliceMax varialbe
	 * 	 *    <property name="scrollSliceQuery">
	 * 	 *         <![CDATA[
	 * 	 *          {
	 * 	 *            "slice": {
	 * 	 *                 "id": #[sliceId],
	 * 	 *                 "max": #[sliceMax]
	 * 	 *             },
	 * 	 *             "size":#[size],
	 * 	 *             "query": {
	 * 	 *                 "term" : {
	 * 	 *                     "gc.jvmGcOldCount" : 3
	 * 	 *                 }
	 * 	 *             }
	 * 	 *         }
	 * 	 *         ]]>
	 * 	 *     </property>
	 * @param scroll
	 * @param type
	 * @param scrollHandler 每次检索结果会被异步交给handle来处理
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public <T> ESDatas<T> scrollSlice(String path,final String dslTemplate,final Map params ,
									  final String scroll  ,final Class<T> type,
									  ScrollHandler<T> scrollHandler,boolean parallel) throws ElasticSearchException;


	/**
	 * slice scroll并行检索，每次检索结果列表交给scrollHandler回调函数处理
	 * @param path
	 * @param dslTemplate here is a example dsltemplate: must contain sliceId,sliceMax varialbe
	 *    <property name="scrollSliceQuery">
	 *         <![CDATA[
	 *          {
	 *            "slice": {
	 *                 "id": #[sliceId],
	 *                 "max": #[sliceMax]
	 *             },
	 *             "size":#[size],
	 *             "query": {
	 *                 "term" : {
	 *                     "gc.jvmGcOldCount" : 3
	 *                 }
	 *             }
	 *         }
	 *         ]]>
	 *     </property>
	 * @param scroll
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public <T> ESDatas<T> scrollSlice(String path,final String dslTemplate,final Map params ,
									  final String scroll  ,final Class<T> type,boolean parallel) throws ElasticSearchException;


	/**
	 * 一次性返回scroll检索结果
	 * @param path
	 * @param entity
	 * @param scroll
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> scroll(String path,String entity,String scroll ,Class<T> type) throws ElasticSearchException;


	/**
	 * scroll检索,每次检索结果列表交给scrollHandler回调函数处理
	 * @param path
	 * @param entity
	 * @param scroll
	 * @param type
	 * @param scrollHandler
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T> ESDatas<T> scroll(String path,String entity,String scroll ,Class<T> type,ScrollHandler<T> scrollHandler) throws ElasticSearchException;



	/**
	 * scroll search
	 * https://www.elastic.co/guide/en/elasticsearch/reference/6.2/search-request-scroll.html
	 * @param scroll
	 * @param scrollId

	 * @return
	 * @throws ElasticSearchException
	 */
	public String searchScroll(String scroll,String scrollId ) throws ElasticSearchException;

	/**
	 * 清理scrollId
	 * @param scrollIds
	 * @return
	 * @throws ElasticSearchException
	 */
	public String deleteScrolls(String ... scrollIds) throws ElasticSearchException;

	/**
	 * 清理all scrollId
	 * @return
	 * @throws ElasticSearchException
	 */
	public String deleteAllScrolls() throws ElasticSearchException;

	/**
	 * 清理scrollId
	 * @param scrollIds
	 * @return
	 * @throws ElasticSearchException
	 */
	public String deleteScrolls(List<String> scrollIds) throws ElasticSearchException;

	public abstract <T> ESDatas<T> searchList(String path, String templateName, Object params, Class<T> type) throws ElasticSearchException;

	public abstract <T> ESDatas<T> searchList(String path, String entity, Class<T> type) throws ElasticSearchException;

	public abstract <T> T searchObject(String path, String templateName, Map params, Class<T> type) throws ElasticSearchException;

	public abstract <T> T searchObject(String path, String templateName, Object params, Class<T> type) throws ElasticSearchException;

	public abstract <T> T searchObject(String path, String entity, Class<T> type) throws ElasticSearchException;

	/**
	 * 聚合查询方法
	 *
	 * @param path   es查询请求相对地址
	 * @param entity es聚合查询模板
	 * @param params es聚合查询参数
	 * @param type   es聚合查询结果类
	 * @param aggs   es聚合查询结果名称
	 * @param stats  es聚合查询统计结果名称
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Map params, Class<T> type, String aggs, String stats, ESAggBucketHandle<T> aggBucketHandle) throws ElasticSearchException;

	public abstract <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Object params, Class<T> type, String aggs, String stats,ESAggBucketHandle<T> aggBucketHandle) throws ElasticSearchException;

	public abstract <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Class<T> type, String aggs, String stats,ESAggBucketHandle<T> aggBucketHandle) throws ElasticSearchException;

	public abstract <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Map params, Class<T> type, String aggs, String stats) throws ElasticSearchException;

	public abstract <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Object params, Class<T> type, String aggs, String stats) throws ElasticSearchException;

	public abstract <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Class<T> type, String aggs, String stats) throws ElasticSearchException;
	public abstract String createTempate(String template,String entity)  throws ElasticSearchException;

	public abstract String createTempate(String template, String templateName,Object params) throws ElasticSearchException ;
	/**
	 * 删除模板
	 * @param template
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String deleteTempate(String template) throws ElasticSearchException ;
	public abstract String createTempate(String template, String templateName,Map params) throws ElasticSearchException ;
	/**
	 * 查询模板
	 * @param template
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String getTempate(String template) throws ElasticSearchException ;
	
	/**
	 * 查询所有模板
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String getTempate() throws ElasticSearchException ;

	/**
	 * 删除所有xpack相关的监控索引
	 * @return
	 * @throws ElasticSearchException
	 */
	public abstract String cleanAllXPackIndices() throws ElasticSearchException;

	/**
	 * The simplest usage of _update_by_query just performs an update on every document in the index without changing the source. This is useful to pick up a new property or some other online mapping change. Here is the API:

	 * POST twitter/_update_by_query?conflicts=proceed
	 * @param path twitter/_update_by_query?conflicts=proceed
	 * @return
	 * @throws ElasticSearchException
	 * @see "https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update-by-query.html#picking-up-a-new-property"
	 */
	public String updateByQuery(String path) throws ElasticSearchException;
	/**
	 * The simplest usage of _update_by_query just performs an update on every document in the index without changing the source. This is useful to pick up a new property or some other online mapping change. Here is the API:

	 * POST twitter/_update_by_query?conflicts=proceed
	 * @param path twitter/_update_by_query?conflicts=proceed
	 *             twitter/_doc/_update_by_query?conflicts=proceed
	 *             twitter/_update_by_query
	 *             twitter,blog/_doc,post/_update_by_query
	 *             twitter/_update_by_query?routing=1
	 *             twitter/_update_by_query?scroll_size=100
	 *             twitter/_update_by_query?pipeline=set-foo
	 *
	 *
	 * @param entity
	 * @return
	 * @throws ElasticSearchException
	 * @see "https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update-by-query.html#picking-up-a-new-property"
	 */
	public String updateByQuery(String path,String entity) throws ElasticSearchException;

	/**
	 * The simplest usage of _update_by_query just performs an update on every document in the index without changing the source. This is useful to pick up a new property or some other online mapping change. Here is the API:

	 * POST twitter/_update_by_query?conflicts=proceed
	 * @param path twitter/_update_by_query?conflicts=proceed
	 *             twitter/_doc/_update_by_query?conflicts=proceed
	 *             twitter/_update_by_query
	 *             twitter,blog/_doc,post/_update_by_query
	 *             twitter/_update_by_query?routing=1
	 *             twitter/_update_by_query?scroll_size=100
	 *             twitter/_update_by_query?pipeline=set-foo
	 *
	 *
	 * @param templateName
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 * @see "https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update-by-query.html#picking-up-a-new-property"
	 */
	public String updateByQuery(String path,String templateName,Map params) throws ElasticSearchException;

	/**
	 * The simplest usage of _update_by_query just performs an update on every document in the index without changing the source. This is useful to pick up a new property or some other online mapping change. Here is the API:

	 * POST twitter/_update_by_query?conflicts=proceed
	 * @param path twitter/_update_by_query?conflicts=proceed
	 *             twitter/_doc/_update_by_query?conflicts=proceed
	 *             twitter/_update_by_query
	 *             twitter,blog/_doc,post/_update_by_query
	 *             twitter/_update_by_query?routing=1
	 *             twitter/_update_by_query?scroll_size=100
	 *             twitter/_update_by_query?pipeline=set-foo
	 *
	 *
	 * @param templateName
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 * @see "https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update-by-query.html#picking-up-a-new-property"
	 */
	public String updateByQuery(String path,String templateName,Object params) throws ElasticSearchException;

	/**
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html
	 * @param index _mget
	 *             test/_mget
	 *             test/type/_mget
	 *             test/type/_mget?stored_fields=field1,field2
	 *             _mget?routing=key1
	 * @param indexType
	 * @param type
	 * @param ids
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public <T> List<T> mgetDocuments(String index,String indexType,Class<T> type,Object ... ids)  throws ElasticSearchException;
	/**
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html
	 * @param index _mget
	 *             test/_mget
	 *             test/type/_mget
	 *             test/type/_mget?stored_fields=field1,field2
	 *             _mget?routing=key1
	 * @param indexType
	 * @param ids
	 * @return
	 * @throws ElasticSearchException
	 */
	public String mgetDocuments(String index,String indexType,Object ... ids)  throws ElasticSearchException;
	/**
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html
	 * @param path _mget
	 *             test/_mget
	 *             test/type/_mget
	 *             test/type/_mget?stored_fields=field1,field2
	 *             _mget?routing=key1
	 * @param entity
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public <T> List<T> mgetDocuments(String path,String entity,Class<T> type)  throws ElasticSearchException;

	/**
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html
	 * @param path _mget
	 *             test/_mget
	 *             test/type/_mget
	 *             test/type/_mget?stored_fields=field1,field2
	 *             _mget?routing=key1
	 * @param templateName
	 * @param params
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public <T> List<T> mgetDocuments(String path,String templateName,Object params,Class<T> type)  throws ElasticSearchException;

	/**
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html
	 * @param path _mget
	 *             test/_mget
	 *             test/type/_mget
	 *             test/type/_mget?stored_fields=field1,field2
	 *             _mget?routing=key1
	 * @param templateName
	 * @param params
	 * @param type
	 * @param <T>
	 * @return
	 * @throws ElasticSearchException
	 */
	public <T> List<T> mgetDocuments(String path,String templateName,Map params,Class<T> type)  throws ElasticSearchException;
	public long count(String index,String entity)  throws ElasticSearchException;
	public long count(String index,String template,Map params)  throws ElasticSearchException;
	public long count(String index,String template,Object params)  throws ElasticSearchException;

	/**
	 * 查询index中的所有文档数量
	 * @param index
	 * @return
	 * @throws ElasticSearchException
	 */
	public long countAll(String index) throws ElasticSearchException;


	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param indexType
	 * @param id
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String indexType,Object id,Object params,Boolean detect_noop,Boolean doc_as_upsert) throws ElasticSearchException;

	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param indexType
	 * @param id
	 * @param params
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String indexType,Object id,Map params,Boolean detect_noop,Boolean doc_as_upsert) throws ElasticSearchException;


	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param indexType
	 * @param id
	 * @param params
	 * @param refreshOption
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String indexType,Object id,Map params,String refreshOption,Boolean detect_noop,Boolean doc_as_upsert) throws ElasticSearchException;

	/**
	 * 根据路径更新文档
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
	 * @param index test/_doc/1
	 *             test/_doc/1/_update
	 * @param indexType
	 * @param id
	 * @param params
	 * @param refreshOption
	 * @param detect_noop default null
	 * @param doc_as_upsert default null
	 *    refresh=wait_for
	 *    refresh=false
	 *    refresh=true
	 *    refresh
	 *    Empty string or true
	Refresh the relevant primary and replica shards (not the whole index) immediately after the operation occurs, so that the updated document appears in search results immediately. This should ONLY be done after careful thought and verification that it does not lead to poor performance, both from an indexing and a search standpoint.
	wait_for
	Wait for the changes made by the request to be made visible by a refresh before replying. This doesn’t force an immediate refresh, rather, it waits for a refresh to happen. Elasticsearch automatically refreshes shards that have changed every index.refresh_interval which defaults to one second. That setting is dynamic. Calling the Refresh API or setting refresh to true on any of the APIs that support it will also cause a refresh, in turn causing already running requests with refresh=wait_for to return.
	false (the default)
	Take no refresh related actions. The changes made by this request will be made visible at some point after the request returns.
	 *
	 * @return
	 * @throws ElasticSearchException
	 */
	public String updateDocument(String index,String indexType,Object id,Object params,String refreshOption,Boolean detect_noop,Boolean doc_as_upsert) throws ElasticSearchException;

	/**
	 *
	 * Reindex does not attempt to set up the destination index.
	 * It does not copy the settings of the source index. You should set up the destination index prior to running a _reindex action, including setting up mappings, shard counts, replicas, etc.
	 * more detail see https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-reindex.html
	 *
	 * @param sourceIndice
	 * @param destIndice
	 * @return
	 */
	public String reindex(String sourceIndice,String destIndice);


	/**
	 *
	 * Reindex does not attempt to set up the destination index.
	 * It does not copy the settings of the source index. You should set up the destination index prior to running a _reindex action, including setting up mappings, shard counts, replicas, etc.
	 * more detail see https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-reindex.html
	 *
	 * @param sourceIndice
	 * @param destIndice
	 * @return
	 */
	public String reindex(String sourceIndice,String destIndice,String versionType);

	/**
	 *
	 * Reindex does not attempt to set up the destination index.
	 * It does not copy the settings of the source index. You should set up the destination index prior to running a _reindex action, including setting up mappings, shard counts, replicas, etc.
	 * more detail see https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-reindex.html
	 *
	 * @param sourceIndice
	 * @param destIndice
	 * @return
	 */
	public String reindex(String sourceIndice,String destIndice,String opType,String conflicts);
	/**
	 * Associating the alias alias with index indice
	 * more detail see :
	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html
	 * @param indice
	 * @param alias
	 * @return
	 */
	public String addAlias(String indice,String alias);

	/**
	 * removing that same alias [alias] of [indice]
	 * more detail see :
	 * 	 * https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html
	 * @param indice
	 * @param alias
	 * @return
	 */
	public String removeAlias(String indice,String alias);
}
