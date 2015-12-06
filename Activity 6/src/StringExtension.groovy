
class StringExtension {
	static void splitify(final String str){
		str.split('').each{ print "${it}      " }
	}
	
	static String app(final String str, final String app){
		"${str}${app}"
	}
	
	static void printme(final String str){
		print str
	}
	
	static void printlnme(final String str){
		println str
	}
}
