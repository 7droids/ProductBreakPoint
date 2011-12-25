/**
 * 
 */
package de.sevendroids.java.cdi.guice;

import java.util.Date;

/**
 * @author 7droids.de (FA) <br>
 *         Custom implementation for Id generation.
 * 
 */
public class CustomIdGenerator extends DefaultIdGenerator {

	@Override
	public String createUniqueId(Date date) {
		String id = super.createUniqueId(date);
		return id.substring(2, id.length() - 2);
	}
}
