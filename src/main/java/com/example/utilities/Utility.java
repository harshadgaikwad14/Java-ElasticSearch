/*
 * 
 */
package com.example.utilities;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.config.JestClientConnection;
import com.example.model.CardType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Count;
import io.searchbox.core.CountResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.Update;

// TODO: Auto-generated Javadoc
/**
 * The Class Utility.
 */
public class Utility {

	/**
	 * Gets the jest client.
	 *
	 * @return the jest client
	 */
	public static JestClient getJestClient() {
		return JestClientConnection.getJestClient();
	}

	/**
	 * Gets the object mapper.
	 *
	 * @return the object mapper
	 */
	public static ObjectMapper getObjectMapper() {
		final SimpleModule module = new SimpleModule();
		module.addSerializer(BigDecimal.class, new ToStringSerializer());
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(module);

	}

	/**
	 * Gets the builder.
	 *
	 * @param indexName
	 *            the index name
	 * @param typeName
	 *            the type name
	 * @param id
	 *            the id
	 * @return the builder
	 */
	public static Get getBuilder(final String indexName, final String typeName, final String id) {
		return new Get.Builder(indexName, id).type(typeName).build();
	}

	/**
	 * Creates the bulk index.
	 *
	 * @param data
	 *            the data
	 * @param typeName
	 *            the type name
	 * @param id
	 *            the id
	 * @return the index
	 */
	public static Index createBulkIndex(final String data, final String typeName, final String id) {
		return new Index.Builder(data).index(Constant.elasticCommonIndexName).type(typeName).id(id).build();
	}

	/**
	 * Delete bulk index.
	 *
	 * @param typeName
	 *            the type name
	 * @param id
	 *            the id
	 * @return the delete
	 */
	public static Delete deleteBulkIndex(final String typeName, final String id) {
		return new Delete.Builder(id).index(Constant.elasticCommonIndexName).type(typeName).build();
	}

	/**
	 * Update bulk index.
	 *
	 * @param data
	 *            the data
	 * @param typeName
	 *            the type name
	 * @param id
	 *            the id
	 * @return the update
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Update updateBulkIndex(final String data, final String typeName, final String id) throws IOException {
		final ObjectNode docNode = JsonNodeFactory.instance.objectNode();
		docNode.set("doc", Utility.getObjectMapper().readValue(data, JsonNode.class));
		return new Update.Builder(docNode.toString()).index(Constant.elasticCommonIndexName).type(typeName).id(id)
				.build();
	}

	/**
	 * Insert record.
	 *
	 * @param enrichData
	 *            the enrich data
	 * @param elasticDocType
	 *            the elastic doc type
	 * @param id
	 *            the id
	 * @return the jest result
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static JestResult insertRecord(final String enrichData, final String elasticDocType, final String id)
			throws IOException {
		final Builder bulkIndexBuilder = new Bulk.Builder();
		JestResult jestResult = null;
		final JsonNode enrichedNode = Utility.getObjectMapper().readTree(enrichData);

		bulkIndexBuilder.addAction(Utility.createBulkIndex(enrichedNode.toString(), elasticDocType, id));

		try {
			jestResult = JestClientConnection.getJestClient().execute(bulkIndexBuilder.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jestResult;
	}

	/**
	 * Update record.
	 *
	 * @param enrichData
	 *            the enrich data
	 * @param elasticDocType
	 *            the elastic doc type
	 * @param id
	 *            the id
	 * @return the jest result
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static JestResult updateRecord(final String enrichData, final String elasticDocType, final String id)
			throws IOException {
		final Builder bulkIndexBuilder = new Bulk.Builder();
		JestResult jestResult = null;
		final JsonNode enrichedNode = Utility.getObjectMapper().readTree(enrichData);

		bulkIndexBuilder.addAction(Utility.updateBulkIndex(enrichedNode.toString(), elasticDocType, id));

		try {
			jestResult = JestClientConnection.getJestClient().execute(bulkIndexBuilder.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jestResult;
	}

	/**
	 * Bulk insert record.
	 *
	 * @param enrichData
	 *            the enrich data
	 * @param elasticDocType
	 *            the elastic doc type
	 * @param id
	 *            the id
	 * @return the jest result
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static JestResult bulkInsertRecord(final String enrichData, final String elasticDocType, final String id)
			throws IOException {
		final Builder bulkIndexBuilder = new Bulk.Builder();
		JestResult jestResult = null;
		final JsonNode enrichedNode = Utility.getObjectMapper().readTree(enrichData);

		bulkIndexBuilder.addAction(Utility.createBulkIndex(enrichedNode.toString(), elasticDocType, id));

		try {
			jestResult = JestClientConnection.getJestClient().execute(bulkIndexBuilder.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jestResult;
	}

	/**
	 * Delete record.
	 *
	 * @param typeName
	 *            the type name
	 * @param id
	 *            the id
	 * @return the jest result
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static JestResult deleteRecord(final String typeName, final String id) throws IOException {

		final Builder bulkIndexBuilder = new Bulk.Builder();
		final JestResult jestResult;

		bulkIndexBuilder.addAction(deleteBulkIndex(typeName, id));

		jestResult = JestClientConnection.getJestClient().execute(bulkIndexBuilder.build());

		return jestResult;

	}

	/**
	 * Gets the card type description.
	 *
	 * @param typeName
	 *            the type name
	 * @param id
	 *            the id
	 * @return the card type description
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getCardTypeDescription(final String typeName, final String id) throws IOException {
		final String description;
		final Get get = Utility.getBuilder(Constant.elasticCommonIndexName, typeName, id);
		final JestResult jestResult = JestClientConnection.getJestClient().execute(get);
		if (jestResult.getSourceAsString() == null) {
			description = null;
		} else {
			final JsonNode payload = Utility.getObjectMapper().readTree(jestResult.getSourceAsString());
			description = payload.get("description").textValue();
		}
		return description;
	}

	/**
	 * Gets the elastic search payload.
	 *
	 * @param typeName
	 *            the type name
	 * @param id
	 *            the id
	 * @return the elastic search payload
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static JsonNode getElasticSearchPayload(final String typeName, final String id) throws IOException {
		JsonNode payload = null;
		final Get get = Utility.getBuilder(Constant.elasticCommonIndexName, typeName, id);
		final JestResult jestResult = JestClientConnection.getJestClient().execute(get);
		if (jestResult.getSourceAsString() == null) {
			payload = null;
		} else {
			payload = Utility.getObjectMapper().readTree(jestResult.getSourceAsString());
		}
		return payload;
	}

	/**
	 * Gets the object to json.
	 *
	 * @param object
	 *            the object
	 * @return the object to json
	 */
	public static JsonNode getObjectToJson(final Object object) {
		return getObjectMapper().convertValue(object, JsonNode.class);
	}

