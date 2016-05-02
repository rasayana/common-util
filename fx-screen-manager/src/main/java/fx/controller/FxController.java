package fx.controller;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface FxController<T extends Parent> extends Controller<T> {
	void load(FxController<T> controller, String fxml);
	void prepareStage(Stage primaryStage);
}
