/**
 * 
 */
package de.sevendroids.java.cdi.weld;

import java.util.Date;

import javax.enterprise.inject.Alternative;

/**
 * @author 7droids.de (FA) <br>
 *         Custom implementation for Id generation.
 * 
 */
@Alternative
public class CustomIdGenerator extends DefaultIdGenerator {

	@Override
	public String createUniqueId(Date date) {
		String id = super.createUniqueId(date);
		return id.substring(2, id.length() - 2);
	}
}
