package com.bit2015.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	private static final int PORT = 10001;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		try {
			// 1.서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2.바인딩
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println("[서버] 바인딩   " + hostAddress + ":" + PORT);

			
			// 3.연결 요청 대기
			System.out.println("[서버] 연결 기다림");
			Socket socket = serverSocket.accept();
			
			
			// 4. 데이터 읽고 쓰기
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket
					.getRemoteSocketAddress();
			System.out.println("[서버] 연결됨 from: "
					+ inetSocketAddress.getHostName() + " : "
					+ inetSocketAddress.getPort());
			
			
			InputStream is = null;
			OutputStream os =null;
			try {
				 is = socket.getInputStream();
				 os = socket.getOutputStream();
				 
				 
				while (true) {
					byte[] buffer = new byte[128];
					int readByteCount = is.read(buffer);
					if (readByteCount < 0) { // 클라이언트가 정상적으로 종료
						System.out.println("[서버] 클라이언트로부터 연결 끊김");
						break;
					}
					
					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.print("[서버] 데이터 수신: " + data);

					os.write(data.getBytes("UTF-8"));
					os.flush();
				}
				
				is.close();
				os.close();
				if(socket.isClosed()==false){
					socket.close();
				}
			} catch (IOException e) {
				System.out.println("[서버] 애러: " + e);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null && serverSocket.isClosed() == false) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
