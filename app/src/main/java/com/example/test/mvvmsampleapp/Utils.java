package com.example.test.mvvmsampleapp;

/**
 * Created by Vetchinin on 01.03.2018.
 */

public class Utils {
	public static boolean empty( final String s ) {
		// Null-safe, short-circuit evaluation.
		return s == null || s.trim().isEmpty();
	}
	
}