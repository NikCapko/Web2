import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 1);
			server.createContext("/test", new MyHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Server start...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
