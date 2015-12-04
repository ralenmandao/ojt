import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder;

class Main {
	static void main(args){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml")
		def header = [:]
		def a = new Account()
		header.put('ACCOUNT_EXPIRY', 'NEVER')
		Message<Account> m = MessageBuilder.withPayload(a)
		.setHeader('ACCOUNT_EXPIRY', 'NEVER')
		.build()
	}
}