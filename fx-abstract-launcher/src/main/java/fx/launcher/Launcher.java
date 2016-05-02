package fx.launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import fx.launcher.preloader.Preloader;
import fx.manager.IScreenManager;

@Lazy
public abstract class Launcher extends Application {
	private static String[] args;
	private ConfigurableApplicationContext applicationContext;
	@Autowired
	private IScreenManager screenManager;
	@SuppressWarnings("static-access")
	@Override
	public void init() throws Exception {
		super.init();
		SpringApplication app = new SpringApplication(getClass());
		app.setShowBanner(false);
		applicationContext = app.run(getClass(), args);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
	}
	@Value("${app.ui.title:Example}")
    private String windowTitle;
    @Override
    public void start(Stage stage) throws Exception {
    	notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
        stage.setTitle(windowTitle);
		stage.setResizable(true);
		//stage.setFullScreen(true);
		stage.centerOnScreen();
		screenManager.setPrimaryStage(stage);
		screenManager.showLogin();
    }
	@Override
	public void stop() throws Exception {
		super.stop();
		applicationContext.close();
	}
	protected static void launchApp(Class<? extends Launcher> appClass, String[] args) {
		Launcher.args = args;
		Application.launch(appClass, args);
	}
}