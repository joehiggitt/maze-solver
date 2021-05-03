package maze.visualisation;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

// import javafx.scene.Group;
// import javafx.scene.Scene;
// import javafx.scene.layout.HBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Arc;
// import javafx.scene.shape.ArcType;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.Text;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.shape.Polygon;

public class Graphics
{
	public static VBox createVBox()
	{
		VBox box = new VBox(5);
		box.setAlignment(Pos.CENTER);
		box.setBackground(new Background(new BackgroundFill(Color.web("#F08D86"), CornerRadii.EMPTY, Insets.EMPTY)));
		return box;
	}

	public static Label createTitle(String text, int size)
	{
		Label title = new Label(text);
		title.setFont(Font.font("Chilanka", size));
		title.setTextFill(Color.web("#001433"));
		return title;
	}

	public static Label createText(String text)
	{
		Label label = new Label(text);
		label.setFont(Font.font("Manjari", 20));
		label.setTextFill(Color.web("#001433"));
		return label;
	}

	public static Button createButton(String text)
	{
		Button button = new Button(text);
		button.setFont(Font.font("Manjari", 20));
		button.setTextFill(Color.web("#001433"));
		button.setStyle("-fx-background-color: #ADD9B9; -fx-text-fill: #001433; -fx-border-width: 0;");
		return button;
	}
}