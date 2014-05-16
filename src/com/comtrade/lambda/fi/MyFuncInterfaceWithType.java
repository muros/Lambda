package com.comtrade.lambda.fi;

@FunctionalInterface
public interface MyFuncInterfaceWithType<T> {
	// Kako bodo pa delali inferred tipi? Bodo, seveda.
	T calc(T a, T b);
}
