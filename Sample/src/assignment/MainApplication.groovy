package assignment

class MainApplication {
	static void main(args){
		println 'Class Stats program by Bassam Quota'
		String command = ""
		while(true){
			print "> "
			command = System.in.newReader().readLine()
			switch(command.toLowerCase()){
				case ~/^load \w+$/:
					String fileName
					try{ 
						fileName = new File(command.split(',')[1]).getText('UTF-8') 
					}catch(all){
					 	println '\tInvalid filepath' 
						continue
					}
					println fileName
				break
				default:
				println 'default'
				break
			}
		}
	}
}
