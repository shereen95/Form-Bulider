package com.java.field.options;

import com.java.field.Field;
import com.java.form.Form;
import com.java.frontend.View;

public class Title extends Field {

	public Title(String id) {
		super(id);
		this.setLabel("Title");
		this.setPosition("text-align: left;");
		this.setWidth("width: 100%;");
		this.setPaddingTop("2");
		this.setPaddingRight("2");
		this.setPaddingBottom("2");
		this.setPaddingLeft("2");
	}

	@Override
	public void addField(Form form) {
		String str = "<h2 onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getPosition() + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'>" + this.getLabel() + "</h2>";
		form.append(str);
		super.addField(form);
	}

	@Override
	public void updateField(Form form) {
		String str = "<h2 onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getPosition() + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'>" + this.getLabel() + "</h2>";
		form.replace(getId(), str);
		View.engine.loadContent(form.preview());
	}

	@Override
	public String fieldPadding() {
		String str = "padding: " + this.getPaddingTop() + "px " + this.getPaddingRight() + "px " + 
					this.getPaddingBottom() + "px " + this.getPaddingLeft() + "px;";
		return str;
	}

}
