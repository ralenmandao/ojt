import wslite.soap.*
import groovy.swing.SwingBuilder

import java.awt.Choice;

import javax.swing.*;

class TempConverterAPI {
	static def client = new SOAPClient('http://192.168.22.10:8080/services/CustomerService')
	static def getResponse = {
		def response = client.send(SOAPAction:'http://www.torocommerce.com/customerservice/listCustomer'){
			body{
				ListCustomerRequest(xmlns:'http://www.torocommerce.com/customerservice/'){
					customerIdList('?')
					customerSearch{
						firstName('dexter')
						lastName('Jaen')
					}
				}
			}
		}
		response.ListCustomerResponse
	}
	
	static void main(args){
		println getResponse().depthFirst().find{ it.name() == 'message' }.text()
	}
}