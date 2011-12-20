/**
 * 
 */
package de.sevendroids.java.startpoint;

/**
 * @author 7droids.de (FA)
 * 
 */
public interface NomintResult {

	String DEFAULT_RESULT = "UNA:+.? '"
			+ "UNB+UNOA:3+Absender:501+Receiver:502+111216:1622+11216162200000++++++0'"
			+ "UNH+1+ORDERS:D:07A:UN:EG4003'BGM+01G::321+NOMINT11216162200000+9'DTM+Z05:0:805'"
			+ "DTM+137:201112161522:203'RFF+CT:NORFF'NAD+ZSY+Absender::321'NAD+ZSO+Receiver::502'"
			+ "UNS+S'UNT+10+1'UNZ+1+11216162200000'";
	String CUSTOM_RESULT = "UNA:+.? '"
			+ "UNB+UNOA:3+Absender:501+Receiver:502+111216:1622+2161622000++++++0'"
			// missing -----------------------------------------^ 11 ----^ 00
			+ "UNH+1+ORDERS:D:07A:UN:EG4003'BGM+01G::321+NOMINT2161622000+9'DTM+Z05:0:805'"
			// missing ----------------------------------------^ 11 ----^ 00
			+ "DTM+137:201112161522:203'RFF+CT:NORFF'NAD+ZSY+Absender::321'NAD+ZSO+Receiver::502'"
			// missing -------------| 11 ----| 00 missing
			+ "UNS+S'UNT+10+1'UNZ+1+2161622000'";

}
