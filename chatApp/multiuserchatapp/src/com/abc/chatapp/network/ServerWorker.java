package com.abc.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Thread == Worker
//Worker needs a job to Perform
//For job you give a runnable
//Once job is created via runnable so write the job logic inside a run function
//Assign the job to a Thread
//Use Runnable approach if you think your class inherits other class
//But if there is one class and only one class inherits a class then go for extends Thread because you will get the benefit of runnable interface and can override run()


public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException{
		this.server = server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream(); //Client Data Read
		out = clientSocket.getOutputStream(); //Client Data Write
		System.out.println("New Client Comes");
	}
	@Override
	public void run() {
		//Read data from the client and broadcast the data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
		while(true) {
				line = br.readLine(); //\n
				System.out.println("Line Read...." + line);
				if(line.equalsIgnoreCase("quit")) {
					break; //Client chat end
				}
//				out.write(line.getBytes()); //Client Send. Not broadcast
				//Broadcast to all
				for(ServerWorker serverWorker: server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes());
				}
		} 
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally {
			try {
				if(br != null)
				{
					br.close();
				}
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
				if(clientSocket != null) {
					clientSocket.close();
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		}
	}


















































/*
//public class ServerWorker implements Runnable{

public class ServerWorker extends Thread{
	@Override
	public void run() {
		//Job to perform
		//Logic
		for(int i=1; i<=5; i++) {
			System.out.println("Run I is " + i + " " + Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		ServerWorker job = new ServerWorker();
		job.start(); //If you extend Thread
		//Assign the job to a Thread/Worker
//		Thread worker = new Thread(job, "worker 1");
//		worker.start(); //Internally it call run()
		for(int j=1; j<=5; j++)
		{
			System.out.println("Main " + j + " " + Thread.currentThread());
		}
	}
} 
*/






