package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TimeClient {
	private static final String SERVER_IP = "192.168.1.99";
	private static final int SERVER_PORT = 50000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket datagramSocket=null;
		String date;
		try {
			//1.UDP 클라이언트 소켓 생성
			datagramSocket=new DatagramSocket();
			
				
				// 2.packet 보내기
				byte[] data="".getBytes();
				DatagramPacket sendPacket=new DatagramPacket(data,data.length,new InetSocketAddress(SERVER_IP,SERVER_PORT));
				datagramSocket.send(sendPacket);
				
				// 3.데이터 받기
				DatagramPacket receivePacket = new DatagramPacket(
						new byte[BUFFER_SIZE], BUFFER_SIZE);
				datagramSocket.receive(receivePacket);

				// 4.데이터 출력
				date = new String(receivePacket.getData(), 0,
						receivePacket.getLength(), "UTF-8");
				System.out.println("<< " + date);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log("error: "+e);
		}finally{
			//5.자원정리
			datagramSocket.close();
		}
	}
	public static void log(String log) {
		// TODO Auto-generated method stub
		System.out.println("[UDP-echo-client]"+log);
	}
}
