package de.sevendroids.java.startpoint;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 7droids.de (FA)
 *
 */
public class StringOutputStream extends OutputStream {
	private StringBuilder data = new StringBuilder(128);

	@Override
	public void write(int b) throws IOException {
		data.append((char) b);
	}

	@Override
	public String toString() {
		return data.toString();
	}
}