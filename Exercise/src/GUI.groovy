import groovy.swing.SwingBuilder
import java.awt.GridBagConstraints as GC
import javax.swing.JFrame
import javax.swing.JOptionPane


class GUI {
	static void main(args){
		def sb = new SwingBuilder()
		def mainFrame
		def prtable
				
		sb.edt{
			mainFrame = frame(
				visible: true,
				pack: true,
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE
			){
				gridBagLayout()
				comboBox(
					items: ['Fahrenheit to Celsius', 'Celsius to Fahrenheit'],
					id: 'cboChoices',
					constraints: new GC(gridx: 0, gridy: 0, fill:GC.HORIZONTAL)
					)
				textField(
					columns : 5,
					constraints: new GC(gridx: 1, gridy: 0, gridwidth: GC.REMAINDER),
					id: 'input'
					)
				button(
					text: "GET",
					constraints: new GC(gridx: 0, gridy: 1, fill:GC.HORIZONTAL, gridwidth: GC.REMAINDER),
					actionPerformed:{
						if(cboChoices.selectedIndex == 0){
							def res = Something.getCelciusToFahrenheit(input.text)
							JOptionPane.showMessageDialog(mainFrame, "${res} C")
						}else{
							def res = Something.getFahrenheitToCelcius(input.text)
							JOptionPane.showMessageDialog(mainFrame, "${res} F")
						}
					})
			}
		}
	}
}
