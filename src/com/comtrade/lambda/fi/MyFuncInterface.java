package com.comtrade.lambda.fi;

@FunctionalInterface
public interface MyFuncInterface {
	// Mora imeti samo eno abstraktno metodo, da velja za FunctionalInterface
	// public abstract lahko izpustimo, ker je implicitno, ker je interface
	Integer calc(Integer a, Integer b);
//	public abstract String addS(String a, String b);
}
