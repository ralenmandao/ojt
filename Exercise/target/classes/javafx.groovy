import static groovyx.javafx.GroovyFX.start

class javafx {
	static void main(args){
		start {
			stage(title: 'GroovyFX Hello World', visible: true) {
				scene(fill: GRAY, width: 500, height: 250) {
					gridPane()
					
				}
			}
		}
	}
}
