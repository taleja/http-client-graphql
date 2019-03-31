package poc.graphqlclient.http.queryhandller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class QueryParser {

	
	public<T> List<T> parseQuery(String jsonString, Query queryAnnotation  ,Class<T> clazz) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper(); 
		ObjectReader reader = mapper.readerFor(clazz);
		JsonNode jsonNodeRoot = mapper.readTree(jsonString);
		
		JsonNode dataJsonNode = (ObjectNode)jsonNodeRoot.get("data");
		
		ArrayNode queryNameJsonNode = (ArrayNode)dataJsonNode.get(queryAnnotation.name());
		List<T> result = new ArrayList<>(queryNameJsonNode.size());
		for(int i = 0; i<queryNameJsonNode.size(); i++) {
			result.add(reader.readValue(queryNameJsonNode.get(i))); 
		}
		return result;

	}
	
}
