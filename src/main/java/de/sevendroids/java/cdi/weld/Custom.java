/**
 * 
 */
package de.sevendroids.java.cdi.weld;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @author 7droids.de (FA)<br>
 *         Annotation to mark/annotate custom implementation
 * 
 */
@Qualifier
@Target({ FIELD, METHOD, PARAMETER, TYPE })
@Retention(RUNTIME)
public @interface Custom {
}