	/**
	 * Gets the json node to string.
	 *
	 * @param node
	 *            the node
	 * @return the json node to string
	 * @throws JsonParseException
	 *             the json parse exception
	 * @throws JsonMappingException
	 *             the json mapping exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getJsonNodeToString(final JsonNode node)
			throws JsonParseException, JsonMappingException, IOException {
		Object json = getObjectMapper().readValue(node.toString(), Object.class);
		return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(json);
	}

	/**
	 * Gets the json node to object.
	 *
	 * @param node
	 *            the node
	 * @param class1
	 *            the class 1
	 * @return the json node to object
	 */
	public static CardType getJsonNodeToObject(final JsonNode node, final Class<CardType> class1) {

		return getObjectMapper().convertValue(node, class1);
	}

	/**
	 * Builds the term query.
	 *
	 * @param prop
	 *            the prop
	 * @param propValue
	 *            the prop value
	 * @return the string
	 */
	public static String buildTermQuery(final String prop, final String propValue) {
		final String format = "{\"query\":{\"match\":{\"%s\":\"%s\"}}}";
		return String.format(format, prop, propValue);
	}

	private static int getQueryResultCount(final String query, final List<String> typeList, final String index)
			throws IOException {
		System.out.println("query : "+query);
		System.out.println("typeList : "+typeList);
		System.out.println("index : "+index);
		final CountResult result = JestClientConnection.getJestClient()
				.execute(new Count.Builder().query(query).addIndex(index).addTypes(typeList).build());
		return (result.getResponseCode() == 200) ? result.getCount().intValue() : 0;
	}

	public static SearchResult searchIntoESData(final String query, final List<String> typeList, final boolean isCommonIndex)
			throws IOException {

		// Get count of search result query
		final String index = Constant.elasticCommonIndexName;
		final int total = getQueryResultCount(query, typeList, index);

		System.out.println("Search query count : " + total);

		final String searchQuery = query.substring(0, query.length() - 1) + ",\"from\":0,\"size\":" + total + "}";
		final Search search = new Search.Builder(searchQuery)
				// multiple index or types can be added.
				.addIndex(index).addTypes(typeList).build();
		final SearchResult searchResult = JestClientConnection.getJestClient().execute(search);

		System.out.println("Search ResponseCode : " + searchResult.getResponseCode() + " Search Result : "
				+ searchResult.getJsonString());

		return searchResult;
	}

	/**
	 * Gets the term query data.
	 *
	 * @return the term query data
	 * @throws IOException 
	 */
	public static void getTermQueryData(final String channelSeqId) throws IOException {
		final String query = buildTermQuery("stateInformation.channelSeqId", channelSeqId);
		final List<String> typeList = new ArrayList<String>();
		typeList.add(Constant.elasticDocType_BuildingStructures);
		final SearchResult searchResult = searchIntoESData(query, typeList, false);
		System.out.println("searchResult : "+searchResult);
		if (searchResult != null && searchResult.getResponseCode() == 200) {
			final List<Hit<Object, Void>> result = searchResult.getHits(Object.class);
			if (result != null && !result.isEmpty()) {
				for (int i = 0; i < result.size(); i++) {
					final Hit<Object, Void> hit = result.get(i);
					final JsonNode jsonNode = getObjectMapper().valueToTree(hit.source);
					System.out.println("jsonNode >>>>>>>>>  "+jsonNode);
				}
			}
		}
	}

}
