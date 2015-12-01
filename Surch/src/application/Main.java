package application;
	
import java.util.Scanner;


public class Main {
	
	private static final Scanner scan = new Scanner(System.in);
	private static String input = "";
	
	public static void main(String[] args) throws Exception{
		while(!input.equals("3")){
			System.out.println("1. Send File");
			System.out.println("2. Receive File");
			System.out.println("3. Exit");
			System.out.print("Input : ");
			input = scan.nextLine();
			switch(input){
			case "1":
				System.out.println("\ta. SAW");
				System.out.println("\tb. GBN");
				System.out.print("\tInput : ");
				input = scan.nextLine();
				try{
					if(input.equals("a")){
						System.out.print("\n\tFile : ");
						String file = scan.nextLine();
						System.out.print("\tDestination IP : ");
						String ip = scan.nextLine();
						System.out.print("\tDestination Port : ");
						int port = scan.nextInt();
						System.out.print("\tSimulation 0-100 : ");
						int simulation = scan.nextInt();
						SAWClient client = new SAWClient(ip, port, simulation, file);
						client.send();
						scan.nextLine();
						System.out.println("\nFile sent!\n");
					}else if(input.equals("b")){
						System.out.print("\n\tFile : ");
						String file = scan.nextLine();
						System.out.print("\tDestination IP : ");
						String ip = scan.nextLine();
						System.out.print("\tDestination Port : ");
						int port = scan.nextInt();
						System.out.print("\tSimulation 0-100 : ");
						int simulation = scan.nextInt();
						GBNClient client = new GBNClient(ip, port, simulation, file);
						client.send();
						scan.nextLine();
						System.out.println("\nFile sent!\n");
					}else{
						System.out.println();
						continue;
					}
				} catch(Exception e){
					e.printStackTrace();
					System.out.println("Error");
				}
				break;
			case "2":
				System.out.println("\ta. SAW");
				System.out.println("\tb. GBN");
				System.out.print("\tInput : ");
				input = scan.nextLine();
				try{
					if(input.equals("a")){
						System.out.print("\n\tFile : ");
						String file = scan.nextLine();
						System.out.print("\tPort : ");
						int port = scan.nextInt();
						System.out.println("\n\tWaiting for file...");
						SAWServer server = new SAWServer(port, file);
						server.receive();
						System.out.println("\tfile received...\n");
						scan.nextLine();
					}else if(input.equals("b")){
						System.out.print("\n\tFile : ");
						String file = scan.nextLine();
						System.out.print("\tPort : ");
						int port = scan.nextInt();
						System.out.println("\n\tWaiting for file...");
						GBNServer server = new GBNServer(port, file);
						server.receive();
						System.out.println("\tfile received...\n");
						scan.nextLine();
					}else{
						System.out.println();
						continue;
					}
				} catch(Exception e){
					System.out.println("Error");
				}
					break;
				case "3":
					System.exit(0);
					break;
				default:
					System.out.println();
					break;
			}
		}
	}
}
