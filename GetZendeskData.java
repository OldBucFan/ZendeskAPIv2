package zdAPI;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetZendeskData {

	public static String main(String[] args) throws Exception {
		//args0 - uri tail    NOTE! - Parameter values must arrive URL encoded
		//args1 - username
		//args2 - token
        HttpHost target = new HttpHost("dataminr.zendesk.com", 443, "https");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(args[1], args[2]));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();
        try {
            AuthCache authCache = new BasicAuthCache();
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);
            HttpGet httpget = new HttpGet("/api/v2/" + args[0]);
            //System.out.println("Executing request " + httpget.getRequestLine() + " to target " + target);
            CloseableHttpResponse response = httpclient.execute(target, httpget, localContext);
            try {
            	int statusCode = response.getStatusLine().getStatusCode();
            	
            	switch (statusCode/100){
            	case 2:
                	return EntityUtils.toString(response.getEntity());
				case 5:
                	throw new RuntimeException("Failed with HTTP error code: " + statusCode);
				default:
            		return statusCode + EntityUtils.toString(response.getEntity());
                }
            	
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
