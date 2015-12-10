package ralen.base;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException{
//		HttpServer server;
//		try {
//			server = HttpServer.create(new InetSocketAddress(8080), 0);
//			server.createContext("/hello", new HttpHandler(){
//
//				@Override
//				public void handle(HttpExchange e) throws IOException {
//					System.out.println(e.getRequestMethod());
//					OutputStream out = e.getResponseBody();
//					String response = "<h1>Rafael</h1>";
//					e.sendResponseHeaders(200, response.length());
//					out.write(response.getBytes());
//					out.close();
//				}
//				
//			});
//			server.setExecutor(null);
//			server.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		WebApplication application = new WebApplication("");
		application.start();
	}
}
