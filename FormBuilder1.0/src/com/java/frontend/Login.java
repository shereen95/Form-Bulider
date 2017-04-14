package com.java.frontend;

import com.java.backend.User;
import com.java.backend.Validation;
import com.java.database.MysqlConnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

	private MysqlConnection mysqlConnection = new MysqlConnection();
	private Validation validate = new Validation();
	Stage window;
	Scene scene1, scene2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		primaryStage.setTitle("Welcome to Form Builder!");
		/////////////////////////////////// >>scene 1 <<
		/////////////////////////////////// ///////////////////////////////////////////////////
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("    Welcome");
		grid.add(scenetitle, 0, 0, 2, 1);

		Label email = new Label("Email:");
		grid.add(email, 0, 2);

		TextField userTextField = new TextField();
		userTextField.setId("textField");
		grid.add(userTextField, 1, 2);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 3);

		PasswordField pwBox = new PasswordField();
		pwBox.setId("textField");
		grid.add(pwBox, 1, 3);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

//				if (userTextField.getText() != null && pwBox.getText() != null) {
//					if (mysqlConnection.login(userTextField.getText(), pwBox.getText()))
						new View();
					primaryStage.close();
				}

				
			
		});

		Button btn2 = new Button("Sign up");
		HBox hbBtn2 = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(btn2);
		grid.add(hbBtn2, 1, 4);
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				window.setScene(scene2);
			}
		});
		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		scenetitle.setId("welcome-text");
		actiontarget.setId("actiontarget");
		scene1 = new Scene(grid, 300, 303);
		window.setScene(scene1);
		scene1.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
		window.show();

		/////////////////////////////////// >>scene 2 <<
		/////////////////////////////////// ///////////////////////////////////////////////////

		GridPane grid1 = new GridPane();
		grid1.setAlignment(Pos.CENTER);
		grid1.setHgap(20);
		grid1.setVgap(20);
		grid1.setPadding(new Insets(25, 25, 25, 25));

		Label first_name = new Label("First name:");
		grid1.add(first_name, 0, 1);

		Label last_name = new Label("Last name:");
		grid1.add(last_name, 0, 2);

		Label email2 = new Label("Email:");
		grid1.add(email2, 0, 3);

		Label password = new Label("Password:");
		grid1.add(password, 0, 4);

		Label conPassword = new Label("Confirm password:");
		grid1.add(conPassword, 0, 5);

		TextField firstNameField = new TextField();
		firstNameField.setId("textField");
		grid1.add(firstNameField, 1, 1);

		TextField lastNameField = new TextField();
		lastNameField.setId("textField");
		grid1.add(lastNameField, 1, 2);

		TextField emailField = new TextField();
		emailField.setId("textField");
		grid1.add(emailField, 1, 3);

		PasswordField passField = new PasswordField();
		passField.setId("textField");
		grid1.add(passField, 1, 4);

		PasswordField conPassField = new PasswordField();
		conPassField.setId("textField");
		grid1.add(conPassField, 1, 5);

		Button button = new Button("Sign up");
		HBox hbBtn3 = new HBox(10);
		hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn3.getChildren().add(button);
		grid1.add(hbBtn3, 1, 6);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!validate.ValidateName(firstNameField.getText())) {
					alert.setContentText("FirstName is not Vaild");
					alert.showAndWait();
				} else if (!validate.ValidateName(lastNameField.getText())) {
					alert.setContentText("LastName is not Vaild");
					alert.showAndWait();
				} else if (!validate.ValidateEmail(emailField.getText())) {
					alert.setContentText("Please enter  your email address in the format someone@example.com");
					alert.showAndWait();
				} else if (!validate.ValidatePassword(passField.getText())) {
					alert.setContentText("password is not Vaild");
					alert.showAndWait();
				} else if (!validate.ValidateConfirmPassword(passField.getText(), conPassField.getText())) {
					alert.setContentText("password is not Equal");
					alert.showAndWait();
				} else {
					// save record in db
					// new user in data base
					User newUser = new User(firstNameField.getText(), lastNameField.getText(), emailField.getText(),
							passField.getText());
					String sql = "INSERT INTO Users(first_name,last_name,email,password) " + "VALUES ( '"
							+ newUser.getFirstName() + "','" + newUser.getLastName() + "','" + newUser.getUserMail()
							+ "','" + newUser.getUserPassword() + "')";
					mysqlConnection.addNewRecord(sql);
					window.setScene(scene1);
				}
			}
		});

		final Text actiontarget2 = new Text();
		grid1.add(actiontarget2, 1, 6);

		actiontarget.setId("actiontarget");

		scene2 = new Scene(grid1, 450, 426);

		scene2.getStylesheets().add(Login.class.getResource("signUp.css").toExternalForm());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
