package poc.graphqlclient.http.dto;

import poc.graphqlclient.http.queryhandller.Query;


@Query(value = "{getStudentById(id: $id){name lastName}}", name = "getStudentById")
public class StudentById {

	private String  name;
	private String lastName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "StudentById [name=" + name + ", lastName=" + lastName + "]";
	}
}
