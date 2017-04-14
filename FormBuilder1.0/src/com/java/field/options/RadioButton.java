package com.java.field.options;

import com.java.field.Field;
import com.java.form.Form;
import com.java.frontend.View;

public class RadioButton extends Field {
	
	public RadioButton(String id) {
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
		String str = "<div onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'>" + this.getLabel() + "<br><input type='radio' value='Option1'>Option1<input type='radio' value='Option2'>Option2<input type='radio' value='Option3'>Option3" + this.getFriendlyHint() + "</div>";
		form.append(str);	
		super.addField(form);
	}
	
	@Override
	public void updateField(Form form) {
		String str = "<div onclick='app.changeTab(\"" + getId() + "\")' style='" + this.getWidth() + "margin: 0px; " + this.fieldPadding() + "'>" + this.getLabel() + "<br><input type='radio' value='Option1'>Option1<input type='radio' value='Option2'>Option2<input type='radio' value='Option3'>Option3" + this.getFriendlyHint() + "</div>";
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
