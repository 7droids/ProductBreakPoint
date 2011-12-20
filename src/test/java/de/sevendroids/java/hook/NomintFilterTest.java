/**
 * 
 */
package de.sevendroids.java.hook;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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

	/**
	 * Test method for
	 * {@link de.sevendroids.java.hook.NomintFilter_basis#filter(InputStream, OutputStream)}
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testFilter() throws Exception {
		// Create a date for 16th of December
		Calendar cal = Calendar.getInstance();
		cal.set(2011, Calendar.DECEMBER, 16, 16, 22, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date lastDayInOctober = cal.getTime();
		// 3 Define return value for constructor
		whenNew(Date.class).withNoArguments().thenReturn(lastDayInOctober);
		NomintFilter filter = new NomintFilter();
		StringOutputStream sos = new StringOutputStream();
		filter.filter(
				new FileInputStream(File.createTempFile("deleteMe", ".tmp")),
				sos);
		assertEquals(NomintResult.DEFAULT_RESULT, sos.toString());
	}
}
