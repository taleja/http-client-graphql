package poc.qraphqlclient;

import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) {
		Function<String, String> fn = parameter -> parameter + "qwety";
		Predicate<String> predicate = parameter -> parameter.endsWith(parameter); 

	}

}
