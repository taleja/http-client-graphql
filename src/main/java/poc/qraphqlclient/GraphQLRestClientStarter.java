package poc.qraphqlclient;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


@SpringBootApplication
public class GraphQLRestClientStarter {
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException {
    	SpringApplication.run(GraphQLRestClientStarter.class, args);
    }
}
