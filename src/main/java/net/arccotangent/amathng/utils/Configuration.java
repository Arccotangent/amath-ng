package net.arccotangent.amathng.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Configuration {

	private static final String PATH = System.getProperty("user.home") + "/amath-ng.conf";
	private static final long DEFAULT_PRECISION = 200;
	private static final int DEFAULT_CERTAINTY = 100;
	
	/**
	 * Create a new configuration file if it doesn't already exist. If it exists, this function does nothing.
	 */
	public static void createConfiguration() {
		File c = new File(PATH);
		if (!c.exists()) {
			try {
				c.createNewFile();
				JSONObject conf = new JSONObject();
				conf.put("precision", DEFAULT_PRECISION);
				conf.put("certainty", DEFAULT_CERTAINTY);
				FileWriter fw = new FileWriter(c);
				fw.write(conf.toString(4));
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Read the precision setting from the configuration file.
	 * @return The precision in significant figures. In the case of an error, the default precision is returned.
	 */
	public static long getPrecision() {
		try {
			byte[] conf = Files.readAllBytes(new File(PATH).toPath());
			String c = new String(conf);
			JSONObject n = new JSONObject(c);
			return n.getLong("precision");
		} catch (IOException e) {
			return DEFAULT_PRECISION;
		}
	}
	
	/**
	 * Read the certainty setting from the configuration file.
	 * @return The certainty of the primality test. In the case of an error, the default certainty is returned.
	 */
	public static int getCertainty() {
		try {
			byte[] conf = Files.readAllBytes(new File(PATH).toPath());
			String c = new String(conf);
			JSONObject n = new JSONObject(c);
			return n.getInt("certainty");
		} catch (IOException e) {
			return DEFAULT_CERTAINTY;
		}
	}

}
