package de.sevendroids.java.hook;

import java.util.Date;

/**
 * @author 7droids.de (FA) <br>
 *         This is a sample class for a custom implementation of a method. The
 *         class should be part of a different module and have the same name as
 *         the standard implementation. When adding the jars to the classpath in
 *         the correct order this class will be seen first from the classloader
 *         and therefore this implementation is used instead of the standard
 *         implementation.
 */
public class NomintFilter_Custom extends NomintFilter_basis {

	@Override
	protected String createUniqueId(Date now) {
		String orgUniqueId = super.createUniqueId(now);
		return orgUniqueId.substring(2, orgUniqueId.length() - 2);
	}
}
