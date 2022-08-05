package com.abc.chatapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class SplashScreen extends JWindow {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SplashScreen frame = new SplashScreen();
		frame.setVisible(true);
		frame.runProgressBar();
	}
	private Timer timer; //Instance Variable (Initialized will null value)
	private void runProgressBar() {
		timer = new Timer(120, (ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(count);
				count++;
				if(count > 100) {
					if(timer != null) {
						timer.stop();
						SplashScreen.this.setVisible(false);
						SplashScreen.this.dispose();
						UserScreen userScreen = new UserScreen();
						userScreen.setVisible(true);
					}
				}
			}
		});
		timer.start();
	}
	

	private int count = 0;
	JProgressBar progressBar;

	/**
	 * Create the frame.
	 */


	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//UIManager is a Special class to change color
		UIManager.put("ProgressBar.background", Color.BLACK); //Color of the background
		UIManager.put("ProgressBar.foreground", Color.RED); //Color of the progress bar
		UIManager.put("ProgressBar.selectionBackground", Color.YELLOW); //Color of percentage in progress bar
		UIManager.put("ProgressBar.selectionForeground", Color.RED);
		UIManager.put("ProgressBar.foreground", new Color(8,32,128));
		progressBar = new JProgressBar();
//		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setForeground(Color.BLACK);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 18));
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 314, 516, 26);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SplashScreen.class.getResource("/Images/480-4804984_chit-chat-media-group-energy-sector-icon-png.png")));
		lblNewLabel.setBounds(0, 0, 546, 375);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}
}
