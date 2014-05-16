package com.comtrade.lambda.fi;

import java.util.concurrent.Callable;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
 * Functional Interfaces
 * 
 * To je bistvo lambde v Javi.
 * Java �e vedno temelji na interfaces in nima spremenljivk, ki bi bile
 * tipa funkcijske spremenljivke.
 * V byte codi java naredi objekt, ki implementira interface z eno metodo in jo pokli�e.
 * Klici morajo �e vedno usetrezati Interface pogodbi, ker druga�e lahko po�lje�
 * katerokoli funkcijo.
 * 
 * @author muros
 *
 */
public class FunctionalInterfaces {

	public static void main(String[] args) {
		FunctionalInterfaces fi = new FunctionalInterfaces();
		fi.go();
	}
	
	private void go() {
		// Klasi�ni pristop
		Adder adder = new Adder();
		Integer r = adder.calc(1, 2);
		System.out.println("1 + 2 = " + r);
		
		// Anonymous inner class
		MyFuncInterface mfi = new MyFuncInterface() {
			
			@Override
			public Integer calc(Integer a, Integer b) {
				return a + b;
			}
		};
		
		// Funkcijsko
		MyFuncInterface fAdd = (Integer a, Integer b) -> a + b;
		Integer fr = fAdd.calc(1, 2);
		System.out.println("1 + 2 = " + fr);
		
		// �e bolj funkcijsko / inferred type ker vemo, da pri�akuje Integer
		MyFuncInterface fAdd2 = (a, b) -> a + b;
		Integer fr2 = fAdd.calc(1, 2);
		System.out.println("1 + 2 = " + fr2);
		
		// �e primer, ko je tip parametra parametriziran 
		MyFuncInterfaceWithType<Integer> fAddInt = (a, b) -> a + b;
		Integer frT = fAdd.calc(1, 2);
		System.out.println("1 + 2 = " + frT);
		// Sedaj pa �e s stringi
		MyFuncInterfaceWithType<String> fAddStr = (a, b) -> a + b;
		String frS = fAddStr.calc("1", "2");
		System.out.println("1 + 2 = " + frS);
		
		// Fu.... awsom!!!
		
		// Tako se lotimo FI sami
		// Ima pa Java java.util.function, ki dolo�a cel kup tipov, ki se nato uporabljajo
		// v knji�nicah, ali pa jih uporabimo �e mi.
		
		// Poleg tega je �e ve�ina interfacov iz java < 8 tudi funkcijskih, kot na primer
		// Callable ali Runnable...
		Runnable runnable = () -> System.out.println("Hello from runnable");
		runnable.run();
		// If exceptions are thorwn, callable must be used and null returned.
		Callable<Void> callable = () -> {
			System.out.println("Hello from callable");
			return null;
		};
		try {
			callable.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

// So Eighties (samo �e disko kugla je starej�a)
class Adder implements MyFuncInterface {

	@Override
	public Integer calc(Integer a, Integer b) {
		return a + b;
	}
	
}
