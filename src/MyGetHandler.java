import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyGetHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange t) throws IOException {
		if (t.getRequestMethod().equalsIgnoreCase("GET")) {
			StringBuilder resp = new StringBuilder();
			Map<String, String> params = SimpleHttpServer.queryToMap(t.getRequestURI().getQuery());
			resp.append("<html><head>");
			resp.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />");
			resp.append("</head></body>");
			resp.append("<p>Работу выполнял Цапко Николай ПИ-41</p>");
			resp.append("<p>rim number: " + params.get("number") + "</p>");
			Convert c = new Convert(params.get("number"));
			resp.append("<p>arabic number: " + c.getResult() + "</p>");
			resp.append("</body></html>");
			SimpleHttpServer.writeResponse(t, resp.toString());
		} else {
			StringBuilder resp = new StringBuilder();
			resp.append("<html><body>");
			resp.append("<p>The server only supports GET requests</p>");
			resp.append("</body></html>");
			SimpleHttpServer.writeResponse(t, resp.toString());
		}
	}
}
