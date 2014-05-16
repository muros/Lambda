package com.comtrade.lambda.defaultmethods;

// Uporaba: V Collection libraries.
// Martin Oddersky je spisal collectionse za javo, hkrati pa je avtor Scale,
// ki je funkcijski jezik.
// Obsotje�e collection lib v javi nimajo forEach, kako ga dodati v Collection interface?
// �e spremeni� interface, kaj se zgodi z vso obstoje�o kodo - back.. compatibility papa.
// Default methods to the rescue.
// To so metode, ki predstavljajo konkretno implementacijo metode na interface. Tako, da niso nujne
// za implementacijo na vseh obstoje�ih implementacijah interfaca. (Ali so to Traits v Scali? Check!).
// Tako sedaj ni treba ve� za vsak interface delati �e abstrakten class,
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
 * Kaj �e je Person class, kako se zakomplicira inheritance?
 * Potem je pomemben samo super class, interface in njegove default metode
 * se ignorirajo.
 * To zagotavlja back.. comp. z Javo 7
 * Nikoli ne more� narediti default metode, ki bi povozila metodo na Object
 * classu (equals, toString) - class wins rule. �eprav bi bilo uporabno.
 */ 

// Problemi z multiple inheritance
interface Named {
	default String getName() {
		return getClass().getName() + "_" + hashCode();
	}
}

//class Student extends Person implements Named {
class Student implements Person, Named {

	// Ok, ta �e, kaj pa bo z getName(), kateri bo pravi
	// Person ali Named?
	// A?
	@Override
	public long getId() {
		return 0;
	}
	
	// Pa� je treba overridat
	// v vsakem prmeru, tudi, �e en od interfacejov nima default getName
	// implementacije. Lahko bi naredili tako, da �e ima samo en, da uporabi
	// tisto. Ampak niso hoteli komplicirati.
	// �e pa noben od interfacejov nima defaulta, je Student abstrac, ali pa mora
	// implementirati metodo, tako kot v preJava 8
	public String getName() {
		return Person.super.getName();
	}
}
