package fx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import fx.manager.IScreenManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
//import java.util.Timer;
//import java.util.TimerTask;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaController<T extends Parent> extends ContentHolder<T> implements Controller<T> {
	public JavaController(T content) {
		super(content);
	}
	@Autowired
	private IScreenManager screenManager;
	protected IScreenManager getScreenManager() {
		return screenManager;
	}
	@Override
	public void prepareStage(Stage primaryStage) {
		Scene scene = new Scene(getContent());
	//	scene.setRoot(value);
		scene.getStylesheets().add("/css/JMetroLightTheme.css");
		primaryStage.setScene(scene);
/*
		final ChangeListener<Number> listener = new ChangeListener<Number>() {
			final Timer timer = new Timer(); // uses a timer to call your resize method
			TimerTask task = null; // task to execute after defined delay
			final long delayTime = 200; // delay that has to pass in order to consider an operation done
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) {
				if (task != null) // there was already a task scheduled from the previous operation ...
					task.cancel(); // cancel it, we have a new size to consider
				task = new TimerTask() { // create new task that calls your resize operation
					@Override
					public void run() { // here you can place your resize code
						System.out.println("resize to " + primaryStage.getWidth() + " " + primaryStage.getHeight());
					}
				};
				// schedule new task
				timer.schedule(task, delayTime);
			}
		};
		primaryStage.widthProperty().addListener(listener);
		primaryStage.heightProperty().addListener(listener);
*/
		init();
	}
//	@Autowired
//	private ConfigService configService;
	private void init() {
//		Locale locale = new Locale(configService.findByName(Constants.LOCALE));
//		CacheUtil.putCache(Constants.LOCALE, locale);
	}
	@FXML
	public void showMainScreen() {
		getScreenManager().showMainScreen();
	}
	@Override
	public void showStage(Stage primaryStage) {
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
	}
	@Override
	public void show(Stage primaryStage, boolean fullscreen) {
		prepareStage(primaryStage);
		showStage(primaryStage);
		if (fullscreen)
			primaryStage.setFullScreen(fullscreen);
	}
	@Override
	public void show(Stage primaryStage) {
		show(primaryStage, false);
	}
}