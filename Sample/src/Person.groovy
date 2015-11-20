import java.security.PublicKey;

class Person{
	static void main(args){
		def reader = new XmlSlurper().parse('http://wsf.cdyne.com/WeatherWS/Weather.asmx/GetWeatherInformation')
		def lol = reader.WeatherDescription.findAll{ it.Description.toString() ==~ /\.*Snow\.*/ }
		println lol
	}
}