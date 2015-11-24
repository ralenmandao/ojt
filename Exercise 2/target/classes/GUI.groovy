import groovy.swing.SwingBuilder
import java.awt.GridBagConstraints
import java.sql.Blob;
import wslite.soap.*

import javax.swing.JFrame
import javax.swing.JOptionPane
import java.awt.BorderLayout as BL
import java.awt.GridLayout


class GUI {
	static void main(args){
		def inputBox
		def client = new SOAPClient('http://www.dneonline.com/calculator.asmx')
		def getAnswer = { a,b,op ->
			if(!a || !b || !op) return b ? b : a ? a : ''
			def response = client.send(SOAPAction:"http://tempuri.org/${op}"){
				body{
					"${op}"(xmlns:'http://tempuri.org/'){
						intA(a)
						intB(b)
					}
				}
			}
			response."${op}Response"."${op}Result"
		}
		def operator = null
		def operators = []	
		def first
		boolean equalIsPressed = false
		def number = {
			if(equalIsPressed){
				 inputBox.text = ''
				 equalIsPressed = false
			}
			if(inputBox.text == '0') inputBox.text = it.source.text
			else inputBox.text += it.source.text
		}
		def equals = {
			try{
				inputBox.text = getAnswer(first, inputBox.text, operator)
			}catch(all){
				inputBox.text = 'NaN'
			}finally{
				operator = null
				first = null
				equalIsPressed = true
			}
		}
		def operatorAction = {
			if(operator != null) {
				equals()
				operator = it.source.text
				first = inputBox.text
				return
			}
			if(first == null){ first = inputBox.text }
			inputBox.text = ''
			operator = it.source.text
		}
		def buttons = []
		
		new SwingBuilder().edt{
			frame(
				visible: true,
				pack: true,
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE
			){
				panel(layout: new BL()){
					inputBox = textField(constraints: BL.NORTH, enabled: false)
					panel(layout: new GridLayout(rows:4, cols: 4), constraints: BL.CENTER){
						buttons << button(text: '1')
						buttons << button(text: '2')
						buttons << button(text: '3')
						operators << button(text: 'Add')
						buttons << button(text: '4')
						buttons << button(text: '5')
						buttons << button(text: '6')
						operators << button(text: 'Subtract')
						buttons << button(text: '7')
						buttons << button(text: '8')
						buttons << button(text: '9')
						operators << button(text: 'Multiply')
						buttons << button(text: '0')
						buttons.each{ it.actionPerformed = number }
						button(text: '=', actionPerformed: equals)
						button(text: 'C', actionPerformed: { 
							inputBox.text = ''
							operator = null
							first = null
						 })
						operators << button(text: 'Divide')
						operators.each{ it.actionPerformed =  operatorAction}
					}
				}
			}
		}
	}
}
