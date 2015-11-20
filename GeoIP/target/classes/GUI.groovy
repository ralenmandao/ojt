import groovy.swing.SwingBuilder
import java.awt.GridBagConstraints as GC
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.border.EmptyBorder;
import javax.swing.*
import java.awt.*


class GUI {
	static void main(args){
		def sb = new SwingBuilder()
		def mainFrame
		def parameters = []
		def prtable
				
		sb.edt{
			mainFrame = frame(
				visible: true,
				pack: true,
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE
			){
			panel(border: new EmptyBorder(new Insets(10, 10, 10, 10))){
				vbox{
					hbox{
						label(
							text: 'Services'
							)
						comboBox(
							items: ['Directions', 'Distance Matrix', 'Elevation', 'Geocoding', 'Geolocation', 'Roads', 'Time Zone', 'Google Place']
							)
						button(
							text: "GET"
							)
					}
					scrollPane(){
						prtable = table(id:'propertyTable'){
						   tableModel{
							  propertyColumn(header: "Parameter" ,propertyName: 'title')
							  propertyColumn(header: "Value" ,propertyName: 'speakerName')
						   }
						}
					}
					hbox{
						label(
							text: "Name : "
							)
						textField(id : 'txtName')
						label(
							text: "Value : "
							)
						textField(id : 'txtValue')
						button(
							text: "Add Parameter",
							actionPerformed: {
								if(txtName.text.size() == 0 || txtValue.text.size() == 0) return
								parameters << ['property': txtName.text, 'value': txtValue.text]
								prtable.model = tableModel(list:parameters) {
									closureColumn(header:'Property', read:{row -> return row.property})
									closureColumn(header:'Value', read:{row -> return row.value})
								}
							}
							)
					}
				}
			}
		}
	}
}
}
