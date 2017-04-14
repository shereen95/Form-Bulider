package com.java.field.options;

import com.java.field.Field;
import com.java.form.Form;
import com.java.frontend.View;

public class DropDown extends Field {

	public DropDown(String id) {
		super(id);
		this.setLabel("Select an option");
		this.setWidth("width: 100%;");
		this.setPaddingTop("2");
		this.setPaddingRight("2");
		this.setPaddingBottom("2");
		this.setPaddingLeft("2");
		this.setFriendlyHint("");
	}

	@Override
	public void addField(Form form) {
		String str = "<div onclick='app.changeTab(\"" + getId()  + "\")' style='" + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'><label>" + this.getLabel() + "</label> <br/> <select>" + this.getOptionsList() + "</select><br>" + this.getFriendlyHint() + "</div>";
		System.out.println(getOptionsList());
		form.append(str);
		super.addField(form);
	}
	
	@Override
	public void updateField(Form form) {
		String str = "<div onclick='app.changeTab(\"" + getId()  + "\")' style='" + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'><label>" + this.getLabel() + "</label> <br/> <select>" + this.getOptionsList() + "</select><br>" + this.getFriendlyHint() + "</div>";
		System.out.println(getOptionsList());
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
