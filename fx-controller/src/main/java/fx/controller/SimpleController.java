package fx.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import fx.config.Constants;

public class SimpleController<T extends Parent> extends JavaController<T> implements FxController<T> {
	public SimpleController() {
		super(null);
	}
	private final Logger log = LoggerFactory.getLogger(SimpleController.class);
	@Value("${locale.language}")
	private String language;
	@Value("${locale.country}")
	private String country;
	@Autowired
	private ResourceLoader loader;
	protected final Resource getResource(String url) {
		return loader.getResource(url);
	}
	@Override
	public void load(FxController<T> controller, String fxml) {
		if (!isLoaded()) {
			FXMLLoader loader;
			try {
				loader = new FXMLLoader(getResource(fxml).getURL());
				loader.setControllerFactory(new Callback<Class<?>, Object>() {
					@Override
					public Object call(Class<?> arg0) {
						return controller;
					}
				});
				loader.setResources(ResourceBundle.getBundle(Constants.LANG_BASE_NAME, new Locale(language, country)));
				setContent(loader.load());
			} catch (IOException e) {
				log.error("Error while show scene : {}", fxml, e);
			}
		}
	}
}