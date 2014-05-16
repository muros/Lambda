package com.comtrade.lambda.fi;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class LambdaOne {

	public static void main(String... args) {
		LambdaOne l = new LambdaOne();
		
		l.go();
	}
	
	private void go() {
		String[] strings = {"asdf", "sd"};
		for (String string : strings) {
			System.out.println(string);
		}
//		Arrays.sort(strings, new LengthComparator());
		Arrays.sort(strings, (String a, String b)->Integer.compare(a.length(), b.length()));
		
		Comparator<String> comp = (first, second)
				-> Integer.compare(first.length(), second.length());
		Comparator<String> comp2 = (first, second)
						-> {if(first.length() < second.length()) return -1;
								else if(first.length() > second.length()) return 1; 
								else return 0;};
		Arrays.sort(strings, comp);
		
		for (String string : strings) {
		    System.out.println(string);
		}
		
	}
}

class LengthComparator implements Comparator<String> {
    public int compare(String first, String second) {
       return Integer.compare(first.length(), second.length());
    }
 }