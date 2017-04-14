package com.java.field.options;

import com.java.field.Field;
import com.java.form.Form;
import com.java.frontend.View;

public class Password extends Field{
	
	public Password(String id) {
		super(id);
		this.setLabel("Password");
		this.setWidth("width: 100%;");
		this.setPaddingTop("2");
		this.setPaddingRight("2");
		this.setPaddingBottom("2");
		this.setPaddingLeft("2");
		this.setMaxLength("maxlength='254'");
		this.setFriendlyHint("");
	}

	@Override
	public void addField(Form form) {
		String str = "<div onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'>" + this.getLabel() + "<input " + this.getMaxLength() + "style='display: block; min-width: 98%; min-height: 20px; border-radius: 5px; margin: 5px auto' type='password'>" + this.getFriendlyHint() + "</div>";
		form.append(str);
		super.addField(form);
	}
	
	@Override
	public void updateField(Form form) {
		String str = "<div onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'>" + this.getLabel() + "<input " + this.getMaxLength() + "style='display: block; min-width: 98%; min-height: 20px; border-radius: 5px; margin: 5px auto' type='password'>" + this.getFriendlyHint() + "</div>";
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
