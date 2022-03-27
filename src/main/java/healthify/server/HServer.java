package healthify.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class HServer extends Thread {
	ServerSocket socket;
	final private int MAX_THREADS = 10;
	final public static int port = 7777;
	public boolean isRunning = false;
	public HServer() throws IOException {
		this(port);
	}
	
	public HServer(int port) throws IOException {
		System.out.println("Starting Healthify Server...");
		
		socket = new ServerSocket(port);
		System.out.println("Healthify Server started succesfully");
		
		this.setName("HServerListenerThread");
		this.start();
	}
	
	@Override
	public void run() {
		System.out.println("Listening on port " + port + " for connections...");
		AtomicInteger curThreads = new AtomicInteger(0);
		ArrayList<HClientManager> clientManagers = new ArrayList<HClientManager>();
		while(true) {
			try {
				Socket clientSocket = socket.accept();
				if (curThreads.get() < MAX_THREADS) {
					System.out.println("Successfully connected to : " 
							+ clientSocket.getInetAddress() + ":" + clientSocket.getPort());
					HClientManager clientManager = new HClientManager(clientSocket, curThreads);
					clientManagers.add(clientManager);
				} else {
					System.out.println("Maximum client connection limit reached, unable to connect. Current Limit: " + MAX_THREADS);
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println("An attempting connection failed to connect." + " Error --> " + e.getMessage());
			}
		}
	}
}