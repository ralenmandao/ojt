@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*
import groovy.swing.SwingBuilder

import java.awt.Choice;

import javax.swing.*;

class CurrencyConverterAPI {
//	static void main(args){
//		def choice
//		def reader = System.in.newReader()
//		def client = new SOAPClient('http://www.webservicex.net/CurrencyConvertor.asmx')
//		def currencies = """
//			AFA or ALL or DZD or ARS or AWG or AUD or BSD or BHD or BDT or BBD or BZD or BMD or BTN or 
//			BOB or BWP or BRL or GBP or BND or BIF or XOF or XAF or KHR or CAD or CVE or KYD or CLP or CNY or COP or 
//			KMF or CRC or HRK or CUP or CYP or CZK or DKK or DJF or DOP or XCD or EGP or SVC or EEK or ETB or EUR or 
//			FKP or GMD or GHC or GIP or XAU or GTQ or GNF or GYD or HTG or HNL or HKD or HUF or ISK or INR or IDR or 
//			IQD or ILS or JMD or JPY or JOD or KZT or KES or KRW or KWD or LAK or LVL or LBP or LSL or LRD or LYD or 
//			LTL or MOP or MKD or MGF or MWK or MYR or MVR or MTL or MRO or MUR or MXN or MDL or MNT or MAD or MZM or 
//			MMK or NAD or NPR or ANG or NZD or NIO or NGN or KPW or NOK or OMR or XPF or PKR or XPD or PAB or PGK or 
//			PYG or PEN or PHP or XPT or PLN or QAR or ROL or RUB or WST or STD or SAR or SCR or SLL or XAG or SGD or 
//			SKK or SIT or SBD or SOS or ZAR or LKR or SHP or SDD or SRG or SZL or SEK or CHF or SYP or TWD or TZS or 
//			THB or TOP or TTD or TND or TRL or USD or AED or UGX or UAH or UYU or VUV or VEB or VND or YER or YUM or 
//			ZMK or ZWD or TRY
//		"""
//
//		while(choice != 'exit'){
//			print '> '
//			choice = reader.readLine()
//			switch(choice){
//				case 'help':
//					println 'print [amount] [currency] to [currency]'
//					break;
//				case ~/print \-?\d+ \w{3} to \w{3}/:
//					def from = choice.split(' ')[2]
//					def to = choice.split(' ')[4]
//					def rs = currencies.split(' or ').find{ it.equals(from)}
//					if(rs == null) {
//						println "Invalid currency ${from}"
//						break;
//					}
//					rs = currencies.split(' or ').find{ it.equals(to)}
//					if(rs == null) {
//						println "Invalid currency ${to}"
//						break;
//					}
//					def value = choice.split(' ')[1]
//					def res = convertionRate(client, from, to)
//					println "${Double.parseDouble(res.ConversionRateResult.toString()) * Integer.parseInt(value)} ${to}"
//					break;
//				default:
//					break;
//			}
//		}
//	}
	static def client = new SOAPClient('http://www.webservicex.net/CurrencyConvertor.asmx')
	static def convertionRate = { from , to ->
		def response = client.send(SOAPAction:'http://www.webserviceX.NET/ConversionRate'){
			body{
				ConversionRate(xmlns: 'http://www.webserviceX.NET/'){
					FromCurrency(from)
					ToCurrency(to)
				}
			}
		}
		response.ConversionRateResponse.ConversionRateResult
	}
}
