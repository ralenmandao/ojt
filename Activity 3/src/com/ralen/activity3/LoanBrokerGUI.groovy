package com.ralen.activity3;

import static groovyx.javafx.GroovyFX.start

import java.lang.Thread.State;

import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.Image
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javafx.scene.chart.PieChart
import com.ralen.activity3.algorithm.LoanBrokerAlgorithm;
import com.ralen.activity3.model.Customer
import com.ralen.activity3.service.LoanBrokerService
import javafx.collections.FXCollections

public class LoanBrokerGUI {
	
	@Autowired
	LoanBrokerService lb;
	
	/* HELPER METHODS */
	def error = { header, body ->
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setContentText(body)
		alert.setHeaderText(header)
		alert.showAndWait()
	}
	
	/* SHOW THE GUI */
	void show(){
		start {
			stage(title: "GridPane Demo", width: 703, height: 380, visible: true) {
				Scene myScene = scene(fill: BLACK, id: "myscene") {
					hbox{
						stackPane(id: 'mainPane'){
							gridPane(hgap: 5, vgap: 10, padding: 25, alignment: "top_center",margin:[20,20,20,20]) {
								columnConstraints(minWidth: 50, halignment: "right")
								columnConstraints(prefWidth: 250, hgrow: 'always')
		
								label("Loan Broker V1", style: "-fx-font-size: 18px;",
								row: 0, columnSpan: 2, halignment: "center",
								margin: [0, 0, 10], id: 'lblTitle')
		
								label("Name", hgrow: "never", row: 1, column: 0)
								textField(promptText: "Customer Name", row: 1, column: 1 , id: 'txtName')
		
								label("SSN", row: 2, column: 0)
								textField(promptText: "SSN", row: 2, column: 1, id: 'txtSSN')
		
								label("Amount", row: 3, column: 0, valignment: "baseline")
								textField(promptText: "Amount", row: 3, column: 1, id: 'txtAmount')

								label("Years", row: 4, column: 0, valignment: "baseline")
								textField(promptText: "Years", row: 4, column: 1, id: 'txtYears')
								
								button("Review", row: 5, column: 1, halignment: "right",id: 'record-sales'){
									onMouseClicked{
										mainPane.getChildren().add(
											stackPane(id : 'loading', visible: true){
												imageView(image: new Image("gears.gif")){
													rotateTransition(500.ms, delay: 200.ms, interpolator: EASE_OUT, to: 180.0).playFromStart()
													scaleTransition(500.ms, delay: 200.ms, interpolator: EASE_OUT, to: 0.5,onFinished: {
														javafx.application.Platform.runLater({
																if(txtName.text.size() == 0){
																	error("Name field is empty", "Please enter your name")
																	mainPane.getChildren().remove(loading);
																	return;
																}else if(!txtSSN.text.isNumber()){
																	error("Invalid SSN", "Please enter a valid SSN")
																	mainPane.getChildren().remove(loading);
																	return;
																}else if(!txtAmount.text.isNumber()){
																	error("Invalid amount", "Please enter a valid amount")
																	mainPane.getChildren().remove(loading);
																	return;
																}else if(!txtYears.text.isNumber()){
																	error("Invalid years", "Please enter a valid year")
																	mainPane.getChildren().remove(loading);
																	return;
																}
																
																// create a new customer
																def customer = new Customer(name: txtName.text, ssn: Integer.parseInt(txtSSN.text),
																	 loanAmount: Integer.parseInt(txtAmount.text), term: Integer.parseInt(txtYears.text))
																
																def loan = lb.loan(customer)
																
																if(loan.accepted){
																	 result.text = "Accepted"
																	 result.setStyle("""-fx-background-color:
																				        linear-gradient(#f0ff35, #a9ff00),
																				        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%)
																					""")
																} else {
																	result.text = "Rejected"
																	result.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00)")
																}
																atxtName.text = customer.name
																atxtSSN.text = customer.ssn
																atxtAmount.text = customer.loanAmount
																atxtTotal.text = (customer.total).round(2)
																atxtCreditScore.text = customer.creditScore
																atxtBank.text = loan.bank.bankName
																atxtRate.text = loan.bank.rate.round(2)
																atxtYearly.text = ((customer.total).round(2) / customer.term).round(2)
																mainPane.getChildren().remove(loading)
																t1.expanded = true
															} as Runnable);
													}).playFromStart()
												}
											}
										)
									}
								}
							}
						}
						
						accordion() {
							titledPane(id: "t1", text: "Customer Details", expanded: true) {
								content {
									gridPane(hgap: 5, vgap: 10, padding: 25, alignment: "top_left"){
										label("Name :", row: 0, column: 0)
										textField(row: 0, column: 1, id: 'atxtName', editable: false)
										label("SSN :", row: 1, column: 0)
										textField(row: 1, column: 1, id: 'atxtSSN', editable: false)
										label("Amount :", row: 2, column: 0)
										textField(row: 2, column: 1, id: 'atxtAmount', editable: false)
										label("Credit Score :", row: 3, column: 0)
										textField(row: 3, column: 1, id: 'atxtCreditScore', editable: false)
										label("Yearly :", row: 4, column: 0)
										textField(row: 4, column: 1, id: 'atxtYearly', editable: false)
										label("Total :", row: 5, column: 0)
										textField(row: 5, column: 1, id: 'atxtTotal', editable: false)
										label("", row: 6,column: 1, columnSpan: 2, id: 'result')
									}
								}
							}
							titledPane(id: "t2", text: "Lender Details") {
								content {
									gridPane(hgap: 5, vgap: 10, padding: 25, alignment: "top_left"){
										label("Bank :", row: 0, column: 0)
										textField(row: 0, column: 1, id: 'atxtBank')
										label("Rate :", row: 1, column: 0)
										textField(row: 1, column: 1, id: 'atxtRate')
									}
								}
							}
						}
					}
				}
				myScene.stylesheets.add("style.css")
			}
		}
	}
}
