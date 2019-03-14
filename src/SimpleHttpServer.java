import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 1);
			server.createContext("/test", new MyGetHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Server start...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, String> queryToMap(String query) {
		// TODO Auto-generated method stub
		System.out.println(query);
		Map<String, String> params = new HashMap();
		String[] paramsArray = query.split("&");
		for (String par : paramsArray) {
			params.put(par.split("=")[0], par.split("=")[1]);
		}
		return params;
	}

	public static void writeResponse(HttpExchange t, String str) {
		// TODO Auto-generated method stub
		byte[] resp = str.getBytes();
		try {
			t.sendResponseHeaders(200, resp.length);
			OutputStream out = t.getResponseBody();
			out.write(resp);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
