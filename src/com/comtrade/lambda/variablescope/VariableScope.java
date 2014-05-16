package com.comtrade.lambda.variablescope;

public class VariableScope {

	public static void main(String... args) {
		VariableScope vs = new VariableScope();
		
		// Closure
		repeatMessage("bla", 1000);
		repeatMessage("foo", 1000);
		
		// This
		vs.whoIsThis();
	}
	
	// Two free variables text in count, ki sta znotraj CLOSURE, ki jo sestavlja
	// metoda od lambde in te spremenljivki.
	// Inner classes v javi so bili tudi do sedaj closures.
	// To pomeni, da tudi, ko je klic repeateMessage že mimo in je kontekst poèišèen
	// imamo še vedno dostop do parametrov klica text in count.
	// Je pa restriction: free variable se ne smejo spreminjati. Predvsem za to ker bi bili
	// problemi z concurrency, ko bi veè threadov spreminjalo free var.
	public static void repeatMessage(String text, int count) {
		//int modal = 1;
		//int[] modal = new int[1]; // TRICK!
		Runnable r = () -> {
			for (int i = 0; i < count; i++) {
				//int count = 2; // scope issue
				System.out.println(text);
				//modal++; // tole ne bo šlo (compiler error)
				// Èe pa silimo naprej
				//modal[0]++;
				Thread.yield();
			}
		};
		new Thread(r).start();
	}
	
	// This ni od runner ampak od enclosing classa, ki je v tem primeru VariableScope.
	public void whoIsThis() {
		Runnable runner = () -> {
			System.out.println(this.toString());
		};
		runner.run();
	}
	
	@Override
	public String toString() {
		return "Hello, I am VariableScope not Runnable.";
	}
}
