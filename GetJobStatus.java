package zdAPI;

import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetJobStatus {

	public static JobStatus main(String[] args) throws Exception{
		//args0 - uri
		//args1 - username
		//args2 - token

		HttpHost target = new HttpHost("dataminr.zendesk.com", 443, "https");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(args[1], args[2]));
        CloseableHttpClient httpclient = HttpClients.custom()
        		.disableContentCompression()
                .setDefaultCredentialsProvider(credsProvider).build();
        try {
            AuthCache authCache = new BasicAuthCache();
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);
            HttpGet httpget = new HttpGet(args[0]);
            httpget.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
            System.out.println("Executing request " + httpget.getRequestLine() + " to target " + target);
            CloseableHttpResponse response = httpclient.execute(target, httpget, localContext);
            HttpEntity entity = response.getEntity();
            Header contentEncodingHeader = entity.getContentEncoding();
            if (contentEncodingHeader != null) {
                HeaderElement[] encodings =contentEncodingHeader.getElements();               
                for (int i = 0; i < encodings.length; i++) {
                    if (encodings[i].getName().equalsIgnoreCase("gzip")) {
                        entity = new GzipDecompressingEntity(entity);
                        break;
                    }
                }
            }
            String result = EntityUtils.toString(entity, Charset.forName("UTF-8").name());
            try {
            	int statusCode = response.getStatusLine().getStatusCode();
            	
            	switch (statusCode/100){
            	case 2:
            		JsonObject objStatus = (JsonObject) new JsonParser().parse(result);
                	JsonElement objElement = objStatus.get("job_status");
            		Gson gson = new GsonBuilder()
                		.registerTypeAdapter(JobStatus.class, new JobStatusDeserializer())
                		.create();
                	JobStatus theJob =  gson.fromJson(objElement, JobStatus.class);
                	return theJob;
				case 5:
                	throw new RuntimeException("Failed with HTTP error code: " + statusCode);
				default:
					throw new RuntimeException("Failed following problem: " + statusCode);
                }
            	
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
