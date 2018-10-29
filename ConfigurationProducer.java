package application;
/**
 * This class produces object readers(producers) based on passed parameters.
 *
 */
public class ConfigurationProducer {
	// Private variable to prohibit multiple instantiation
	private static ConfigurationProducer instance = new ConfigurationProducer();
	/**
	 * Gets the class instance: a Configuration Producer
	 * @return
	 */
	public static ConfigurationProducer getInstance() {
		return instance;
	}
	 /**
	  * Returns a reader based on the parameters given
	  * @param reader: A string value denoting the type of reader to return
	  * @return A reader based on the specified parameter
	  */
	public Configuration geConfiguration(String conf) {
		if(conf.equalsIgnoreCase("BALL")) {
			return new BallConfiguration();
		}
		else if(conf.equalsIgnoreCase("TABLE")) {
			return new TableConfiguration();
		} else if (conf.equalsIgnoreCase("POCKETS")) {
			return new PocketsConfiguration();
		}

		
		return null;
	}
}
