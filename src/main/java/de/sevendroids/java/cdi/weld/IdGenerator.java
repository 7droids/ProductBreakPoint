package de.sevendroids.java.cdi.weld;

import java.util.Date;

import javax.inject.Singleton;

/**
 * @author 7droids.de (FA)
 * 
 */
@Singleton
public interface IdGenerator {

	String createUniqueId(Date date);

}