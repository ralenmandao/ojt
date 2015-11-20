import wslite.soap.*
import groovy.swing.SwingBuilder
import javax.swing.*;

class WeatherAPI {
	static def client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
	static def list = []
	
	static def getCityForecastByZip(String zip){
		def response = client.send(SOAPAction:'http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP'){
			body{
				GetCityForecastByZIP(xmlns:"http://ws.cdyne.com/WeatherWS/"){
					ZIP(zip)
				}
			}
		}
		convertToMap(response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult)
	}
	
	static def getCityWeatherByZip(String zip){ 
		def response = client.send(SOAPAction:'http://ws.cdyne.com/WeatherWS/GetCityWeatherByZIP'){
			body{
				GetCityWeatherByZIP(xmlns:"http://ws.cdyne.com/WeatherWS/"){
					ZIP(zip)
				}
			}
		}
		convertToMap(response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult)
	}
	
	static def getWeatherInformation(){ 
		def response = client.send(SOAPAction:'http://ws.cdyne.com/WeatherWS/GetWeatherInformation'){
			body{
				GetWeatherInformation()
			}
		}
		convertToMap(response.GetWeatherInformationResponse.GetWeatherInformationResult)
	}
	
	static def convertToMap(nodes) {
		nodes.children().each {
			if(it.childNodes().size() != 0){
				list << ["", ""]
				list << [it?.name(), ""]
				convertToMap(it)
			}else{
				list << [it?.name(), it?.text() ]
			}
		}
		return list
	}
}
