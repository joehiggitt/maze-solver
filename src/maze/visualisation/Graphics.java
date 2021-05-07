package maze.visualisation;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

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
	* @param text the text content of the title
	* @param size the size of the title (used to differentiate application title or scene title)
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
	* @param text the text content of the element
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
	* @param text the text content of the button
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

	/**
	* Creates an ImageView element with the application dimensions
	* @param image the image to be displayed
	<!-- * @param size the size of the images -->
	* @return Returns a {@link javafx.scene.image.ImageView} object
	*/
	public static ImageView createGridImage(Image image, int size)
	{
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(0);
		imageView.setY(0);
		imageView.setFitHeight(size);
		imageView.setFitWidth(size);
		imageView.setPreserveRatio(true);
		return imageView;
	}

	/**
	* Reads in the images used in the maze/route visualisation
	* @return Returns an array of 8 images
	*/
	public static Image[] getImages()
	{
		Image[] images = new Image[8];
		try
		{
			images[0] = new Image(new FileInputStream("resources/images/PathSquare.png"));
		}
		catch (IOException e) {}
		finally {}

		try
		{
			images[1] = new Image(new FileInputStream("resources/images/PathSquareVisited.png"));
		}
		catch (IOException e) {}
		finally {}

		try
		{
			images[2] = new Image(new FileInputStream("resources/images/EntranceSquare.png"));
		}
		catch (IOException e) {}
		finally {}

		try
		{
			images[3] = new Image(new FileInputStream("resources/images/EntranceSquareVisited.png"));
		}
		catch (IOException e) {}
		finally {}

		try
		{
			images[4] = new Image(new FileInputStream("resources/images/ExitSquare.png"));
		}
		catch (IOException e) {}
		finally {}

		try
		{
			images[5] = new Image(new FileInputStream("resources/images/ExitSquareVisited.png"));
		}
		catch (IOException e) {}
		finally {}

		try
		{
			images[6] = new Image(new FileInputStream("resources/images/WallSquare.png"));
		}
		catch (IOException e) {}
		finally {}

		try
		{
			images[7] = new Image(new FileInputStream("resources/images/OuterWallSquare.png"));
		}
		catch (IOException e) {}
		finally {}

		return images;
	}
}