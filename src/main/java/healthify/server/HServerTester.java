package healthify.server;

import java.io.IOException;

public class HServerTester {
	public static void main(String[] args) throws InterruptedException {
		HServer server;
		for(int port = 7777; port <= 7777 + 10; port++) {
			try {
				server = new HServer(port);
				server.join();
				break;
			} catch (IOException e) {
				System.out.println("Error launching server on port: " + port);
			}
		}
	}
}