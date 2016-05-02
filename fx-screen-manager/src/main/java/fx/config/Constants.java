package fx.config;

public abstract class Constants {
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SYSTEM_ACCOUNT = "system";
	public static final String LANG_BASE_NAME = "i18n/Language";
	public static final String LOCALE = "LOCALE";
    private static final String FXML_DESIGN_PREFIX = "classpath:design/";
    private static final String FXML_DESIGN_SUFIX = ".fxml";
    public static final String getFXML(String fxml) {
    	StringBuilder sb = new StringBuilder(FXML_DESIGN_PREFIX);
    	sb.append(fxml).append(FXML_DESIGN_SUFIX);
    	return sb.toString();
    }
}