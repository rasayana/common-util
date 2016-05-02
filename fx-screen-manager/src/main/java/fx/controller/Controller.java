package fx.controller;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface Controller<T extends Parent> extends IContentHolder<T> {
	void prepareStage(Stage primaryStage);
	void showStage(Stage primaryStage);
	void show(Stage primaryStage);
	void show(Stage primaryStage, boolean fullscreen);
}