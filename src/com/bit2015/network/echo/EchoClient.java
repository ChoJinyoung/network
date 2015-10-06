package com.bit2015.network.echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_ADDRESS = "192.168.1.100";
	private static final int SERVER_PORT = 10005;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Socket socket = null;
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));

			// 쓰고/받기

			BufferedReader reader = null;
			PrintWriter printWriter = null;
			// OutputStream os =null;
			// InputStream is =null;

			while (true) {
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				printWriter = new PrintWriter(socket.getOutputStream());
				// os = socket.getOutputStream();
				// is = socket.getInputStream();
				System.out.print(">>");
				String data = scanner.nextLine();
				if ("exit".equals(data) == true) {
					break;
				}
				data += "\n";
				
				
				printWriter.print(data);
				printWriter.flush();
				
				// os.write(data.getBytes("UTF-8"));

				// int readByteCount = is.read(buffer);
				// data = new String(buffer, 0, readByteCount, "UTF-8");
				// System.out.print("<<" + data);

			}
			printWriter.close();
			reader.close();
			if (socket.isClosed() == false) {
				socket.close();
			}

		} catch (IOException e) {
			System.out.println("<<에러: " + e);
		}
	}
}
