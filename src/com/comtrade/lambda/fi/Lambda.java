package com.comtrade.lambda.fi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lambda {

	public static void main(String[] args) {
		Lambda ir = new Lambda();
		ir.go();
	}
	
	private void go() {
//		(String first, String second) -> Integer.compare(first.length(), second.length());
	    String[] strings = {"asadf", "asdf"};
	    
		Arrays.sort(strings, String::compareToIgnoreCase);
		
		Comparator<String> cmp = (a, b)->Integer.compare(a.length(), b.length());
		
		Runnable r = ()->System.out.println("run");
		Thread t = new Thread(r);
		t.start();
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(r);
		executor.execute(r);
		executor.execute(r);
		
		java.util.function.Function<Integer,Integer> f = (a)->a+1;
		System.out.println(f.apply(1));
		Integer[] ia = {1,2,3};
		
		Thread gt = new Thread(this::greet);
		gt.start();
		
		repeatMessageOld("Hello oldi", 50);
		System.out.println("repeateMessageOld call done");

		repeatMessage("Hello", 50);
		System.out.println("repeateMessage call done");
	}
	
	public void greet() {
		System.out.println("Greetings");
	}

	public static void repeatMessageOld(String text, int count) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < count; i++) {
					System.out.println(text);
					Thread.yield();
				}
			}
		};
		new Thread(r).start();
	}
	
	public static void repeatMessage(String text, int count) {
		Runnable r = () -> {
			for (int i = 0; i < count; i++) {
				System.out.println(text);
				Thread.yield();
			}
		};
		new Thread(r).start();
	}
}
