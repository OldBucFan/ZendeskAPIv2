package zdAPI;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PostZendeskData {

	public static String main(String[] args, String payload) throws IOException {
		//args0 - uri tail    NOTE! - Parameter values must arrive URL encoded
		//args1 - username
		//args2 - token
		HttpHost target = new HttpHost("dataminr.zendesk.com", 443, "https");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(args[1], args[2]));
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();
        try {
            AuthCache authCache = new BasicAuthCache();
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);
            HttpPost post = new HttpPost("/api/v2/" + args[0]);
            StringEntity postEntity = new StringEntity(payload,ContentType.create("application/json", "UTF-8"));
            post.setEntity(postEntity);
            
            System.out.println("Executing request " + post.getRequestLine() + " to target: " + target);
            CloseableHttpResponse response = httpClient.execute(target, post, localContext);
            try{
            	int statusCode = response.getStatusLine().getStatusCode();
            	switch (statusCode/100){
            	case 2:
                	return EntityUtils.toString(response.getEntity());
				case 5:
                	throw new RuntimeException("Post Failed with HTTP error code: " + statusCode);
				default:
            		return statusCode + ":\r\n" + EntityUtils.toString(response.getEntity());
                }
            } finally {
            	response.close();
            }
        } finally {
        	httpClient.close();
        }            
	}
}
