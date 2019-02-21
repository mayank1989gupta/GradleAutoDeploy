package com.test.api.interceptors;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

/**
 * 
 */

/**
 * @author MGupta
 *
 */
public class APILoggingInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOGGER = Logger.getLogger(APILoggingInterceptor.class.getName());
	private static final String CHARSET_NAME = "UTF-8";

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String requestBody = String.valueOf(Charset.forName("UTF-8").decode(ByteBuffer.wrap(body)));
		logRequest(requestBody, request);//Logging Request
		ClientHttpResponse response = null;
		try {
			response = execution.execute(request, body);
			logResponse(response);//Logging response
		} catch (IOException exception) {
			LOGGER.log(Level.SEVERE, "Error while intercepting the reuest", exception);
		}

		return response;//response
	}

	/**
	 * Method to log request of the request intercepted.</br>
	 * 
	 * @param body
	 * @param request
	 */
	private void logRequest(String body, HttpRequest request) {
		StringBuilder builder = new StringBuilder()
				.append(System.lineSeparator())
				.append("================ REST Request BEGIN ================")
				.append(System.lineSeparator())
				.append("Request URL: ").append(request.getURI())
				.append(System.lineSeparator())
				.append("API: ").append(request.getMethod())
				.append(System.lineSeparator())
				.append("Body: ")
				.append(!StringUtils.isEmpty(body) ? body : "[empty]")
				.append(System.lineSeparator())
				.append("================ REST Request END ==================");
		LOGGER.info(builder.toString());

	}
	
	/**
	 * Method to log response of the request intercepted.</br> 
	 * @param response
	 * @throws IOException
	 */
	private void logResponse(ClientHttpResponse response) throws IOException {
		/*
		 * BufferedReader bufferedReader = new BufferedReader(new
		 * InputStreamReader(response.getBody(), CHARSET_NAME)); String line =
		 * bufferedReader.readLine();
		 */
		StringBuilder bodyBuilder = new StringBuilder();
		/*
		 * while (line != null) {
		 * bodyBuilder.append(line).append(System.lineSeparator()); line =
		 * bufferedReader.readLine(); }
		 */

		StringBuilder builder = new StringBuilder()
				.append(System.lineSeparator())
				.append("================ REST Response BEGIN ================")
				.append(System.lineSeparator())
				.append("Status: ")
				.append(response.getStatusCode())
				.append(System.lineSeparator())
				.append("Body: ")
				.append(bodyBuilder)
				.append(System.lineSeparator())
				.append("================ REST Response END ==================");
				LOGGER.info(builder.toString());
	}

}
