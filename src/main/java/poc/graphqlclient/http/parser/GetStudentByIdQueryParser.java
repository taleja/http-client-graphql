package poc.graphqlclient.http.parser;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;

import poc.graphqlclient.http.dto.Student;

public class GetStudentByIdQueryParser {

	public Student parseQuery(String jsonString) throws IOException {
		ObjectMapper mapper = new ObjectMapper(); 
		ObjectReader reader = mapper.readerFor(Student.class);
		JsonNode jsonNodeRoot = mapper.readTree(jsonString);
		
//		System.out.println("Size: "+ jsonNodeRoot.size());
////		System.out.println("Path"+ jsonNodeRoot.path(0).asText());
//		System.out.println("Get node: "+ jsonNodeRoot.get(0));
		
		JsonNode dataJsonNode = (ObjectNode)jsonNodeRoot.get("data");
		JsonNode queryNameJsonNode = dataJsonNode.get("getStudentById");
		return reader.readValue(queryNameJsonNode);
	}

}
