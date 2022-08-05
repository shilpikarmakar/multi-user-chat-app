package com.abc.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.abc.chatapp.dao.UserDAO;
import com.abc.chatapp.dto.UserDTO;
import com.abc.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {
	Logger logger = Logger.getLogger(UserScreen.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField useridtxt;
	private JPasswordField passwordField;

	public static void main(String[] args) {
					UserScreen window = new UserScreen();
					
	}
	
	private void doLogin() {
		logger.debug("Inside the Do Login");
		String userid = useridtxt.getText();
//		String password = passwordField.getText(); //For security reasons it is deprecated because if you print it it will print the password as well so password is printable
		char[] password = passwordField.getPassword(); //In place of getText you use getPassword()
		
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message = "";
			try {
				if(UserDAO.isLogin(userDTO)) {
					message = "Welcome " + userid;
					UserInfo.USER_NAME = userid;
					JOptionPane.showMessageDialog(this, message);
					setVisible(false); //To close the screen of dashboard which was open at the back
					dispose(); //To remove it from the memory as well
					DashBoard dashBoard = new DashBoard(message);
					dashBoard.setVisible(true);
					logger.debug("In a New Screen....Login Successful" + userid);
				}
				else {
					message = "Invalid userid or Password";
					JOptionPane.showMessageDialog(this, message);
				}
//				JOptionPane.showMessageDialog(this, message);
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			catch(Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		finally {
			
		}
	}
	
	
	//Function to pick userid and password
	private void register() throws ClassNotFoundException, SQLException {
		String userid = useridtxt.getText();
//		String password = passwordField.getText(); //For security reasons it is deprecated because if you print it it will print the password as well so password is printable
		char[] password = passwordField.getPassword(); //In place of getText you use getPassword()
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
		int result = userDAO.add(userDTO);
		if(result > 0) {
			JOptionPane.showMessageDialog(this, "Registered Succesfully"); //Shows a pop up that you have registered successfully along with the printing
//			System.out.println("Record Added...");
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Registration Failed"); //Shows a pop up that your registration has failed along with the printing
//			System.out.println("Record Not Added...");
		}
		}
		catch(ClassNotFoundException |SQLException ex) {
			System.out.println("DB Issue....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some Generic Exception Raised....");
			ex.printStackTrace(); //Where is the Exception
		}
		System.out.println("userid " + userid + " Password " + password); //Password will be printed in the form ClassNAme@HashCode(Hexa)
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 40));
		lblNewLabel.setBounds(253, 32, 274, 82);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(373, 148, 322, 40);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("UserId");
		useridlbl.setForeground(Color.WHITE);
		useridlbl.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		useridlbl.setBounds(163, 135, 149, 59);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setForeground(Color.WHITE);
		pwdlbl.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		pwdlbl.setBounds(163, 258, 149, 59);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(372, 271, 323, 39);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Lucida Sans", Font.BOLD, 20));
		loginbt.setBounds(163, 390, 190, 47);
		getContentPane().add(loginbt);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					register();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Registerbt.setFont(new Font("Lucida Sans", Font.BOLD, 20));
		Registerbt.setBounds(478, 390, 190, 47);
		getContentPane().add(Registerbt);
		setBackground(Color.GRAY);
		setSize(810, 498);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //To bring to center
		setVisible(true);
	}
}
