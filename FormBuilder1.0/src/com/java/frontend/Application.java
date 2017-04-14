package com.java.frontend;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.java.form.Form;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
//**********************

public class Application {
	WebEngine engine;

	public Application(Form form) {
		WebView formView;

		BorderPane layout = new BorderPane();
		Stage primaryStage = new Stage();
		// Top Layout that holds the ToolBar
		VBox topContainer = new VBox();
		ToolBar topToolBar = new ToolBar();
		topContainer.getChildren().add(topToolBar);
		layout.setBottom(topContainer);
		StackPane centerContainer = new StackPane();
		Button submit = new Button("Submit");
		// ToolBar
		topToolBar.getItems().add(submit);
		formView = new WebView();
		centerContainer.getChildren().add(submit);
		engine = formView.getEngine();
		engine.loadContent(form.preview());

		centerContainer.getChildren().add(formView);
		layout.setCenter(centerContainer);
		primaryStage.setTitle("Application");
		Scene scene = new Scene(layout, 700, 700);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(Login.class.getResource("view.css").toExternalForm());
		primaryStage.show();
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				append();
				engine.executeScript("document.getElementById('ding').innerHTML=document.getElementById('content').value;");
			}
		});

	}

	private void append() {
		Document doc = engine.getDocument();
		Element el = doc.getElementById("ding");
		String s = el.getTextContent();
		System.out.println(s);
	}
}
