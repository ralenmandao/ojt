import javax.swing.*
import java.awt.*

class SimpleGUI {
	static void main(args){
		def sb = new StringBuilder()
		sb.edit{
			frame(visible: true,
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
				pack: true,
				layout: new BorderLayout()){
				
			}
		}
	}
}
