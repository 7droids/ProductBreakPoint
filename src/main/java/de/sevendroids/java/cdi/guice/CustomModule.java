/**
 * 
 */
package de.sevendroids.java.cdi.guice;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * @author 7droids.de (FA)<br>
 *         Configuration file for custom implementation
 * 
 */
public class CustomModule implements Module {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.Module#configure(com.google.inject.Binder)
	 */
	@Override
	public void configure(Binder binder) {
		binder.bind(IdGenerator.class).to(CustomIdGenerator.class);
	}
}
