/*
 * 
 */
package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.config.JestClientConnection;
import com.example.model.CardType;
import com.example.utilities.Constant;
import com.example.utilities.Utility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;

// TODO: Auto-generated Javadoc
/**
 * The Class CardTypeTest.
 */
public class CardTypeTest {

	/**
	 * Creates the card type.
	 *
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void createCardType() throws JsonParseException, JsonMappingException, IOException {

		CardType cc = new CardType();
		cc.setCode("C");
		cc.setDescription("Credit Card");

		final JsonNode jsonNode = Utility.getObjectToJson(cc);
		final String creaditCard = Utility.getJsonNodeToString(jsonNode);
		final JestResult jestResult = Utility.insertRecord(creaditCard, Constant.elasticDocType_CardTypes, "C");

		System.out.println("Response code -> " + jestResult.getResponseCode());
		System.out.println("Response isSuccess -> " + jestResult.isSucceeded());
		System.out.println("Response message -> " + jestResult.getJsonString());

	}

	/**
	 * Delete card type.
	 *
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void deleteCardType() throws JsonParseException, JsonMappingException, IOException {

		final JestResult jestResult = Utility.deleteRecord(Constant.elasticDocType_CardTypes, "R");

		System.out.println("Response code -> " + jestResult.getResponseCode());
		System.out.println("Response isSuccess -> " + jestResult.isSucceeded());
		System.out.println("Response message -> " + jestResult.getJsonString());

	}

	/**
	 * Creates the bulk card type.
	 *
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void createBulkCardType() throws JsonParseException, JsonMappingException, IOException {

		List<CardType> list = new ArrayList<>();
		list.add(new CardType("D", "Debit Card"));
		list.add(new CardType("R", "Rupay Card"));

		final JsonNode cardTypeJsonNode = Utility.getObjectToJson(list);

		if (cardTypeJsonNode.isArray()) {

			final Builder bulkIndexBuilder = new Bulk.Builder();
			for (final JsonNode node : cardTypeJsonNode) {

				final String primaryId = node.get("code").textValue();

				bulkIndexBuilder.addAction(
						Utility.createBulkIndex(node.toString(), Constant.elasticDocType_CardTypes, primaryId));
			}

			final JestResult jestResult = JestClientConnection.getJestClient().execute(bulkIndexBuilder.build());

			System.out.println("Response code -> " + jestResult.getResponseCode());
			System.out.println("Response isSuccess -> " + jestResult.isSucceeded());
			System.out.println("Response message -> " + jestResult.getJsonString());

		}

	}

	/**
	 * Update card type.
	 *
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void updateCardType() throws JsonParseException, JsonMappingException, IOException {

		CardType cc = new CardType();
		cc.setCode("C");
		cc.setDescription("Credit Card New");

		final JsonNode jsonNode = Utility.getObjectToJson(cc);
		final String creaditCard = Utility.getJsonNodeToString(jsonNode);
		final JestResult jestResult = Utility.updateRecord(creaditCard, Constant.elasticDocType_CardTypes, "C");

		System.out.println("Response code -> " + jestResult.getResponseCode());
		System.out.println("Response isSuccess -> " + jestResult.isSucceeded());
		System.out.println("Response message -> " + jestResult.getJsonString());

	}

	/**
	 * Gets the card type desc.
	 *
	 * @return the card type desc
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void getCardTypeDesc() throws IOException {
		final String cardTypesDesc = Utility.getCardTypeDescription(Constant.elasticDocType_CardTypes, "C");
		System.out.println("cardTypesDesc : " + cardTypesDesc);
	}

	/**
	 * Gets the elastic search payload.
	 *
	 * @return the elastic search payload
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void getElasticSearchPayload() throws IOException {
		final JsonNode jsonNode = Utility.getElasticSearchPayload(Constant.elasticDocType_CardTypes, "C");

		final CardType cardType = (CardType) Utility.getJsonNodeToObject(jsonNode, CardType.class);

		System.out.println("cardType : " + cardType);

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {

		// createCardType();
		// createBulkCardType();
		// getCardTypeDesc();
		// deleteCardType();
		// updateCardType();
		getElasticSearchPayload();
	}

}
