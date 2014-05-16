package com.comtrade.lambda.staticmethods;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

// Do sedaj so bile statiène metode na interfacih prepovedane, brez razloga,
// kar tako, ker bi to podiralo idejo interfaceja, kot pogodbe API vmesnika.
// Sedaj ni potrebno za interface delati še dodatnega razreda, ki nosi vse
// utility metode (kot Collection/Collections ali Path/Paths)
public class StaticMethods {

	public static void main(String... args) {
		StaticMethods sm = new StaticMethods();
		
		sm.go();
	}

	private void go() {
		Comparator comp = Comparator.comparing(Person::getName);
		
		// Zakaj ne kar na Path class-u
		Path paths = Paths.get("tra", "la", "la");
		// Collection bi lahko imel metodo, ne pa da mora biti še en class, ki ima
		// samo static metode (helpers/utils)
		Collection lista = Collections.emptyList();

	}
	
}

class Person {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
