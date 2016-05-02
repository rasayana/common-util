package fx.controls;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.beans.property.StringProperty;

public class TitledBorder extends StackPane {
    private Label titleLabel = new Label();
    private StackPane contentPane = new StackPane();
    private Node content;
    public void setContent(Node content) {
    	getStyleClass().add("titled-address");
        content.getStyleClass().add("bordered-titled-content");
        contentPane.getChildren().add(content);
    }
    public Node getContent() {
        return content;
    }
    public void setTitle(String title) {
    	StringBuilder sb = new StringBuilder();
    	sb = sb.append(' ').append(title).append(' ');
    	titleProperty().set(sb.toString());
    }
    public String getTitle() {
        return titleProperty().get();
    }
    public StringProperty titleProperty() {
    	return titleLabel.textProperty();
    }
    public TitledBorder() {
    	setTitle("default title");
        titleLabel.getStyleClass().add("bordered-titled-title");
        StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
        getStyleClass().add("bordered-titled-border");
        getChildren().addAll(titleLabel, contentPane);
	}
}