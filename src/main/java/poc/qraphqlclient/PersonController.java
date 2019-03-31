package poc.qraphqlclient;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;

import poc.graphqlclient.http.dto.Student;

@RestController
public class PersonController {

	@RequestMapping(method = RequestMethod.POST, value = "/graphql")
	public List<Student> doPost() throws JsonParseException, JsonMappingException, IOException{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
				
		headers.setContentType(MediaType.APPLICATION_JSON);
		String jsonRequest = "{\"query\":\"{allStudents{name lastName}}\"}";

		HttpEntity<String> request = new HttpEntity<String>(jsonRequest, headers);
		String response = restTemplate.postForEntity("http://localhost:8080/graphql", request, String.class).getBody();
	
		ObjectMapper mapper = new ObjectMapper(); 
		ObjectReader reader = mapper.readerFor(new TypeReference<List<Student>>(){});
		
		JsonNode jsonNodeRoot = mapper.readTree(response);
		JsonNode jsonNodeData = (ObjectNode)jsonNodeRoot.get("data");
		JsonNode jsonNodeAllStudents = jsonNodeData.get("allStudents");
		List<Student> persons = reader.readValue(jsonNodeAllStudents);
		persons.stream().forEach(System.out::println);
		return persons;		
	}	
}
