/**
 * 
 */
package de.sevendroids.java.cdi.weld;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.util.AnnotationLiteral;

import org.jboss.weld.environment.se.Weld;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.sevendroids.java.hook.NomintFilter;
import de.sevendroids.java.startpoint.NomintResult;
import de.sevendroids.java.startpoint.StringOutputStream;

/**
 * @author 7droids.de (FA)
 * 
 */
// 1 Define special runner for PowerMock
@RunWith(PowerMockRunner.class)
// 2 Define class to test
@PrepareForTest(NomintFilter.class)
public class NomintFilterTest {

	private static final Date testDate;

	static {
		// Create a date for 16th of December
		Calendar cal = Calendar.getInstance();
		cal.set(2011, Calendar.DECEMBER, 16, 16, 22, 0);
		cal.set(Calendar.MILLISECOND, 0);
		testDate = cal.getTime();
	}

	/**
	 * Test method for
	 * {@link de.sevendroids.java.cdi.weld.NomintFilter#filter(InputStream, OutputStream)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFilterWithDefaultImplementation() throws Exception {
		// 3 Define return value for constructor
		whenNew(Date.class).withNoArguments().thenReturn(testDate);
		// 4 Here we ask for an instance of NomintFilter with the default
		// implementation
		NomintFilter filter = new Weld().initialize().instance()
				.select(NomintFilter.class).get();
		StringOutputStream sos = new StringOutputStream();
		filter.filter(
				new FileInputStream(File.createTempFile("deleteMe", ".tmp")),
				sos);
		assertEquals(NomintResult.DEFAULT_RESULT, sos.toString());
	}

	@Test
	public void testDefaultImplementation() {
		IdGenerator generator = new Weld().initialize().instance()
				.select(IdGenerator.class).get();
		assertEquals("11216162200000", generator.createUniqueId(testDate));
	}

	@Test
	@Ignore
	public void testCustomImplementation() {
		IdGenerator generator = new Weld().initialize().instance()
				.select(IdGenerator.class, new AnnotationLiteral<Custom>() {
				}).get();
		assertEquals("2161622000", generator.createUniqueId(testDate));
	}
}
