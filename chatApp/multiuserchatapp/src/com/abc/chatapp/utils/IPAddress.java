package com.abc.chatapp.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;


//to get ip of your machine
public class IPAddress {
	public static void main(String[] args) throws UnknownHostException {
//		InetAddress ad = InetAddress.getLocalHost(); //for local
//		System.out.println(ad);
		
		InetAddress add[] = InetAddress.getAllByName("DESKTOP-NI2834K"); //for finding the ip address of other machine 
		for(InetAddress a : add) {
			System.out.println(a);
		}
	}
}
