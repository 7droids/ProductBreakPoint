/**
 * 
 */
package de.sevendroids.java.cdi.guice;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 7driods (FA)<br>
 *         Default implementation for Id generation.
 * 
 */
public class DefaultIdGenerator implements IdGenerator {

	@Override
	public String createUniqueId(Date date) {
		SimpleDateFormat sdfUnique = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdfUnique.format(date).substring(3);
	}
}
