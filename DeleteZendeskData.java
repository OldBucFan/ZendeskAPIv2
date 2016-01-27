package zdAPI;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class DeleteZendeskData {

	public static int main(String[] args) throws IOException {
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
            HttpDelete del = new HttpDelete("/api/v2/" + args[0]);
            
            System.out.println("Executing request " + del.getRequestLine() + " to target: " + target);
            CloseableHttpResponse response = httpClient.execute(target, del, localContext);
            try {
	            return response.getStatusLine().getStatusCode();
            } finally {
            	response.close();
            }
        } finally {
        	httpClient.close();
        }

	}

}
