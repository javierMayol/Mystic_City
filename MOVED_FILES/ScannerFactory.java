/*
 * Author: Neel Patel
 * netID : npate315
 * UIN   : 674004711
 * 
 * this class provide the Scanner for keyboard input 
 * whenever we need for any class it provides
 * 
 */

//package proj1;

import java.util.Scanner;

public class ScannerFactory {

	private static Scanner keyboardScanner = null;    // initialize scanner to null
	
	public static Scanner getKeyboardScanner() {
		if(keyboardScanner == null);
			keyboardScanner = new Scanner(System.in);  // if it is null then get the new scanner
					
		return keyboardScanner;                        // return the scanner
	}
}
