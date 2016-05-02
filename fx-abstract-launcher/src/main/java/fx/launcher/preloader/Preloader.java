package fx.launcher.preloader;

import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings("restriction")
public class Preloader extends javafx.application.Preloader {
	private Stage stage;
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		Scene scene = new Scene(new ProgressIndicator(-1), 100, 100);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}
	@Override
	public void handleApplicationNotification(PreloaderNotification pn) {
		if (pn instanceof StateChangeNotification) {
			stage.hide();
		}
	}
}