package com.java.properties;

import javafx.scene.control.*;
import com.java.field.Field;
import com.java.form.Form;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public abstract class Properties {
	private Label label;
	private TextField textField;
	@SuppressWarnings("rawtypes")
	private ChoiceBox choiceBox;

	@SuppressWarnings("static-access")
	public void addLabel(GridPane grid, int row, Field field, Form form) {
		label = new Label("Label:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextField labelTextField = new TextField();
		labelTextField.setOnKeyReleased(event -> {
			field.setLabel(labelTextField.getText());
			field.updateField(form);
		});
		GridPane.setConstraints(labelTextField, 2, row, 4, 1);
		grid.getChildren().addAll(label, labelTextField);
	}

	@SuppressWarnings("static-access")
	public void addPlainText(GridPane grid, int row, Field field, Form form) {
		label = new Label("Text:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextArea plainTextArea = new TextArea();
		plainTextArea.setPrefWidth(80);
		plainTextArea.setPrefHeight(50);
		plainTextArea.setOnKeyReleased(event -> {
			field.setPlainText(plainTextArea.getText());
			field.updateField(form);
		});

		GridPane.setConstraints(plainTextArea, 2, row, 4, 1);
		grid.getChildren().addAll(label, plainTextArea);
	}

	@SuppressWarnings("static-access")
	public void addPaddingTop(GridPane grid, int row, Field field, Form form) {
		label = new Label("Padding:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextField top = new TextField();
		top.setId("text");
		top.setPrefWidth(40);
		top.setOnKeyReleased(event -> {
			field.setPaddingLeft(top.getText());
			field.updateField(form);
		});
		GridPane.setConstraints(top, 2, row);
		grid.getChildren().addAll(label, top);
	}

	public void addPaddingRight(GridPane grid, int row, Field field, Form form) {
		TextField right = new TextField();
		right.setId("text");
		right.setPrefWidth(40);
		right.setOnKeyReleased(event -> {
			field.setPaddingRight(right.getText());
			field.updateField(form);
		});
		GridPane.setConstraints(right, 3, row);

		grid.getChildren().add(right);
	}

	public void addPaddingBottom(GridPane grid, int row, Field field, Form form) {
		TextField bottom = new TextField();
		bottom.setId("text");
		bottom.setPrefWidth(40);
		bottom.setOnKeyReleased(event -> {
			field.setPaddingLeft(bottom.getText());
			field.updateField(form);
		});
		GridPane.setConstraints(bottom, 4, row);

		grid.getChildren().add(bottom);
	}

	public void addPaddingLeft(GridPane grid, int row, Field field, Form form) {
		TextField left = new TextField();
		left.setId("text");
		left.setPrefWidth(40);
		left.setOnKeyReleased(event -> {
			field.setPaddingLeft(left.getText());
			field.updateField(form);
		});
		GridPane.setConstraints(left, 5, row);

		grid.getChildren().add(left);
	}

	@SuppressWarnings("static-access")
	public void addWidth(GridPane grid, int row, Field field, Form form) {
		label = new Label("Width:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextField width = new TextField();
		width.setOnKeyReleased(event -> {
			field.setWidth("width: " + width.getText() + "%;");
			field.updateField(form);
		});
		GridPane.setConstraints(width, 2, row, 4, 1);

		grid.getChildren().addAll(label, width);
	}

	@SuppressWarnings("static-access")
	public void addHeight(GridPane grid, int row, Field field, Form form) {
		label = new Label("Height:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextField height = new TextField();
		height.setOnKeyReleased(event -> {
			field.setHeight("rows='" + height.getText() + "';");
			field.updateField(form);
		});
		GridPane.setConstraints(height, 2, row, 4, 1);
		height.setPrefWidth(40);

		grid.getChildren().addAll(label, height);
	}

	@SuppressWarnings("static-access")
	public void addErrorMessage(GridPane grid, int row, Field field) {
		label = new Label("Error Message:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		
		TextArea error = new TextArea();
		error.setPrefWidth(80);
		error.setPrefHeight(50);
		GridPane.setConstraints(error, 2, row, 4, 1);
		
		grid.getChildren().addAll(label, error);
	}

	@SuppressWarnings("static-access")
	public void addFriendlyHint(GridPane grid, int row, Field field, Form form) {
		label = new Label("Friendly Hint:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextArea hint = new TextArea();
		hint.setPrefWidth(80);
		hint.setPrefHeight(50);
		GridPane.setConstraints(hint, 2, row, 4, 1);
		hint.setOnKeyReleased(event -> {
			field.setFriendlyHint("<p style='color:#999; margin: 0px;'>" + hint.getText() + "</p>");
			field.updateField(form);
		});

		grid.getChildren().addAll(label, hint);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public void addPosition(GridPane grid, int row, Field field, Form form) {
		label = new Label("Position:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		choiceBox = new ChoiceBox(FXCollections.observableArrayList("Left", "Center", "Right", "Justify"));
		choiceBox.setPrefWidth(40);
		choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
				field.setPosition("text-align: " + choiceBox.getItems().get((Integer) number2) + ";");
				field.updateField(form);
			}
		});
		GridPane.setConstraints(choiceBox, 2, row);

		grid.getChildren().addAll(label, choiceBox);
	}

	@SuppressWarnings("static-access")
	public void addPlaceholder(GridPane grid, int row, Field field, Form form) {
		label = new Label("Placeholder:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextField placeholder = new TextField();
		placeholder.setPrefWidth(40);
		GridPane.setConstraints(placeholder, 2, row, 4, 1);
		placeholder.setOnKeyReleased(event -> {
			field.setPlaceholder("placeholder='" + placeholder.getText() + "';");
			field.updateField(form);
		});

		grid.getChildren().addAll(label, placeholder);
	}

	@SuppressWarnings("static-access")
	public void addRequired(GridPane grid, int row, Field field) {
		label = new Label("Field Options:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		grid.getChildren().add(label);
	}

	@SuppressWarnings("static-access")
	public void addMinLength(GridPane grid, int row, Field field) {
		label = new Label("Min Length:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		textField = new TextField();
		textField.setPrefWidth(40);
		GridPane.setConstraints(textField, 2, row);

		grid.getChildren().addAll(label, textField);
	}

	@SuppressWarnings("static-access")
	public void addMaxLength(GridPane grid, int row, Field field, Form form) {
		label = new Label("Max Length:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		TextField maxLength = new TextField();
		maxLength.setPrefWidth(40);
		GridPane.setConstraints(maxLength, 2, row, 4, 1);
		maxLength.setOnKeyReleased(event -> {
			field.setPlaceholder("maxlength='" + maxLength.getText() + "';");
			field.updateField(form);
		});

		grid.getChildren().addAll(label, maxLength);
	}

	@SuppressWarnings("static-access")
	public void addMinValue(GridPane grid, int row, Field field) {
		label = new Label("Min Value:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		textField = new TextField();
		GridPane.setConstraints(textField, 2, row);
		textField.setPrefWidth(40);

		grid.getChildren().addAll(label, textField);
	}

	@SuppressWarnings("static-access")
	public void addMaxValue(GridPane grid, int row, Field field) {
		label = new Label("Max Value:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		textField = new TextField();
		GridPane.setConstraints(textField, 2, row);
		textField.setPrefWidth(40);

		grid.getChildren().addAll(label, textField);
	}

	@SuppressWarnings("static-access")
	public void addDropDownOptions(GridPane grid, int row, Field field, Form form) {
		label = new Label("Options:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		ListView<String> listOptions = new ListView<String>();
		BorderPane optionPane = new BorderPane();
		ObservableList<String> items = FXCollections.observableArrayList("Option1", "Option2", "Option3");
		field.editExistedOption(items);
		listOptions.setItems(items);
		listOptions.setEditable(true);
		listOptions.setCellFactory(TextFieldListCell.forListView());
		listOptions.setPrefWidth(40);
		listOptions.setPrefHeight(100);
		listOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue != null) {
					System.out.println(oldValue + "   " + newValue);
					field.editExistedOption(items);
					field.updateField(form);
				}
			}
		});
		optionPane.setTop(listOptions);
		
		
		HBox optionBox = new HBox();
		Button plusBtn = new Button("+");
		plusBtn.setOnAction(event -> {
			items.add("Option");
			field.editExistedOption(items);
		});
		Button minusBtn = new Button("-");
		optionBox.getChildren().addAll(plusBtn, minusBtn);
		
		optionPane.setBottom(optionBox);
		
		GridPane.setConstraints(optionPane, 2, row, 4, 1);

		grid.getChildren().addAll(label, optionPane);
	}

	
	@SuppressWarnings("static-access")
	public void addCheckBoxOptions(GridPane grid, int row, Field field, Form form) {
		label = new Label("Options:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		ListView<String> checkList = new ListView<String>();
		BorderPane optionPane = new BorderPane();
		ObservableList<String> checkItems = FXCollections.observableArrayList("Check1", "Check2", "Check3");
		field.editExistedCheckList(checkItems); 
		checkList.setItems(checkItems);
		checkList.setEditable(true);
		checkList.setCellFactory(TextFieldListCell.forListView());
		
		checkList.setPrefWidth(40);
		checkList.setPrefHeight(100);
		checkList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue != null) {
					System.out.println(oldValue + "   " + newValue);
					field.editExistedCheckList(checkItems);
					field.updateField(form);
				}
			}
		});
		optionPane.setTop(checkList);
		
		HBox optionBox = new HBox();
		Button plusBtn = new Button("+");
		plusBtn.setOnAction(event -> {
			checkItems.add("Check");
			field.editExistedCheckList(checkItems);
		});
		Button minusBtn = new Button("-");
		optionBox.getChildren().addAll(plusBtn, minusBtn);
		
		optionPane.setBottom(optionBox);
		
		GridPane.setConstraints(optionPane, 2, row, 4, 1);

		grid.getChildren().addAll(label, optionPane);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public GridPane addLayout(GridPane grid, int row, Field field) {
		label = new Label("Layout:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		choiceBox = new ChoiceBox(FXCollections.observableArrayList("Side by side", "One coulmn", "Two coulmn", "Three coulmn"));
		choiceBox.setPrefWidth(40);
		GridPane.setConstraints(choiceBox, 2, row);

		grid.getChildren().addAll(label, choiceBox);
		return grid;
	}

	public void addMaxFileSize(GridPane grid, int row, Field field) {
		label = new Label("Max File Size:");
		label.setId("label");
		GridPane.setConstraints(label, 0, row, 2, 1);

		textField = new TextField();
		GridPane.setConstraints(textField, 2, row);
		textField.setPrefWidth(40);

		grid.getChildren().addAll(label, textField);
	}

	@SuppressWarnings("static-access")
	public void addAllowedFiles(GridPane grid, int row, Field field) {
		label = new Label("Allowed Files:");
		label.setId("label");
		grid.setHalignment(label, HPos.RIGHT);
		GridPane.setConstraints(label, 0, row, 2, 1);

		textField = new TextField();
		GridPane.setConstraints(textField, 2, row);
		textField.setPrefWidth(40);

		grid.getChildren().addAll(label, textField);
	}

	public BorderPane getPane() {
		return null;
	}

}
