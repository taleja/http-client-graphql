package poc.graphqlclient.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import poc.graphqlclient.http.dto.Student;
import poc.graphqlclient.http.dto.StudentById;
import poc.graphqlclient.http.queryhandller.QueryClient;


public class QueryHanlderStarter {

	public static void main(String[] args) throws IOException {	
		QueryClient queryHandler = new QueryClient();
		//List<Student> allStudents = queryHandler.handleQuery(Student.class);
		//System.out.println(allStudents);
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put("id", "2");
		queryHandler.handleQuery(StudentById.class, parameters);
//		GetStudentByIdQueryHandler getStudentByIdHandler = new GetStudentByIdQueryHandler();
//		System.out.println(getStudentByIdHandler.handleQuery());
	}

}
