package clinicaltrial;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public final class TrecConfig {	
	private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle("config");

    /* STORAGE - ELASTICSEARCH */

    public static final String ELASTIC_CT_INDEX = getString("ELASTIC_CT_INDEX");
    public static final String ELASTIC_CT_TYPE = getString("ELASTIC_CT_TYPE");
    
    public static final String ELASTIC_HOSTNAME = getString("ELASTIC_HOSTNAME");
    public static final int ELASTIC_PORT = getInteger("ELASTIC_PORT");
    public static final String ELASTIC_CLUSTER = getString("ELASTIC_CLUSTER");

	public static String getString(String key) {
		return PROPERTIES.getString(key);
	}

	public static int getInteger(String key) {
	    return Integer.parseInt(PROPERTIES.getString(key));
    }

}
