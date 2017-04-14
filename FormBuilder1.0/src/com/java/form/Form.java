package com.java.form;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.java.frontend.View;

public class Form {

	private String startTags = "<!DOCTYPE html><html><head><meta charset=utf-8'><meta name='description' content='Form Builder'><link media='screen'><title>Form</title></head><body><form style='min-height: 600px; background-color: #f8f8f8; border-radius: 5px'>";
	private String endTags = "</form></body></html>";
	private ArrayList<String> formList;

	public Form() {
		formList = new ArrayList<String>();
		formList.add(startTags);
	}

	public void append(String str) {
		formList.add(str);
	}

	public String preview() {
		String html = "";
		for (int i = 0; i < formList.size(); i++) {
			html += formList.get(i);
		}
		html += endTags;
		return html;
	}

	public void submit(File file) {
//		String workingDirectory = System.getProperty("user.dir");
//		workingDirectory += "\\bin\\com\\java\\frontend\\";
//		File file = new File(workingDirectory, name + ".html");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(preview());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void replace(String id, String str) {
		int index = View.getFieldIndex(id) + 1;
		formList.set(index, str);
	}
}
