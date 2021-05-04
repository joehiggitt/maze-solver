package maze.visualisation;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

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
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Arc;
// import javafx.scene.shape.ArcType;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.Text;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.shape.Polygon;

/**
* Class that generates the graphics in the MazeSovler Pro application so that there is consistency.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class Graphics
{
	/**
	* Creates a vertical box element with the application styling
	* @return Returns a {@link javafx.scene.layout.VBox} object
	*/
	public static VBox createVBox()
	{
		VBox box = new VBox(5);
		box.setAlignment(Pos.CENTER);
		box.setBackground(new Background(new BackgroundFill(Color.web("#F08D86"), CornerRadii.EMPTY, Insets.EMPTY)));
		return box;
	}

	/**
	* Creates a horizontal box element with the application styling
	* @return Returns a {@link javafx.scene.layout.HBox} object
	*/
	public static HBox createHBox()
	{
		HBox box = new HBox(5);
		box.setAlignment(Pos.CENTER);
		box.setBackground(new Background(new BackgroundFill(Color.web("#F08D86"), CornerRadii.EMPTY, Insets.EMPTY)));
		return box;
	}

	/**
	* Creates a title element with the application styling
	* @return Returns a {@link javafx.scene.control.Label} object
	*/
	public static Label createTitle(String text, int size)
	{
		Label title = new Label(text);
		title.setFont(Font.font("Chilanka", size));
		title.setTextFill(Color.web("#001433"));
		return title;
	}

	/**
	* Creates a text element with the application styling
	* @return Returns a {@link javafx.scene.control.Label} object
	*/
	public static Label createText(String text)
	{
		Label label = new Label(text);
		label.setFont(Font.font("Manjari", 20));
		label.setTextFill(Color.web("#001433"));
		return label;
	}

	/**
	* Creates a button element with the application styling
	* @return Returns a {@link javafx.scene.control.Button} object
	*/
	public static Button createButton(String text)
	{
		Button button = new Button(text);
		button.setFont(Font.font("Manjari", 20));
		button.setTextFill(Color.web("#001433"));
		button.setStyle("-fx-background-color: #ADD9B9; -fx-text-fill: #001433; -fx-border-width: 0;");
		return button;
	}

	/**
	* Creates a text box element with the application styling
	* @return Returns a {@link javafx.scene.control.TextArea} object
	*/
	public static TextArea createTextArea()
	{
		TextArea textArea = new TextArea();
		textArea.setFont(Font.font("Bitstream Vera Sans Mono"));
		textArea.setPrefHeight(150);
		textArea.setMaxHeight(600);
		textArea.setPrefWidth(200);
		textArea.setMaxWidth(800);
		return textArea;
	}
}