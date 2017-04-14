package com.java.field;

import java.util.ArrayList;

import com.java.form.Form;
import com.java.properties.Properties;

import javafx.collections.ObservableList;

public abstract class Field {

	private String id;
	private Properties propertiesTab;
	private ArrayList<String> optionList = new ArrayList<String>();
	private ArrayList<String> checkList = new ArrayList<String>();
	
	// ALL
	private String label; // except plain text
	private String width;
	private String paddingTop;
	private String paddingRight;
	private String paddingBottom;
	private String paddingLeft;
	private String errorMessage;
	private String friendlyHint;

	// Title, Plain Text
	private String position;

	private String plainText;

	private String placeholder; // Text Field ,Text Area, Number
	private boolean required; // Text Field ,Text Area, Number, Password
	private String maxLength; // Text Field ,Text Area, Password

	// Text Area
	private String height;

	// Number
	private int minValue;
	private int maxValue;

	// Password
	private int minLength;

	// Drop Down, Radio Buttons, Check Box
	private String[] options;

	// Radio Buttons, Check Box
	private String layout;

	// Add Attachment
	private int maxFileSize;
	private String[] allowedFiles;

	public Field(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Properties getPropertiesTab() {
		return propertiesTab;
	}

	public void setPropertiesTab(Properties propertiesTab) {
		this.propertiesTab = propertiesTab;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		if (label.equals("")) {
			this.label = "Title";
		} else {
			this.label = label;
		}
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		if (plainText.equals("")) {
			this.plainText = "Sample Text";
		} else {
			this.plainText = plainText;
		}
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(String paddingTop) {
		this.paddingTop = paddingTop;
	}

	public String getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(String paddingRight) {
		this.paddingRight = paddingRight;
	}

	public String getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(String paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	public String getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(String paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFriendlyHint() {
		return friendlyHint;
	}

	public void setFriendlyHint(String friendlyHint) {
		this.friendlyHint = friendlyHint;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minlength) {
		this.minLength = minlength;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String[] getAllowedFiles() {
		return allowedFiles;
	}

	public void setAllowedFiles(String[] allowedFiles) {
		this.allowedFiles = allowedFiles;
	}

	public void addField(Form form) {
	}

	public void updateField(Form form) {
	}

	public String fieldPadding() {
		return null;
	}

	public String getOptionsList() {
		String str = "";
		for (int i = 0; i < optionList.size(); i++) {
			str += optionList.get(i);
		}
		return str;
	}
	
	public void editExistedOption(ObservableList<String> items) {
		optionList.clear();
		for (int i = 0; i < items.size(); i++) {
			optionList.add(i, "<option>" + items.get(i) + "</option>");
		}
	}
	
	public String getCheckList() {
		String str = "";
		for (int i = 0; i < checkList.size(); i++) {
			str += checkList.get(i);
		}
		return str;
	}
	
	public void editExistedCheckList(ObservableList<String> items) {
		checkList.clear();
		for (int i = 0; i < items.size(); i++) {
			checkList.add(i, "<input type='checkbox'>" + items.get(i));
		}
	}

	
}
