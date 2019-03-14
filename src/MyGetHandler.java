import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyGetHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange t) throws IOException {
		// TODO Auto-generated method stub
		StringBuilder resp = new StringBuilder();
		Map<String, String> params = SimpleHttpServer.queryToMap(t.getRequestURI().getQuery());
		resp.append("<html><body>");
		resp.append("hello: " + params.get("hello") + "<br/>");
		resp.append("</body></html>");
		SimpleHttpServer.writeResponse(t, resp.toString());
	}
}
