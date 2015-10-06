package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress inetAddress=InetAddress.getLocalHost();
			System.out.println(inetAddress.getHostName());
			System.out.println(inetAddress.getHostAddress());
			byte[] addresses=inetAddress.getAddress();
			for (int i = 0; i < addresses.length; i++) {
				int address=addresses[i] & 0xff;
				System.out.println(address);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
