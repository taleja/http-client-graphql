package poc.graphqlclient.http.queryhandller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryClient {

	private static final String URL = "http://localhost:8080/graphql";
	private QueryParser allStudentsQueryParser;
	private CloseableHttpClient client;

	public QueryClient() {
		this.client = HttpClients.createDefault();
		this.allStudentsQueryParser = new QueryParser();
	}

	public <T>List<T> handleQuery(Class<T> clazz, Map<String, String> parameters) throws ClientProtocolException, IOException {

		HttpPost httpPost = new HttpPost(URL);
		StringEntity entity = null;
		Query queryAnnotation = clazz.getAnnotation(Query.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> request = new HashMap<>();
		request.put("query", queryAnnotation.value());
		request.put("parameters", parameters);

		try {
			entity = new StringEntity(mapper.writeValueAsString(request));
		} catch (UnsupportedEncodingException e) {
			//log.error(e.getMessage(), e);
		}
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		HttpResponse httpResponse = client.execute(httpPost);
		HttpEntity responseEntity = httpResponse.getEntity();

		String jsonString = EntityUtils.toString(responseEntity);
		client.close();

		return this.allStudentsQueryParser.parseQuery(jsonString, queryAnnotation, clazz);
	}
	
	public <T>List<T> handleQuery(Class<T> clazz) throws ClientProtocolException, IOException {
		return handleQuery(clazz, Collections.emptyMap());
	}

}
