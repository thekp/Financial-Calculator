package application;
	

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("InterestView.fxml"));
		primaryStage.setTitle("Financial Computing");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}
