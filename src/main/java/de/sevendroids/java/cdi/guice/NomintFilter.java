package de.sevendroids.java.cdi.guice;

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

import javax.inject.Inject;

/**
 * @author 7droids.de (FA)
 * 
 */
public class NomintFilter {
	
	@Inject
	private IdGenerator generator;
	private NumberFormat nfParse = NumberFormat.getInstance();
	private SimpleDateFormat sdfYMDHM = new SimpleDateFormat("yyyyMMddHHmm");

	public NomintFilter() {
		sdfYMDHM.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public void filter(InputStream is, OutputStream os) throws IOException {
		// BufferedReader und -Writer anlegen
		BufferedReader in = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os,
				"UTF-8"));

		try {
			// Filter starten
			Date now = new Date();
			SimpleDateFormat sdfYMD = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdfHM = new SimpleDateFormat("HHmm");
			String uniqueId = generator.createUniqueId(now);

			nfParse.setGroupingUsed(false);
			NumberFormat nfEdi = new DecimalFormat();
			nfEdi.setGroupingUsed(false);
			nfEdi.setMinimumIntegerDigits(1);
			nfEdi.setMinimumFractionDigits(0);
			nfEdi.setMaximumFractionDigits(0);

			// Header-Informationen
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

			int segmentCnt = 8; // UNA und UNB zählen hier nicht dazu

			// Schleife über die LIN-Daten wird zur Vereinfachung weggelassen
			out.write("UNS+S'");
			segmentCnt++;
			// Das UNT zählt mit
			out.write("UNT+" + (segmentCnt + 1) + "+1'");
			out.write("UNZ+1+" + uniqueId + "'");

		} finally {
			// Reader und Writer wieder zumachen
			in.close();
			out.close();
		}
	}

	protected String createUniqueId(Date now) {
		SimpleDateFormat sdfUnique = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdfUnique.format(now).substring(3);
	}
}
