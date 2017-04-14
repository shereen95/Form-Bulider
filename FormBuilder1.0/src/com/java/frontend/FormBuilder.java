package com.java.frontend;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

public class FormBuilder {
	private JFrame mainFrame;
	private JMenuBar firstBar;
	private JPanel toolPanel;
	private JPanel elementsPanel;
	private JScrollPane scrollPane;
	private JScrollPane formScrollPane;
	private JEditorPane editorPane;
	private JPanel formPanel;
	private Font font;

	final static String[] menus = { "File", "Edit", "Actions", "View", "Tools", "Help" };
	final static String[] images = { "New", "Open", "Save", "Search", "Themes" };
	final static String[] basic = { "Title/Heading", "Plain Text","Text Field", "Text Area",
		"Number", "Password", "Drop Down", "Radio Buttons", "Checkboxes", "Upload File" };
	final static String[] basicIcons = { "\uf121", "\uf0f6", "\uf044", "\uf022", "\uf162",
		"\uf084", "\uf13a", "\uf192", "\uf046", "\uf0ee" };
	

	public FormBuilder() {
		// Frame Configurations
		mainFrame = new JFrame("Form Editor");
		mainFrame.setSize(1100, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		// Icon Font " Font Awesome "
		InputStream inputStream = FormBuilder.class
				.getResourceAsStream("/fontawesome-webfont.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			font = font.deriveFont(Font.PLAIN, 24f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		// Load Form Editor Front-End Elements
		this.addMenuBar();
		this.addToolPanel();
		this.addElementsPanel();
		this.addFormArea();

		mainFrame.setVisible(true);
	}

	public void addMenuBar() {
		firstBar = new JMenuBar();
		firstBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		firstBar.setBounds(0, 0, width(), height() / 20);
		firstBar.setBackground(SystemColor.controlHighlight);
		for (int i = 0; i < menus.length; i++) {
			JMenu item = new JMenu(menus[i]);
			item.setFont(new Font("Sitka Small", Font.PLAIN, 13));
			firstBar.add(item);
		}
		mainFrame.add(firstBar, BorderLayout.NORTH);
	}

	public void addToolPanel() {
		toolPanel = new JPanel();
		toolPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolPanel.setBounds(0, height() / 20, width(), height() / 10);
		toolPanel.setBackground(SystemColor.menu);
		for (int i = 0; i < images.length; i++) {
			JButton imgButton = new JButton(" " + images[i] + " ");
			imgButton.setBackground(SystemColor.menu);
			imgButton.setHorizontalTextPosition(JButton.CENTER);
			imgButton.setVerticalTextPosition(JButton.BOTTOM);
			imgButton.setFont(new Font("Sitka Small", Font.PLAIN, 13));
			imgButton.setBorder(new LineBorder(SystemColor.controlHighlight, 2));
			Image img = new ImageIcon(this.getClass().getResource("/" + images[i] + ".png")).getImage();
			imgButton.setIcon(new ImageIcon(img));
			toolPanel.add(Box.createRigidArea(new Dimension(5, 0)));
			toolPanel.add(imgButton);
		}
		mainFrame.add(toolPanel);
	}

	public void addElementsPanel() {
		elementsPanel = new JPanel();
		elementsPanel.setLayout(new GridLayout(2, 1));
		elementsPanel.setBorder(new LineBorder(SystemColor.menu, 3));

		scrollPane = new JScrollPane(elementsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(2 * width() / 3, 3 * height() / 20, width() / 3, 80 * height() / 100);

		for (int i = 0; i < 2; i++) {
			JPanel section = new JPanel();
			section.setLayout(new GridLayout(6, 2));
			section.setBackground(SystemColor.controlHighlight);
			if (i == 0) {
				JLabel basicLabelI = new JLabel("Basic");
				basicLabelI.setHorizontalAlignment(JLabel.HORIZONTAL);
				basicLabelI.setFont(new Font("Sitka Small", Font.BOLD, 20));
				section.add(basicLabelI);
				JLabel label = new JLabel("");
				section.add(label);

				for (int j = 0; j < 10; j++) {
					JButton basicButton = new JButton(basic[j]);
					basicButton.setLayout(new BorderLayout());
					basicButton.setBackground(SystemColor.menu);
					basicButton.setHorizontalTextPosition(JButton.RIGHT);
					basicButton.setVerticalTextPosition(JButton.CENTER);
					basicButton.setFont(new Font("Sitka Small", Font.PLAIN, 13));
					basicButton.setBorder(new LineBorder(SystemColor.controlHighlight, 3));

					JLabel label2 = new JLabel(basicIcons[j]);
					label2.setFont(font);
					label2.setForeground(new Color(232, 153, 27));
					
			


					basicButton.add(label2, BorderLayout.WEST);
					section.add(basicButton);
				}
			}
			elementsPanel.add(section);
		}
		mainFrame.add(scrollPane);
	}

	public void addFormArea() {
		formPanel = new JPanel();
		formPanel.setBorder(new LineBorder(SystemColor.menu, 3));
		formPanel.setBackground(SystemColor.controlHighlight);

		formScrollPane = new JScrollPane(formPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		formScrollPane.setBounds(0, 3 * height() / 20, 2 * width() / 3, 80 * height() / 100);

		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setFont(new Font("Sitka Small", Font.PLAIN, 13));
		editorPane.setBounds(0, 3 * height() / 20, 2 * width() / 3, 80 * height() / 100);
		try {
			editorPane.setPage(getClass().getResource("web/form.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		formPanel.add(editorPane);
		mainFrame.add(formScrollPane);
	}

	public int width() {
		return mainFrame.getWidth();
	}

	public int height() {
		return mainFrame.getHeight();
	}
}
