package com.java.frontend;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.java.database.MysqlConnection;
import com.java.field.Field;
import com.java.field.options.*;
import com.java.field.options.CheckBox;
import com.java.field.options.Number;
import com.java.field.options.RadioButton;
import com.java.field.options.TextArea;
import com.java.field.options.TextField;
import com.java.form.Form;
import com.java.properties.*;
import com.java.properties.tab.AddAttachementTab;
import com.java.properties.tab.DropDownTab;
import com.java.properties.tab.NumberTab;
import com.java.properties.tab.PasswordTab;
import com.java.properties.tab.RadioButtonAndCheckBoxTab;
import com.java.properties.tab.TextAreaTab;
import com.java.properties.tab.TextFieldTab;
import com.java.properties.tab.TitleAndPlainTextTab;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.*;
import javafx.geometry.*;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import netscape.javascript.JSObject;

public class View implements EventHandler<ActionEvent> {
	private BorderPane layout;
	private Form form;
	private Field field;
	private WebView formView;
	public static WebEngine engine;
	private Tab tab1, tab2, tab3;
	private Properties properties;
	private int fieldID = 0;
	private TabController tabController;
	private Field[] fieldArr;
	private ArrayList<Field> fieldList;
	private InputStream font;
	private Font fontAwesome;
	private String formName;
	private MysqlConnection mysqlConnection = new MysqlConnection();
	private Stage primaryStage;

	public View() {

		primaryStage = new Stage();
		// Main Layout of the Form Builder Program
		layout = new BorderPane();

		// Icon Font " Font Awesome "
		font = FormBuilder.class.getResourceAsStream("/fontawesome-webfont.ttf");
		fontAwesome = Font.loadFont(font, 10);

		// Top Layout that holds the MenuBar && ToolBar
		VBox topContainer = new VBox();
		MenuBar topMenuBar = new MenuBar();
		ToolBar topToolBar = new ToolBar();

		topContainer.getChildren().addAll(topMenuBar, topToolBar);
		layout.setTop(topContainer);

		StackPane centerContainer = new StackPane();

		// MenuBar
		this.addMenuBar(topMenuBar);

		// ToolBar
		this.addToolBar(topToolBar);

		// Grid of Form Fields
		// this.addFormFields(rightContainer);
		this.addTabs();

		// Form Area
		this.fieldArr = new Field[1000];
		this.tabController = new TabController();
		this.addFormArea(centerContainer);

		primaryStage.setTitle("Form Builder");
		Scene scene = new Scene(layout, 1100, 700);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(Login.class.getResource("view.css").toExternalForm());
		primaryStage.show();
	}

	public void addMenuBar(MenuBar topMenuBar) {
		String[] menus = { "File", "Edit", "Actions", "View", "Tools", "Help" };
		for (int i = 0; i < menus.length; i++) {
			Menu menu = new Menu(menus[i]);
			topMenuBar.getMenus().add(menu);
		}
	}

	public void addToolBar(ToolBar topToolBar) {
		String[] images = { "New", "Open","Print" , "Save", "Search", "Themes" ,"Apply"};
		for (int i = 0; i < images.length; i++) {
			Button btn = new Button();
			btn.setContentDisplay(ContentDisplay.TOP);
			btn.setId("ToolBar-button");
			btn.setText(images[i]);
			btn.setOnAction(this);
			btn.setTextAlignment(TextAlignment.CENTER);
			btn.setGraphic(new ImageView("/" + images[i] + ".png"));
			topToolBar.getItems().add(btn);
		}
	}

	public void addTabs() {
		String[] tabTitle = { "Elements", "Properties", "Form Options" };
		TabPane tabPane = new TabPane();
		tab1 = new Tab();
		tab2 = new Tab();
		tab3 = new Tab();

		tab1.setText(tabTitle[0]);
		tab2.setText(tabTitle[1]);
		tab3.setText(tabTitle[2]);

		addFormFields(tab1);
		initProperties(tab2);

		tabPane.getTabs().addAll(tab1, tab2, tab3);
		layout.setRight(tabPane);
	}

	public void initProperties(Tab tab) {
		HBox hbox = new HBox();
		hbox.getChildren().add(new Label("please select the field you want to change"));
		hbox.setAlignment(Pos.CENTER);
		tab.setContent(hbox);
	}

