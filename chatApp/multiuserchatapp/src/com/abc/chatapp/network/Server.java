package com.abc.chatapp.network;

import java.io.IOException;
//import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.abc.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>(); //Contains all the Client Sockets
	
	//Multiple Clients
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Start and Waiting for the Clients to join...");
		handleClientRequest();
	}
	//Multiple Client Handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept(); //Handshaking
			//Per Client Per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket, this); //Creating a New Worker/Thread
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	 
	
	
	/*Single Client
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client Connection....");
		Socket socket = serverSocket.accept(); //Handshaking
		System.out.println("Client Joins the Server");
		InputStream in = socket.getInputStream(); //read bytes from the network
		byte arr[] = in.readAllBytes();
		String str = new String(arr); //Bytes convert to string
		System.out.println("Message Receive from the client " + str);
		in.close();
		socket.close();
	}*/
	
	public static void main(String[] args) throws IOException
	{
		Server server = new Server();
	}
}
