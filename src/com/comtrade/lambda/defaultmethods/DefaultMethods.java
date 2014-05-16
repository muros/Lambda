package com.comtrade.lambda.defaultmethods;

// Uporaba: V Collection libraries.
// Martin Oddersky je spisal collectionse za javo, hkrati pa je avtor Scale,
// ki je funkcijski jezik.
// Obsotjeèe collection lib v javi nimajo forEach, kako ga dodati v Collection interface?
// Èe spremeniš interface, kaj se zgodi z vso obstojeèo kodo - back.. compatibility papa.
// Default methods to the rescue.
// To so metode, ki predstavljajo konkretno implementacijo metode na interface. Tako, da niso nujne
// za implementacijo na vseh obstojeèih implementacijah interfaca. (Ali so to Traits v Scali? Check!).
// Tako sedaj ni treba veè za vsak interface delati še abstrakten class,
// ki implementiravse metode / defaulte (sedaj vemo od kje ime).

public class DefaultMethods {
	
	public static void main(String... args) {
		DefaultMethods dm =  new DefaultMethods();
		dm.go();
	}

	private void go() {
		Student s = new Student();
		System.out.println(s.getName());
	}
	
}

interface Person {
	long getId();

	default String getName() {
		return "John Doe";
	}
}
/* 
 * Kaj èe je Person class, kako se zakomplicira inheritance?
 * Potem je pomemben samo super class, interface in njegove default metode
 * se ignorirajo.
 * To zagotavlja back.. comp. z Javo 7
 * Nikoli ne moreš narediti default metode, ki bi povozila metodo na Object
 * classu (equals, toString) - class wins rule. Èeprav bi bilo uporabno.
 */ 

// Problemi z multiple inheritance
interface Named {
	default String getName() {
		return getClass().getName() + "_" + hashCode();
	}
}

//class Student extends Person implements Named {
class Student implements Person, Named {

	// Ok, ta že, kaj pa bo z getName(), kateri bo pravi
	// Person ali Named?
	// A?
	@Override
	public long getId() {
		return 0;
	}
	
	// Paè je treba overridat
	// v vsakem prmeru, tudi, èe en od interfacejov nima default getName
	// implementacije. Lahko bi naredili tako, da èe ima samo en, da uporabi
	// tisto. Ampak niso hoteli komplicirati.
	// Èe pa noben od interfacejov nima defaulta, je Student abstrac, ali pa mora
	// implementirati metodo, tako kot v preJava 8
	public String getName() {
		return Person.super.getName();
	}
}
