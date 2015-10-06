package com.bit2015.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoReceiveThread extends Thread {
	private Socket socket = null;

	public EchoReceiveThread(Socket socket) {
		this.socket = socket;
	}

	// run 구현
	public void run() {
		InetSocketAddress inetSocketAddress = (InetSocketAddress) socket
				.getRemoteSocketAddress();
		System.out.println("[서버] 연결됨 from: " + inetSocketAddress.getHostName()
				+ " : " + inetSocketAddress.getPort());

//		InputStream is = null;
//		OutputStream os = null;
		
		BufferedReader reader =null;
		PrintWriter printWriter=null;
		try {
//			is = socket.getInputStream();
//			os = socket.getOutputStream();
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter=new PrintWriter(socket.getOutputStream());
			while (true) {
				
//				byte[] buffer = new byte[128];
//				int readByteCount = is.read(buffer);
//				if (readByteCount < 0) { // 클라이언트가 정상적으로 종료
//					System.out.println("[서버] 클라이언트로부터 연결 끊김");
//					break;
//				}
//
//				String data = new String(buffer, 0, readByteCount, "UTF-8");
				String data=reader.readLine();
				if(data==null){
					break;
				}
				System.out.println("[서버] 데이터 수신: " + data);

//				os.write(data.getBytes("UTF-8"));
//				os.flush();
				
				printWriter.println(data);
				printWriter.flush();
			}

			reader.close();
			printWriter.close();
			if (socket.isClosed() == false) {
				socket.close();
			}
		} catch (IOException e) {
			System.out.println("[서버] 애러: " + e);
		}

	}
}
