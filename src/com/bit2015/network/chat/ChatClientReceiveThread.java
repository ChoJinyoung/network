package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiveThread extends Thread {
	BufferedReader bufferedReader;


	public ChatClientReceiveThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	// run 구현
	public void run() {
		try {
			while (true) {
				String data = bufferedReader.readLine();
				if(data==null){
					break;
				}
				System.out.println(data);
			}
		} catch (IOException e) {
			ChatClient.log("error: " + e);
		}
	}
}