	public void addFormFields(Tab tab) {
		String[] basicFields = { "Title/Heading", "Plain Text", "Text Field", "Text Area", "Number", "Password",
				"Drop Down", "Radio Buttons", "Checkboxes", "Add Attachement" };
		String[] basicIcons = { "\uf121", "\uf0f6", "\uf044", "\uf022", "\uf162", "\uf084", "\uf13a", "\uf192",
				"\uf046", "\uf0ee" };
		GridPane rightContainer = new GridPane();
		rightContainer.getStyleClass().add("rightContainer");
		ScrollPane rightContainerScroll = new ScrollPane();
		rightContainerScroll.setContent(rightContainer);
		rightContainerScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		rightContainerScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				rightContainer.setMaxWidth(Double.MAX_VALUE);
				// containerWidth = (int) (layout.getWidth() / 3.5);
			}
		});
		
		// Basic Label
		Label basicLabel = new Label();
		basicLabel.setText("Basic");
		basicLabel.setId("basicLabel");
		GridPane.setConstraints(basicLabel, 0, 0, 2, 1);
		rightContainer.getChildren().add(basicLabel);

		// Add Fields Buttons
		rightContainer.setGridLinesVisible(false);
		rightContainer.setHgap(5);
		rightContainer.setVgap(3);

		for (int i = 0; i < basicFields.length; i++) {
			HBox box = new HBox();

			Label label = new Label(basicIcons[i]);
			label.setFont(fontAwesome);
			label.setId("icons");
			label.setMinWidth(25);
			box.getChildren().add(label);

			Button btn = new Button();
			btn.setId("grid-button");
			btn.setText(basicFields[i]);
			btn.setPrefWidth(130);
			btn.setOnAction(this);
			if (i % 2 == 0)
				GridPane.setConstraints(box, 0, i + 1);
			else
				GridPane.setConstraints(box, 1, i);
			box.getChildren().add(btn);

			rightContainer.getChildren().add(box);
		}
		tab.setContent(rightContainer);
	}

	public void addFormArea(StackPane centerContainer) {
		form = new Form();
		formView = new WebView();
		engine = formView.getEngine();
		engine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> p, State oldState, State newState) -> {
					if (newState == Worker.State.SUCCEEDED) {
						JSObject win = (JSObject) engine.executeScript("window");
						win.setMember("app", new TabController());
					}
				});
		engine.loadContent(form.preview());
		centerContainer.getChildren().add(formView);
		layout.setCenter(centerContainer);
	}

	@Override
	public void handle(ActionEvent event) {
		String text = ((Button) event.getSource()).getText();
		fieldList = new ArrayList<Field>();
		if (text.equals("New")) {
		} else if (text.equals("Open")) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("HTML", "*.html"));
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			formName = selectedFile.getName().substring(0, (selectedFile.getName().length() - 5));
		} else if (text.equals("Save")) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("HTML", "*.html"));
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			if (selectedFile != null)
				form.submit(selectedFile);
			formName = selectedFile.getName().substring(0, (selectedFile.getName().length() - 5));
			int id = mysqlConnection.getUserID();
			String sql = "INSERT INTO Forms (name , users_id) " + "VALUES ( '" + formName + "'," + 1 + ")";
			mysqlConnection.addNewRecord(sql);
		}else if (text.equals("Apply")) {
			String str = "";
			for (int i = 0; i < fieldList.size(); i++) {
				if (!fieldList.get(i).getClass().getSimpleName().equals("Number")) {
					str += fieldList.get(i).getLabel() + " VARCHAR(255), ";
				} else {
					str += fieldList.get(i).getLabel() + " INTEGER, ";
				}
			}
			String sql = "CREATE TABLE " + formName + " (id INTEGER not NULL AUTO_INCREMENT, " + str
					+ "PRIMARY KEY ( id ), " + "form_id INTEGER, " + "FOREIGN KEY (form_id) REFERENCES Forms(id))";
			mysqlConnection.createTable(sql, formName);
			new Application(this.form);
		} else if (text.equals("Search")) {
		} else if (text.equals("Print")) {
			PrinterJob job = PrinterJob.createPrinterJob();
			if(job != null) {
				engine.print(job);
				job.endJob();
			}
		} else if (text.equals("Title/Heading")) {
			field = new Title("Title" + fieldID);
			properties = new TitleAndPlainTextTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Plain Text")) {
			field = new PlainText("PlainText" + fieldID);
			properties = new TitleAndPlainTextTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Text Field")) {
			field = new TextField("TextField" + fieldID);
			properties = new TextFieldTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Text Area")) {
			field = new TextArea("TextArea" + fieldID);
			properties = new TextAreaTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Number")) {
			field = new Number("Number" + fieldID);
			properties = new NumberTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Password")) {
			field = new Password("Password" + fieldID);
			properties = new PasswordTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Drop Down")) {
			field = new DropDown("DropDown" + fieldID);
			properties = new DropDownTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Radio Buttons")) {
			field = new RadioButton("RadioButton" + fieldID);
			properties = new RadioButtonAndCheckBoxTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Checkboxes")) {
			field = new CheckBox("CheckBox" + fieldID);
			properties = new RadioButtonAndCheckBoxTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		} else if (text.equals("Add Attachement")) {
			field = new AddAttachement("AddAttachement" + fieldID);
			properties = new AddAttachementTab(field, form);
			field.addField(form);
			tabController.registerField(field);
			field.setPropertiesTab(properties);
		}
		
		if (!field.getClass().getSimpleName().equals("Title")) {
			fieldList.add(field);
		}
		fieldID++;
		engine.loadContent(form.preview());
		tab2.setContent(properties.getPane());
	}

	public static int getFieldIndex(String id) {
		int result = 0;
		for (int i = id.length() - 1; i >= 0; i--) {
			try {
				String str = id.substring(i, id.length());
				result = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				result = Integer.parseInt(id.substring(i + 1, id.length()));
				break;
			}
		}
		System.out.println(result);
		return result;
	}

	public class TabController {
		public void registerField(Field field) {
			int index = getFieldIndex(field.getId());
			fieldArr[index] = field;
		}

		public void changeTab(String id) {
			tab2.setContent(fieldArr[getFieldIndex(id)].getPropertiesTab().getPane());
		}
	}

}
