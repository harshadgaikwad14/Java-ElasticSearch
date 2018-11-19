/*
 * 
 */
package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.config.JestClientConnection;
import com.example.model.BuildingStatusInfo;
import com.example.model.BuildingStructure;
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
public class BuildingStructureTest {

	/**
	 * Creates the bulk card type.
	 *
	 * @throws JsonParseException
	 *             the json parse exception
	 * @throws JsonMappingException
	 *             the json mapping exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void createBuildingStructure() throws JsonParseException, JsonMappingException, IOException {

		List<BuildingStructure> list = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {

			final BuildingStructure buildingStructure = new BuildingStructure();
			buildingStructure.setId("" + i);
			buildingStructure.setStructureName("buildingStructure_" + i);
			final BuildingStatusInfo buildingStatusInfo = new BuildingStatusInfo();
			buildingStatusInfo.setChannelSeqId("channelSeqId_" + i);
			buildingStatusInfo.setDomainSeqId("domainSeqId_" + i);
			buildingStructure.setStateInformation(buildingStatusInfo);
			list.add(buildingStructure);
		}

		final JsonNode buildingStructureTypeJsonNode = Utility.getObjectToJson(list);

		if (buildingStructureTypeJsonNode.isArray()) {

			final Builder bulkIndexBuilder = new Bulk.Builder();
			for (final JsonNode node : buildingStructureTypeJsonNode) {

				final String primaryId = node.get("id").textValue();

				bulkIndexBuilder.addAction(Utility.createBulkIndex(node.toString(),
						Constant.elasticDocType_BuildingStructures, primaryId));
			}

			final JestResult jestResult = JestClientConnection.getJestClient().execute(bulkIndexBuilder.build());

			System.out.println("Response code -> " + jestResult.getResponseCode());
			System.out.println("Response isSuccess -> " + jestResult.isSucceeded());
			System.out.println("Response message -> " + jestResult.getJsonString());

		}

	}

	public static void getTermQueryData(final String channelSeqId) throws IOException {
		Utility.getTermQueryData(channelSeqId);
		
		
		/*
		 * Search Using Postman
		
			Url : http://localhost:9200/quest.liqc/buildingStructures/_search
			Method : Post
			Query : 
				
			{
				  "query": {
				    "match": {
				      "stateInformation.channelSeqId": "channelSeqId_2"
				    }
				  }
			}
			
		*/
		
			
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {

		//createBuildingStructure();

		getTermQueryData("channelSeqId_5");
	}

}
