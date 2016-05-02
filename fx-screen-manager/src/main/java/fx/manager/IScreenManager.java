package fx.manager;

import javafx.stage.Stage;
import fx.controller.Controller;

public interface IScreenManager {
	void setPrimaryStage(Stage stage);
	Stage getStage();
	void showMainScreen();
	void showLogin();
	Controller<?> getMainController();
}
