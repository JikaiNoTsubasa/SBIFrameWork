package fr.triedge.fwk.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	public static final String CONF_LOCATION				= "conf/dam.properties";
	public static Properties params = new Properties();
	
	public static void save() throws FileNotFoundException, IOException {
		params.store(new FileOutputStream(new File(CONF_LOCATION)), "Stored by DAM");
	}
	
	public static void load() throws FileNotFoundException, IOException {
		params.load(new FileInputStream(new File(CONF_LOCATION)));
	}
}
