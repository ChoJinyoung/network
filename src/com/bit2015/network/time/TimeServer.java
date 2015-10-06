package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {

	private static final int PORT = 50000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatagramSocket datagramSocket = null;
		try {
			// 1.UDP 서버 소켓생성
			datagramSocket = new DatagramSocket(PORT);

			while (true) {
				// 2.수신 대기
				log("packet 수신 대기");
				DatagramPacket receivePacket = new DatagramPacket(
						new byte[BUFFER_SIZE], BUFFER_SIZE);
				datagramSocket.receive(receivePacket);

				// 3.수신 데이터 출력
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss a");
				String date = format.format(new Date());
				byte[] data=date.getBytes();
				log("packet 수신");

				// 4.데이터 보내기
				DatagramPacket sendPacket = new DatagramPacket(
						receivePacket.getData(), receivePacket.getLength(),
						receivePacket.getAddress(), receivePacket.getPort());
				datagramSocket.send(sendPacket);
			}
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			// 5.자원 정리
			datagramSocket.close();
		}
	}

	public static void log(String log) {
		// TODO Auto-generated method stub
		System.out.println("[UDP-echo-server]" + log);
	}

}
