package net.arccotangent.amathng;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Configuration {

	public static final String PATH = System.getProperty("user.home") + "/amath-ng.conf";
	public static final long DEFAULT_PRECISION = 200;
	public static final int DEFAULT_CERTAINTY = 100;

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
