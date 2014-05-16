package com.comtrade.lambda.mr;

import java.awt.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodReferences {

	public static void main(String... args) {
		MethodReferences mr = new MethodReferences();
		mr.go();
	}

	private void go() {
		String[] strings = {"Hopa", "Cupa"};
		// Sort kot drugi parameter rabi comparator interface, ki ima metodo
		// int compare(String), in to metodo lahko nadomesti String.compareToIgnoreCase()
		Arrays.sort(strings, String::compareToIgnoreCase);
		FArray.foreach(strings, System.out::println);
		
		//Constructor References
		List<String> labels = new ArrayList<>();
		labels.add("XXX"); labels.add("ZZZ");
		Stream<Button> stream = labels.stream().map(Button::new);
		List<Button> buttons = stream.collect(Collectors.toList());
		
		labels.stream().forEach(System.out::println);
		labels.forEach(System.out::println);
		
		
//		object::instanceMethod
//		Class::staticMethod == x -> System.out.println(x)
//		Class::instanceMethod == (x, y) -> x.compareToIgnoreCase(y)
		
	}
}

class FArray {
	public static void foreach(String[] elements, Foreachable foreach) {
		for (String element : elements) {
			foreach.apply(element);
		}
	}
}

@FunctionalInterface
interface Foreachable {
	void apply(Object o);
}
