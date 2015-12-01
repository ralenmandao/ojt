package application;
	
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws Exception{
		Scanner reader = new Scanner(System.in);
		String str = "";
		
		while(!str.equals("0")){
			System.out.println("1. Send(Send and Wait)");
			System.out.println("2. Receive(Send and Wait)");
			System.out.println("3. Send(Go Back N)");
			System.out.println("4. Receive(Go Back N)");
			System.out.println("0. Exit");
			System.out.print(">> ");
			str = reader.nextLine();
			try{
				if(str.equals("1")){
					System.out.println("------------------------------------");
					System.out.print("File path : ");
					String path = reader.nextLine();
					System.out.print("Host : ");
					String host = reader.nextLine();
					System.out.print("Port : ");
					int port = reader.nextInt();
					System.out.print("Data loss simulation (0-99%) : ");
					int sim = reader.nextInt();
					reader.nextLine();
					System.out.println("Sending file " + path);
					SAWClient saw = new SAWClient(host, port, sim, path);
					saw.send();
					System.out.println("File sent!\n");
				}else if(str.equals("3")){
					System.out.println("------------------------------------");
					System.out.print("File path : ");
					String path = reader.nextLine();
					System.out.print("Host : ");
					String host = reader.nextLine();
					System.out.print("Port : ");
					int port = reader.nextInt();
					System.out.print("Data loss simulation (0-99%) : ");
					int sim = reader.nextInt();
					reader.nextLine();
					System.out.println("Sending file " + path);
					GBNClient saw = new GBNClient(host, port, sim, path);
					saw.send();
					System.out.println("File sent!\n");
				}else if(str.equals("2")){
					System.out.println("------------------------------------");
					System.out.print("File path : ");
					String path = reader.nextLine();
					System.out.print("Port : ");
					int port = reader.nextInt();
					reader.nextLine();
					System.out.println("Waiting for file...");
					SAWServer saw = new SAWServer(port, path);
					saw.receive();
					System.out.println("File received!\n");
				}else if(str.equals("4")){
					System.out.println("------------------------------------");
					System.out.print("File path : ");
					String path = reader.nextLine();
					System.out.print("Port : ");
					int port = reader.nextInt();
					reader.nextLine();
					System.out.println("Waiting for file...");
					GBNServer saw = new GBNServer(port, path);
					saw.receive();
					System.out.println("File received!\n");
				}else{
					System.out.println("------------------------------------");
				}
			}catch(Exception e){
				System.out.println("Error please try again!");
			}
		}	
	}
}
