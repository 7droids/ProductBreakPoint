package de.sevendroids.java.startpoint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author 7droids.de (FA)
 * 
 */
public class NomintFilter {

	public void filter(InputStream is, OutputStream os) throws IOException {
		// Create BufferedReader and -Writer
		BufferedReader in = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os,
				"UTF-8"));

		try {
			// start filter
			Date now = new Date();
			NumberFormat nfParse = NumberFormat.getInstance();
			SimpleDateFormat sdfYMDHM = new SimpleDateFormat("yyyyMMddHHmm");
			sdfYMDHM.setTimeZone(TimeZone.getTimeZone("UTC"));
			SimpleDateFormat sdfUnique = new SimpleDateFormat(
					"yyyyMMddHHmmssSSS");
			SimpleDateFormat sdfYMD = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdfHM = new SimpleDateFormat("HHmm");
			String uniqueId = sdfUnique.format(now).substring(3);

			nfParse.setGroupingUsed(false);
			NumberFormat nfEdi = new DecimalFormat();
			nfEdi.setGroupingUsed(false);
			nfEdi.setMinimumIntegerDigits(1);
			nfEdi.setMinimumFractionDigits(0);
			nfEdi.setMaximumFractionDigits(0);

			// Header information
			out.write("UNA:+.? '");

			out.write("UNB+UNOA:3+Absender:501" + "+Receiver:502" + "+"
					+ sdfYMD.format(now) + ":" + sdfHM.format(now) + "+"
					+ uniqueId + "++++++0" + "'");
			out.write("UNH+1+ORDERS:D:07A:UN:EG4003'");
			out.write("BGM+01G::321+NOMINT" + uniqueId + "+9'");
			out.write("DTM+Z05:0:805'");
			out.write("DTM+137:" + sdfYMDHM.format(now) + ":203'");
			out.write("RFF+CT:NORFF'");
			out.write("NAD+ZSY+Absender::321'");
			out.write("NAD+ZSO+Receiver::502'");

			int segmentCnt = 8; // UNA and UNB don't count

			// Loop through LIN data omitted for simplicity
			out.write("UNS+S'");
			segmentCnt++;
			// Count UNT segment
			out.write("UNT+" + (segmentCnt + 1) + "+1'");
			out.write("UNZ+1+" + uniqueId + "'");

		} finally {
			// Close reader and writer
			in.close();
			out.close();
		}
	}
}
