package fx.manager;

import javafx.stage.Stage;

public abstract class AbstractScreenManager implements IScreenManager {
    private Stage stage;
    public final void setPrimaryStage(Stage stage) {
    	this.stage = stage;
    }
    @Override
    public final Stage getStage() {
		return stage;
	}
	@Override
	public final void showMainScreen() {
		getMainController().show(getStage());
	}
	@Override
	public void showLogin() {
		showMainScreen();
	}
}
