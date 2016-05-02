package fx.util;

import java.io.InputStream;
import javafx.scene.layout.Pane;

public class AbstractMapPane extends Pane {
	public void setBackgroundURL(String url, InputStream stream) {
		setBackground(FXUtils.getBackground(url, stream));
	}
}