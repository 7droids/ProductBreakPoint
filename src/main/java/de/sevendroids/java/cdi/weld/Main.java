/**
 * 
 */
package de.sevendroids.java.cdi.weld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jboss.weld.environment.se.Weld;

import de.sevendroids.java.startpoint.NomintResult;
import de.sevendroids.java.startpoint.StringOutputStream;

/**
 * @author 7droids.de (FA)<br>
 *         To switch between default and custom implementation the alternative
 *         area in beans.xml has to be commented/uncomment.
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		NomintFilter filter = new Weld().initialize().instance()
				.select(NomintFilter.class).get();
		StringOutputStream sos = new StringOutputStream();
		filter.filter(
				new FileInputStream(File.createTempFile("deleteMe", ".tmp")),
				sos);
		System.out.println("The "
				+ (sos.toString().length() == NomintResult.DEFAULT_RESULT
						.length() ? "default" : "custom") + " filter is used.");
	}
}
