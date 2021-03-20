package com.shopstick.web.client;

import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopstick.web.exception.GenericHttpException;
import com.shopstick.web.exception.UnauthorizedException;
import com.shopstick.web.util.Constants;

public class RestClient {

	private static final Logger logger = LoggerFactory.getLogger(RestClient.class);
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Method used to invoke a POST REST service
	 * 
	 * @param url
	 * @param service
	 * @param responseType
	 * @param bodyRequest
	 * @return
	 * @throws UnauthorizedException
	 * @throws GenericHttpException
	 */
	public <T> T callRestServicePost(String url, String service, Class<T> responseType,
			Object bodyRequest) throws UnauthorizedException, GenericHttpException {
		
		logger.debug("Invoking post rest service");
		T retValue = null;
		
		try {
			RestTemplate restService = new RestTemplate();

			String jsonString = mapper.writeValueAsString(bodyRequest);
			HttpEntity<Object> requestCall = new HttpEntity<>(jsonString, setHeader());
			
			retValue = restService.postForObject(url + service, requestCall, responseType);
		} catch (HttpClientErrorException e) {
			if (e.getMessage().startsWith(Constants.HTTP_STATUS_CODE_401) || e.getMessage().startsWith(Constants.HTTP_STATUS_CODE_403)) {
				throw new UnauthorizedException(e);
			} else {
				throw new GenericHttpException(e);
			}
		} catch (Exception e) {
			throw new GenericHttpException(e);
		}
		return retValue;
	}
	
	/**
	 * Method used to invoke a GET REST service
	 * 
	 * @param url
	 * @param service
	 * @param returnType
	 * @param postParams
	 * @return
	 * @throws UnauthorizedException
	 * @throws GenericHttpException
	 */
	public <T> T callRestServiceGet(String url, String service, Class<T> responseType, Map<String,?> uriVariables) throws UnauthorizedException, GenericHttpException {
		
		logger.debug("Invoking get rest service");
		T retValue = null;
		
		try {
			RestTemplate restService = new RestTemplate();
			retValue = restService.getForObject(url + service, responseType, uriVariables);
		} catch (HttpClientErrorException e) {
			if (e.getMessage().startsWith(Constants.HTTP_STATUS_CODE_401) || e.getMessage().startsWith(Constants.HTTP_STATUS_CODE_403)) {
				throw new UnauthorizedException(e);
			} else {
				throw new GenericHttpException(e);
			}
		} catch (Exception e) {
			throw new GenericHttpException(e);
		}
		return retValue;
	}

	private HttpHeaders setHeader() {
		HttpHeaders headerCall = new HttpHeaders();
		headerCall.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headerCall.setContentType(MediaType.APPLICATION_JSON);
		return headerCall;
	}

}
