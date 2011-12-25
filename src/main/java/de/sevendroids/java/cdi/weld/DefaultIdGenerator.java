/**
 * 
 */
package de.sevendroids.java.cdi.weld;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.inject.Default;

/**
 * @author 7driods (FA)<br>
 *         Default implementation for Id generation.
 * 
 */
@Default
public class DefaultIdGenerator implements IdGenerator {

	public String createUniqueId(Date date) {
		SimpleDateFormat sdfUnique = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdfUnique.format(date).substring(3);
	}
}
