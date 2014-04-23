package net.toolab.http;

import net.toolab.http.exception.HttpRequestException;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;

public interface HttpRequester {

	String request() throws HttpResponseException;
	
	<R> R request(ResponseHandler<R> handler) throws HttpRequestException, HttpResponseException;
}
