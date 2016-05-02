package fx.controls;

import fx.util.FXUtils;
import java.io.InputStream;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

public class ImagePane extends Pane {
	public void setBackground(InputStream stream) {
		FXUtils.setBackground(this, null, stream);
	}
	public void setBGURL(String imageLoc, double prefWidth, double prefHeight) {
    	setPrefSize(prefWidth, prefHeight);
    	setBGURL(imageLoc);
    }
	// size an image by placing it in a pane.
	public void setBGURL(String imageLoc) {
		setBGURL(imageLoc, "-fx-background-size: contain; -fx-background-repeat: no-repeat;");
	}
	// size an image by placing it in a pane.
	public void setBGURL(String imageLoc, String style) {
		setBGURL(new SimpleStringProperty(imageLoc), new SimpleStringProperty(style));
	}
	// size a replacable image in a pane and add a replaceable style.
	public void setBGURL(StringProperty imageLocProperty, StringProperty styleProperty) {
		styleProperty().bind(
		    new SimpleStringProperty("-fx-background-image: url(\"")
					.concat(imageLocProperty)
					.concat(new SimpleStringProperty("\");"))
			        .concat(styleProperty)
		);
	}
}