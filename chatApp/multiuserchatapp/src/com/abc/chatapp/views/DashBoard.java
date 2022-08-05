package com.abc.chatapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class DashBoard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) { //It is being done in UserScreen
//		DashBoard frame = new DashBoard();
//		frame.setVisible(true);
//	}

	/**
	 * Create the frame.
	 */
	public DashBoard(String message) {
		setExtendedState(JFrame.MAXIMIZED_BOTH); //Appears on the whole screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 442);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu chatMenu = new JMenu("Chat");
		menuBar.add(chatMenu);
		
		JMenuItem startChat = new JMenuItem("Start Chat");
		startChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ClientChatScreen();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		chatMenu.add(startChat);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle(message);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 760, 598);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(DashBoard.class.getResource("/Images/images.jpg")));
		contentPane.add(lblNewLabel);
	}

}
