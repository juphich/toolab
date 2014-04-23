package net.toolab.http;

import net.toolab.http.context.DeleteRequestContext;
import net.toolab.http.context.GetRequestContext;
import net.toolab.http.context.HttpRequestContext;
import net.toolab.http.context.PostRequestContext;
import net.toolab.http.context.PutRequestContext;
import net.toolab.http.exception.RequesterCreationException;
import net.toolab.http.exception.UnsupportedRequestException;


public class HttpRequesterFactory {

	public static HttpRequester getRequester(HttpRequestContext context) 
			throws RequesterCreationException, UnsupportedRequestException {
		
		if (context instanceof GetRequestContext) {
			return new GetRequester((GetRequestContext) context);
		} else if (context instanceof PostRequestContext) {
			return new PostRequester((PostRequestContext) context);
		} else if (context instanceof PutRequestContext) {
			throw new UnsupportedRequestException("It doesn't support yet. - (put method)");
		} else if (context instanceof DeleteRequestContext) {
			throw new UnsupportedRequestException("It doesn't support yet. - (delete method)");
		}
		
		throw new UnsupportedRequestException("unknwon request method type.... : " + context.getClass());
	}
}