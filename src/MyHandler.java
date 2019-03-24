import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		byte[]  resp = "Welcome page".getBytes();
		t.sendResponseHeaders(200, resp.length);
		OutputStream out = t.getResponseBody();
		out.write(resp);
		out.close();
	}
}
