import groovy.swing.SwingBuilder
import java.awt.GridBagConstraints as GC
import javax.swing.JFrame
import javax.swing.JOptionPane


class GUI {
	static void main(args){
		def sb = new SwingBuilder()
		def mainFrame
		def prtable
		def currencies = """
			AFA or ALL or DZD or ARS or AWG or AUD or BSD or BHD or BDT or BBD or BZD or BMD or BTN or 
			BOB or BWP or BRL or GBP or BND or BIF or XOF or XAF or KHR or CAD or CVE or KYD or CLP or CNY or COP or 
			KMF or CRC or HRK or CUP or CYP or CZK or DKK or DJF or DOP or XCD or EGP or SVC or EEK or ETB or EUR or 
			FKP or GMD or GHC or GIP or XAU or GTQ or GNF or GYD or HTG or HNL or HKD or HUF or ISK or INR or IDR or 
			IQD or ILS or JMD or JPY or JOD or KZT or KES or KRW or KWD or LAK or LVL or LBP or LSL or LRD or LYD or 
			LTL or MOP or MKD or MGF or MWK or MYR or MVR or MTL or MRO or MUR or MXN or MDL or MNT or MAD or MZM or 
			MMK or NAD or NPR or ANG or NZD or NIO or NGN or KPW or NOK or OMR or XPF or PKR or XPD or PAB or PGK or 
			PYG or PEN or PHP or XPT or PLN or QAR or ROL or RUB or WST or STD or SAR or SCR or SLL or XAG or SGD or 
			SKK or SIT or SBD or SOS or ZAR or LKR or SHP or SDD or SRG or SZL or SEK or CHF or SYP or TWD or TZS or 
			THB or TOP or TTD or TND or TRL or USD or AED or UGX or UAH or UYU or VUV or VEB or VND or YER or YUM or 
			ZMK or ZWD or TRY
		""".split(' or ')
		currencies = currencies*.trim()
				
		sb.edt{
			mainFrame = frame(
				visible: true,
				pack: true,
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE
			){
				gridBagLayout()
				label(
					text: "From",
					constraints: new GC(gridx: 0, gridy: 0) 
					)
				comboBox(
					items: currencies,
					id: 'cboFrom',
					constraints: new GC(gridx: 1, gridy: 0, gridwidth: GC.REMAINDER)
					)
				label(
					text: "To",
					constraints: new GC(gridx: 0, gridy: 1)
					)
				comboBox(
					items: currencies,
					id: 'cboTo',
					constraints: new GC(gridx: 1, gridy: 1, gridwidth: GC.REMAINDER)
					)
				textField(
					columns : 5,
					constraints: new GC(gridx: 0, gridy: 2, gridwidth: GC.REMAINDER, fill:GC.HORIZONTAL),
					id: 'input'
					)
				button(
					text: "GET",
					constraints: new GC(gridx: 0, gridy: 3, fill:GC.HORIZONTAL, gridwidth: GC.REMAINDER),
					actionPerformed:{
						if(!input.text.isNumber()) {
							JOptionPane.showMessageDialog(mainFrame, "${input.text} is not a number")
							return
						}
						def rate = CurrencyConverterAPI.convertionRate(cboFrom.selectedItem,cboTo.selectedItem).toString()
						JOptionPane.showMessageDialog(mainFrame, "${Double.parseDouble(rate) * Double.parseDouble(input.text)} ${cboTo.selectedItem}")
					})
			}
		}
	}
}
