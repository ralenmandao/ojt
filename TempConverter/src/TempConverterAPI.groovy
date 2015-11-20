@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*
import groovy.swing.SwingBuilder

import java.awt.Choice;

import javax.swing.*;

class TempConverterAPI {
//	static void main(args){
//		def choice
//		def reader = System.in.newReader()
//		def client = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')
//		
//		while(choice != 'exit'){
//			print '> '
//			choice = reader.readLine()
//			switch(choice){
//				case 'help':
//					println 'print [value] [unit] to [unit]'
//					println 'unit : '
//					println '  f - Fahrenheit'
//					println '  c - Celsius'
//					break;
//				case ~/print \-?\d+ c to f/:
//					def res = getCelciusToFahrenheit(client, choice.split(" ")[1])
//					println res.CelsiusToFahrenheitResult
//					break;
//				case ~/print \-?\d+ f to c/:
//					def res = getFahrenheitToCelcius(client, choice.split(" ")[1])
//					println res.FahrenheitToCelsiusResult
//					break;
//				default:
//					break;
//			}
//		}
//	}
	static def client = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')
	static def getCelciusToFahrenheit = { value ->
		def response = client.send(SOAPAction:'http://www.w3schools.com/webservices/CelsiusToFahrenheit'){
			body{
				CelsiusToFahrenheit(xmlns:"http://www.w3schools.com/webservices/"){
					Celsius(value)
				}
			}
		}
		response.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult
	}
	
	static def getFahrenheitToCelcius = { value ->
		def response = client.send(SOAPAction:'http://www.w3schools.com/webservices/FahrenheitToCelsius'){
			body{
				FahrenheitToCelsius(xmlns: "http://www.w3schools.com/webservices/"){
					Fahrenheit(value)
				}
			}
		}
		response.FahrenheitToCelsiusResponse.FahrenheitToCelsiusResult
	}
}