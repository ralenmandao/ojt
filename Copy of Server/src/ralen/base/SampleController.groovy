package ralen.base
import com.sun.net.httpserver.HttpContext;

import ralen.annotation.Controller
import ralen.annotation.Mapping

@Controller
class SampleController {
	@Mapping("/hello")
	String wew(HttpContext request, def response){
		response << ["name" : "fuck"]
		"sample"
	}
	
	@Mapping("/hi")
	String sayHi(HttpContext request, def response){
		response << ["name" : "judith"]
		"hi"
	}
}
