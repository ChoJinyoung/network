package com.bit2015.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	
	private static final String SERVER_IP = "192.168.1.99";
	private static final int SERVER_PORT = 50000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket datagramSocket=null;
		Scanner scan=new Scanner(System.in,"euc-kr");
		try {
			//1.UDP 클라이언트 소켓 생성
			datagramSocket=new DatagramSocket();
			
			while (true) {
				//사용자 입력받기
				System.out.print(">> ");
				String message=scan.next();
				
				
				if ("quit".equals(message)==true) {
					break;
				}
				// 2.packet 보내기
				byte[] data = message.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(data,
						data.length, new InetSocketAddress(SERVER_IP,
								SERVER_PORT));
				datagramSocket.send(sendPacket);
				
				// 3.데이터 받기
				DatagramPacket receivePacket = new DatagramPacket(
						new byte[BUFFER_SIZE], BUFFER_SIZE);
				datagramSocket.receive(receivePacket);

				// 4.데이터 출력
				message = new String(receivePacket.getData(), 0,
						receivePacket.getLength(), "UTF-8");
				System.out.println("<< " + message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log("error: "+e);
		}finally{
			//5.자원정리
			datagramSocket.close();
			scan.close();
		}
	}

	public static void log(String log) {
		// TODO Auto-generated method stub
		System.out.println("[UDP-echo-client]"+log);
	}

}
