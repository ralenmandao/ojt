package ralen.base
import groovy.xml.MarkupBuilder
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer;

import ralen.annotation.Controller;
import ralen.annotation.Mapping;

import ralen.annotation.View;

public class WebApplication {
	
	private final Reflections reflections;
	private HttpServer server;
	private Map<Object, Method> mapHandlers = new HashMap<Object, Method>();
	private Map<Class<?>, Object> controllerHandlers = new HashMap<Class<?>, Object>();
	private Map<String, ViewBuilder> viewHandlers = new HashMap<String, ViewBuilder>();
	
	public WebApplication(String basePackage){
		reflections = new Reflections(new ConfigurationBuilder()
				.setUrls( ClasspathHelper.forPackage(basePackage) )
				.setScanners( new FieldAnnotationsScanner(),
							  new TypeAnnotationsScanner(),
							  new SubTypesScanner()));
		
		  try {
			  server = HttpServer.create(new InetSocketAddress(8080), 0);
			  server.setExecutor(null);
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
		  
		Set< Class<?> > views = reflections.getSubTypesOf(ViewBuilder.class)
		for( Class<?> view : views){
			View viewAnnotation = view.getAnnotation(View.class)
			if(!viewAnnotation){
				continue;
			}
			viewHandlers.put(viewAnnotation.value(), view.newInstance())
		}
						  
		Set< Class<?> > controllers = reflections.getTypesAnnotatedWith(Controller.class);
		for( Class<?> controller : controllers){
			controllerHandlers.put(controller, controller.newInstance())
			for(Method method : controller.getMethods()){
				Mapping mapping = method.getAnnotation(Mapping.class)
				if(mapping == null){
					continue;
				}
				addNewHandler(mapping.value(), method)
			}
		}
	}
	
	private void addNewHandler(String path, Method method){
		server.createContext(path, { HttpExchange e ->
			Object obj = controllerHandlers.get(method.getDeclaringClass())
			def model = [:]
			String view = method.invoke(obj, e.getHttpContext(), model)
			def writer = new StringWriter()
			String response = viewHandlers.get(view).getView(new MarkupBuilder(writer), model)
			response = writer.toString()
			println ' I GOT'
			OutputStream out = e.getResponseBody();
			e.sendResponseHeaders(200, response.length());
			out.write(response.getBytes());
			out.close();
		})
	}
	
	public void start(){ 
		server.start();
	}
}
