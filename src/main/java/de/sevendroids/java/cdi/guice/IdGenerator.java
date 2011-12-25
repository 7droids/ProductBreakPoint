package de.sevendroids.java.cdi.guice;

import java.util.Date;

/**
 * @author 7droids.de (FA)
 * 
 */
public interface IdGenerator {

	String createUniqueId(Date date);

}