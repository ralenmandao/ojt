import wslite.soap.*
import groovy.swing.SwingBuilder
import groovy.swing.impl.TableLayout;

import javax.swing.*
import javax.swing.table.DefaultTableModel

import java.awt.GridBagConstraints as GC

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
				def setValues = {  values ->
					def res = values
					WeatherAPI.list = []
					def m = []
					res.each{ k, v ->
						println "${k} ${v}"
						m << ['property':k, 'value':v]
					}
					prtable.model = tableModel(list:m) {
						closureColumn(header:'Property', read:{row -> return row.property})
						closureColumn(header:'Value', read:{row -> return row.value})
					}
				}
				gridBagLayout()
				comboBox(
					items: ['Forecast', 'Weather', 'Weather Information'],
					id: 'cboChoices',
					constraints: new GC(gridx: 0, gridy: 0, fill:GC.HORIZONTAL)
					)
				button(
					text: "GET",
					constraints: new GC(gridx: 1, gridy: 0, fill:GC.HORIZONTAL, gridwidth: GC.REMAINDER),
					actionPerformed:{
						if(cboChoices.selectedIndex == 0){
							def val = JOptionPane.showInputDialog(mainFrame, "Zip")
							if(!val) return
							setValues(WeatherAPI.getCityForecastByZip(val))
						}else if(cboChoices.selectedIndex == 1){
							def val = JOptionPane.showInputDialog(mainFrame, "Zip")
							if(!val) return
							
							setValues(WeatherAPI.getCityWeatherByZip(val))
						}else{
							setValues(WeatherAPI.getWeatherInformation())
						}
					})
				scrollPane(
					constraints: new GC(gridx:0 , gridy:1, fill:GC.HORIZONTAL, gridwidth: 2)){
					prtable = table(id:'propertyTable'){
					   tableModel{
						  propertyColumn(header: "Property" ,propertyName: 'title', width: 20)
						  propertyColumn(header: "Value" ,propertyName: 'speakerName')
					   }
					}
				}
			}
		}
	}
}
