@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*
import groovy.swing.SwingBuilder

import java.awt.Choice;

import javax.swing.*;

class GeoIPAPI {
//	static void main(args){
//		def choice
//		def reader = System.in.newReader()
//		def client = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')
//		
//		while(choice != 'exit'){
//			print '> '
//			choice = reader.readLine()
//			switch(choice){
//				case 'help':
//					println 'print [ip]'
//					break;
//				case ~/print \d+.\d+.\d+.\d+/:
//					def res = getGeoIp(client, choice.split(" ")[1])
//					if(res.ReturnCode.toString().equals("0")){
//						println '\nNo result found\n'
//						break
//					}
//					println "IP : ${res.IP}"
//					println "Country name : ${res.CountryName}"
//					println "Country code : ${res.CountryCode}"
//					break;
//				case ~/print me/:
//					def res = getGeoIpContext(client)
//					if(res.ReturnCode.toString().equals("0")){
//						println '\nNo result found\n'
//						break
//					}
//					println "IP : ${res.IP}"
//					println "Country name : ${res.CountryName}"
//					println "Country code : ${res.CountryCode}"
//					break;
//				default:
//					break;
//			}
//		}
//	}
	
	static def client = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')
	static def getGeoIp = { ip ->
		def response = client.send(SOAPAction:'http://www.webservicex.net/GetGeoIP'){
			body{
				GetGeoIP(xmlns: 'http://www.webservicex.net/'){
					IPAddress(ip)
				}
			}
		}
		response.GetGeoIPResponse.GetGeoIPResult
	}
	
	static def getGeoIpContext = {
		def response = client.send(SOAPAction:'http://www.webservicex.net/GetGeoIPContext'){
			body{
				GetGeoIPContext(xmlns: 'http://www.webservicex.net/')
			}
		}
		response.GetGeoIPContextResponse.GetGeoIPContextResult
	}
}
