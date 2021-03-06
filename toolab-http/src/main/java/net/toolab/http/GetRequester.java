package net.toolab.http;

import java.io.IOException;

import net.toolab.http.context.GetRequestContext;
import net.toolab.http.exception.HttpRequestException;
import net.toolab.http.exception.RequesterCreationException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetRequester extends AbstractHttpRequester {
	
	GetRequester(GetRequestContext context) throws RequesterCreationException {
		super(context);
	}
	
	@Override
	public <R> R request(ResponseHandler<R> responseHandler) throws HttpRequestException, HttpResponseException {
		HttpClient client = new DefaultHttpClient();
		super.setParameters(client.getParams());
		
		try {
			HttpGet httpGet = new HttpGet(getRequestUri());
			this.applyHeaders(httpGet);
			
			if (log.isDebugEnabled()) {
				log.debug("http request... : " + httpGet.getURI());
			}

			return client.execute(httpGet, responseHandler);
		} catch (HttpResponseException response) {
			throw response;
		} catch (IOException e) {
			throw new HttpRequestException("request failed. : " + e.getMessage() , e);
		} finally {
			client.getConnectionManager().shutdown();
		}
	}
}
