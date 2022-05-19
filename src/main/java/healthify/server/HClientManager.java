package healthify.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class HClientManager extends Thread {
	private Socket clientSocket;
	private AtomicInteger threadCounter;
	public HClientManager(Socket socket, AtomicInteger counter) {
		clientSocket = socket;
		threadCounter = counter;
		this.setName("ClientManagerThread" + counter.get());
		this.start();
		threadCounter.incrementAndGet();
	}
	
	@Override
	public void run() {
		try (InputStream istrm = clientSocket.getInputStream()) {
			String msg = "";
			while(true) {
				int valueRead = istrm.read();
				if (valueRead == -1) {
					break;
				} else {
					char txt = (char) valueRead;
					if (txt != '\n') {
						msg += txt;
					} else {
						ParseMessage(msg);
						msg = "";
					}
				}
			}
			clientSocket.close();
			System.out.println("Connection successfully closed with client " + 
						clientSocket.getInetAddress() + ":" + clientSocket.getPort());
		} catch (IOException e) {
//			System.out.println("Error reading client's inputs. Error : " + e.getMessage());
		}
		threadCounter.decrementAndGet();
	}
	
	public void ParseMessage(String msg) {
		try {
			if (msg.contains("GetBlocks")) {
				int blocksCount = Integer.parseInt(msg.substring("GetBlocks ".length()));
				String blocksStr = BlockChainReader.GetBlocks(blocksCount);
				PrintWriter socketOutputStream = new PrintWriter(clientSocket.getOutputStream());
				socketOutputStream.println(blocksStr);
				socketOutputStream.close();
			} else {
				System.out.println("Invalid command passed!");
			}			
		} catch(IOException e) {
			System.out.println("Error sending block data. " + e.getMessage());
		}
	}
}