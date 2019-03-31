package poc.graphqlclient.http.dto;

import poc.graphqlclient.http.queryhandller.Query;

@Query(value = "{allStudents{name lastName}}", name = "allStudents")
//@Query(value = "{\"query\":\"{allStudents{name lastName}}\"}", name = "allStudents")
public class Student {
	
	private String name;
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
		return "Student [name=" + name + ", lastName=" + lastName + "]";
	}
	
	

} 
