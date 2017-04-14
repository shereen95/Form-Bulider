package com.java.properties.tab;

import com.java.field.Field;
import com.java.form.Form;
import com.java.properties.Properties;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DropDownTab extends Properties{
	private BorderPane pane;
	private GridPane grid;
	private Label label;

	public DropDownTab(Field field, Form form) {
		pane = new BorderPane();
		label = new Label();
		label.setText(field.getClass().getSimpleName());
		label.setId("Title");
		pane.setTop(label);

		grid = new GridPane();
		this.addLabel(grid, 1, field, form);
		this.addDropDownOptions(grid, 2, field, form);
		this.addWidth(grid, 3, field, form);
		this.addPaddingTop(grid, 4, field, form);
		this.addPaddingRight(grid, 4, field, form);
		this.addPaddingBottom(grid, 4, field, form);
		this.addPaddingLeft(grid, 4, field, form);
		this.addFriendlyHint(grid, 5, field, form);
		this.addErrorMessage(grid, 6, field);
		
//		grid.setGridLinesVisible(true);
		grid.setHgap(5);
		grid.setVgap(10);
		pane.setCenter(grid);
	}
	
	@Override
	public BorderPane getPane() {
		return pane;
	}

}
