package com.java.properties.tab;

import com.java.field.Field;
import com.java.form.Form;
import com.java.properties.Properties;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class RadioButtonAndCheckBoxTab extends Properties {

	private BorderPane pane;
	private GridPane grid;
	private Label lbl;

	public RadioButtonAndCheckBoxTab(Field field, Form form) {

		pane = new BorderPane();
		lbl = new Label();
		lbl.setText(field.getClass().getSimpleName());
		lbl.setId("Title");
		pane.setTop(lbl);

		grid = new GridPane();
		this.addLabel(grid, 1, field, form);
		this.addLayout(grid, 2, field);
		this.addCheckBoxOptions(grid, 3, field, form);
		this.addWidth(grid, 6, field, form);
		this.addPaddingTop(grid, 7, field, form);
		this.addPaddingRight(grid, 7, field, form);
		this.addPaddingBottom(grid,7, field, form);
		this.addPaddingLeft(grid, 7, field, form);
		this.addFriendlyHint(grid, 8, field, form);
		this.addErrorMessage(grid, 9, field);

		grid.setGridLinesVisible(false);
		grid.setHgap(5);
		grid.setVgap(10);
		pane.setCenter(grid);
	}

	@Override
	public BorderPane getPane() {
		return pane;
	}

}
