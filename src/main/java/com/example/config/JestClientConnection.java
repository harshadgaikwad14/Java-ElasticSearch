/*
 * 
 */
package com.example.config;

import com.example.utilities.Constant;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

// TODO: Auto-generated Javadoc
/**
 * The Class JestClientConnection.
 */
public class JestClientConnection {

	/**
	 * Gets the jest client.
	 *
	 * @return the jest client
	 */
	public static JestClient getJestClient() {
		final JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(
				Constant.elasticProtocol + "://" + Constant.elasticHost + ":" + Constant.elasticPort)
						.defaultCredentials(Constant.elasticUser, Constant.elasticPassword)
						.readTimeout(Constant.elasticDefaultTimeout).multiThreaded(true).build());
		return factory.getObject();
	}

}
