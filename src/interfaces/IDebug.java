package interfaces;

import java.util.*;

public interface IDebug {
	/**
	 * println a String
	 * @param string String to be printed
	 */
	public static void pl(Object object) {
		p(object);
		System.out.println();
	}
	
	/**
	 * println a String
	 * @param string String to be printed
	 */
	public static void p(Object object) {
		if(object.getClass().isArray()) {
			ArrayException.Error();
		}
		else {
			System.out.print("*** " + object);
		}
	}
}
