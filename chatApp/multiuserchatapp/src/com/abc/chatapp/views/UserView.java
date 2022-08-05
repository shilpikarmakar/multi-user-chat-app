package com.abc.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	int counter;
	public UserView() {
		counter = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //the tab closes on closing the screen
		setSize(500,500); //sets the size of the screen
		setResizable(false); //not able to maximize
		setTitle("Login"); //sets the title of the screen
		setLocationRelativeTo(null); // center of screen
//		setLocation(500,150);
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial", Font.BOLD,40));
		Container container = this.getContentPane();
		container.setLayout(null); // You don't want the container to set the layout
//		welcome.setBounds(x,y,width,height);
		welcome.setBounds(100,70,200,60);
		container.add(welcome);
		JButton button = new JButton("Count"); //button gets created with the name Count
		button.addActionListener(new ActionListener() //Listener -> ActionListener(Parent of ActionListener is EventListener) -> Implemented by a class
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				counter++;
				welcome.setText("Count " + counter);
			}
		});
		button.setBounds(100,300,200,50); //Where the button will be shown
		container.add(button);
		setVisible(true);
	}
	public static void main(String[] args){
		UserView userview = new UserView();
	}
}
