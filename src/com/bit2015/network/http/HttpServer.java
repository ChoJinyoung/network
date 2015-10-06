package com.bit2015.network.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;



public class HttpServer {

	private static final int PORT = 8088;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		
		try {
			// 1.서버소켓 생성
			serverSocket = new ServerSocket();
			// 2.바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			log("연결기다림 "+hostAddress+":"+PORT);
			// 3.연결요청 기다림
			while (true) {
				Socket socket = serverSocket.accept();
				
				Thread thread=new HttpThread(socket);
				thread.start();
			}

		} catch (IOException e) {
			log("error: "+e);
		}finally{
			if(serverSocket!=null&&serverSocket.isClosed()==false){
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log("error: "+e);
				}
			}
		}
	}
	public static void log(String log){
		System.out.println("[chat-server] "+log);
	}

}
