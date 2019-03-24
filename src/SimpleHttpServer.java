import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 1);
			server.createContext("/convert", new MyGetHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Server start...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> queryToMap(String query) {
		Map<String, String> params = new HashMap<String, String>();
		if (query != null && query.contains("=")) {
			String[] paramsArray = query.split("&");
			for (String par : paramsArray) {
				String[] p = par.split("=");
				if (p.length == 2) {
					params.put(p[0], p[1]);
				}
			}
		}
		return params;
	}

	public static void writeResponse(HttpExchange t, String str) {
		byte[] resp = str.getBytes();
		try {
			t.sendResponseHeaders(200, resp.length);
			t.setAttribute("Content-Type", "text/html");
			OutputStream out = t.getResponseBody();
			out.write(resp);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
