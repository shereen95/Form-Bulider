package com.java.field.options;

import com.java.field.Field;
import com.java.form.Form;
import com.java.frontend.View;

public class PlainText extends Field {

	public PlainText(String id) {
		super(id);
		this.setPlainText("Sample Text");
		this.setPosition("text-align: left;");
		this.setWidth("width: 100%;");
		this.setPaddingTop("2");
		this.setPaddingRight("2");
		this.setPaddingBottom("2");
		this.setPaddingLeft("2");
	}

	@Override
	public void addField(Form form) {
		String str = "<div onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getWidth() + this.getPosition() + "margin: 0px; " + this.fieldPadding() + " min-height: 50px'>"+  this.getPlainText() + "</div>";
		form.append(str);
		super.addField(form);
	}
	
	@Override
	public void updateField(Form form) {
		String str = "<div onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getWidth() + this.getPosition() + "margin: 0px; " + this.fieldPadding() + " min-height: 50px'>" + this.getPlainText() + "</div>";
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