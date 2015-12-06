package application;
	
import java.util.Scanner;


public class Main {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception{		
		String choice = "";
		// The program wont exit until the user type "exit"
		while(!choice.equals("exit")){
			System.out.print("> ");
			choice = sc.nextLine();
			if(choice.equals("help")){
				showHelp();
			}else if(choice.matches("send (saw|gbn) .*? .*? \\d+ \\d+")){ // match send <gbn || saw> <file> <ip> <port> <simulation>
				// handle and display an error if it occurs
				try{
					String[] str = choice.split(" ");
					String protocol = str[1];
					String file = str[2];
					String ip = str[3];
					int port = Integer.parseInt(str[4]);
					int sim = Integer.parseInt(str[5]);
					System.out.println("Sending data...");
					if(protocol.toLowerCase().equals("saw")){
						// send the file
						SAWSender sender = new SAWSender(ip, port, sim, file);
						sender.send();
					}else{
						// send the file
						GBNSender sender = new GBNSender(ip, port, sim, file);
						sender.send();
					}
					System.out.println("Data sent...");
				}catch(Exception e){
					System.out.println("\tError please try again!");
				}
			}else if(choice.matches("receive (saw|gbn) \\d+ .*?")){
				// handle and display an error if it occurs
				try{
					String[] str = choice.split(" ");
					String protocol = str[1];
					int port = Integer.parseInt(str[2]);
					String file = str[3];
					if(protocol.toLowerCase().equals("saw")){
						System.out.println("Waiting for data...");
						SAWReceiver saw = new SAWReceiver(port, file);
						saw.receive();
						System.out.println("Finish");
					}else{
						System.out.println("Waiting for data...");
						GBNReceiver gbn = new GBNReceiver(port, file);
						gbn.receive();
						System.out.println("Finish");
					}
				}catch(Exception e){
					System.out.println("\tError please try again!");
				}
			}else if(choice.equals("exit")){
				// Exit the program
				System.exit(0);
			}else{
				// show the available commands each time user type unrecognize menu
				showHelp();
			}
		}
	}
	
	// show the available commands
	private static void showHelp(){
		System.out.println("\tHelp");
		System.out.println("\tsend <gbn || saw> <file> <ip> <port> <simulation>");
		System.out.println("\treceive <gbn || saw> <port> <path>");
		System.out.println("\texit");
	}
}
