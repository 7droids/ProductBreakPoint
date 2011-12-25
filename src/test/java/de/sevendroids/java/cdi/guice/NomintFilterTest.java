package de.sevendroids.java.cdi.guice;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.inject.Guice;

import de.sevendroids.java.hook.NomintFilter;
import de.sevendroids.java.startpoint.NomintResult;
import de.sevendroids.java.startpoint.StringOutputStream;

//1 Define special runner for PowerMock
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

	@Test
	public void testDefaultFilter() throws Exception {
		// 3 Define return value for constructor
		whenNew(Date.class).withNoArguments().thenReturn(testDate);
		// 4 Here we ask for an instance of NomintFilter with the default
		// implementation
		NomintFilter filter = Guice.createInjector(new DefaultModule())
				.getInstance(NomintFilter.class);
		StringOutputStream stream = new StringOutputStream();
		filter.filter(
				new FileInputStream(File.createTempFile("DeleteMe", ".tmp")),
				stream);
		assertEquals(NomintResult.DEFAULT_RESULT, stream.toString());
	}

	@Test
	public void testCustomFilter() throws Exception {
		// 3 Define return value for constructor
		whenNew(Date.class).withNoArguments().thenReturn(testDate);
		// 4 Here we ask for an instance of NomintFilter with the default
		// implementation
		NomintFilter filter = Guice.createInjector(new CustomModule())
				.getInstance(NomintFilter.class);
		StringOutputStream stream = new StringOutputStream();
		filter.filter(
				new FileInputStream(File.createTempFile("DeleteMe", ".tmp")),
				stream);
		assertEquals(NomintResult.CUSTOM_RESULT, stream.toString());
	}
}
