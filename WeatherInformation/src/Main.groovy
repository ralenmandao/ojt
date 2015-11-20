@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import groovy.swing.SwingBuilder
import wslite.soap.*
import java.awt.BorderLayout as BL
import java.awt.FlowLayout

import javax.swing.JFrame as JF
import javax.swing.JOptionPane

class Main {
	public static void main(args){
		String weatherInformationUrl = 'http://wsf.cdyne.com/WeatherWS/Weather.asmx/GetWeatherInformation'

		def swing = new SwingBuilder()
		def lblState, lblCity, lblForecastDate, lblForecastWD, lblForecastML, lblForecastDH, lblForecastN, lblForecastD
		def myFrame
	
		def sendCityForecast = { zip ->
			def client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
			def response = client.send(SOAPAction:'http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP'){
				body{
					GetCityForecastByZIP(xmlns:"http://ws.cdyne.com/WeatherWS/"){
						ZIP(zip)
					}
				}
			}
			def cR = response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult 
			def values = [success: cR.Success, city: cR.City, state:cR.State, stationCity: cR.WeatherStationCity, forecastResult: cR.ForecastResult.Forecast]
			return values
		}
		
		// position in the array of the displayed forecast
		int displayedForecastIndex = 0
		// collection of the forecast
		def forecasts
		
		def displayForecast = { int index ->
			def f = forecasts[index]
			lblForecastDate.text = f?.Date
			lblForecastWD.text = f?.Desciption
			lblForecastML.text = f.Temperatures.MorningLow
			lblForecastDH.text = f?.Temperatures?.DaytimeHigh
			lblForecastN.text = f?.ProbabilityOfPrecipiation?.Nighttime
			lblForecastD.text = f?.ProbabilityOfPrecipiation?.Daytime
			displayedForecastIndex = index
		}
		
		def contentsPanel
		def forecastPanelContents
		def weatherPanelContents
		swing.edt{
			myFrame = frame(defaultCloseOperation: JF.EXIT_ON_CLOSE,
				  visible: true,
				  layout: new BL(),
				  pack: true
				  ){
				  mainPanel = panel(layout: new BL()){
					  panel(constraints: BL.NORTH){
						  hbox{
							  label(text: "Get ")
							  cboType = comboBox(items: ['Forecast', 'Weather'], id:'cboType1', actionPerformed: {
							  	if(cboType1.selectedIndex == 0){
									  contentsPanel.removeAll()
									  contentsPanel.add(forecastPanelContents)
									  myFrame.pack()
								  }else{
								  	  contentsPanel.removeAll()
									  contentsPanel.add(weatherPanelContents)
									  myFrame.pack()
								  }
							  })
							  label(text: 'Zip')
							  textField(columns: 10, id:'zip')
							  button(text: "View", actionPerformed:{ e ->
							  	if(cboType1.selectedIndex == 0){
									  println 'Forecast'
									  def result = sendCityForecast(zip.text)
									  if(result['success'] == 'false'){
										  JOptionPane.showMessageDialog(myFrame, "Cannot find ${zip.text}")
									  }else{
									  	  lblCity.text = result['city']
										  lblState.text = result['state']
										  forecasts = result['forecastResult']
										  displayForecast(displayedForecastIndex)
										  next.enabled = true
									  }
								  }else if(cboType1.selectedIndex == 1){
								  	println 'weather'
								  }
							  })
						  }
					  }
					  contentsPanel = panel(constraints: BL.CENTER,
						    layout: new BL(),
							visible: true,
							id: 'forecast'){
							forecastPanelContents = vbox(constraints: BL.CENTER){
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"State : ")
									lblState = label(text:"-")
								}
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"City : ")
									lblCity = label(text:"-")
								}
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"Date : ")
									lblForecastDate = label(text:"-", id: 'forecastDate')
								}
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"Weather Description : ")
									lblForecastWD = label(text:"-", id: 'forecastWD')
								}
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"Morning Low : ")
									lblForecastML = label(text:"-", id: 'forecastML')
								}
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"Daytime High : ")
									lblForecastDH = label(text:"-", id: 'forecastDH')
								}
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"Nighttime : ")
									lblForecastN = label(text:"-", id: 'forecastN')
								}	
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"Daytime : ")
									lblForecastD = label(text:"-", id: 'forecastD')
								}
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									button(text: "Previous", id: 'previous', enabled: false, actionPerformed: {
										if(!(displayedForecastIndex - 1 < 0)){
											displayForecast(displayedForecastIndex - 1)
										}
										if(displayedForecastIndex - 1 < 0){
											previous.enabled = false
										}else{
											previous.enabled = true
										}
										if(displayedForecastIndex + 1 > 7){
											next.enabled = false
										}else{
											next.enabled = true
										}
									})
									button(text: "Next",enabled: false, id: 'next', actionPerformed: {
										if(!(displayedForecastIndex + 1 > 7)){
											displayForecast(displayedForecastIndex + 1)
										}
										if(displayedForecastIndex - 1 < 0){
											previous.enabled = false
										}else{
											previous.enabled = true
										}
										if(displayedForecastIndex + 1 >= 7){
											next.enabled = false
										}else{
											next.enabled = true
										}
									})
								}
							}
							
							weatherPanelContents = vbox{
								panel(layout: new FlowLayout(FlowLayout.LEFT)){
									label(text:"Daytime : ")
									lblForecastD = label(text:"-", id: 'forecastD')
								}
							}
							
							panel()
					  }
				  }
			}
		}
	}
}
