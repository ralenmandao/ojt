package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Random;

public class SAWSender {
	
	private File dataSentFile ;
	private int port_number;
	private DatagramSocket SAWClientSocket;
	private DatagramSocket socket_ack;
	private FileInputStream fis_reader;
	private int sim_num ;
	private int packet_loss ;
	private int totalPacket ;
	private byte packet_num ;
	private boolean ack_received ;
	private boolean isTransfered ;
	private InetAddress ip_address ;
	private String host ;
	private String file ;
	
	public SAWSender(String host, int port_number , int sim_num, String file) throws Exception {
		this.host = host ;
		this.port_number = port_number;
		this.sim_num = sim_num ;
		this.file = file;
		SAWClientSocket = new DatagramSocket();
		socket_ack = new DatagramSocket(port_number + 1);
		
		refresh() ;
	}
	
	public boolean send() throws Exception {				
		refresh() ;
		while (!isTransfered) {
					
			int avail_data = fis_reader.available();

			int data_num = 0 ;
			
			totalPacket += 1 ;
			
			if(avail_data >= (1021)){
				data_num = 1021 ;
			}else{
				data_num = avail_data ;
			}
			
			if(avail_data <= (1024 - 3)){
				isTransfered = true ;
			}else{
				isTransfered = false ;
			}

			byte send_byte[] = new byte[data_num + 3];

			send_byte[0] = 0;
			send_byte[1] = packet_num;
			
			if(isTransfered){
				send_byte[2] = (byte)1 ;
			}else{
				send_byte[2] = (byte)0 ;
			}
			
			fis_reader.read(send_byte, 3, data_num);
				
			int sim_rand = new Random(System.currentTimeMillis()).nextInt(99) ;

			if(sim_rand < sim_num){
				packet_loss++ ;
				continue ;
			}

			DatagramPacket sendPacket = new DatagramPacket(send_byte, send_byte.length,
																ip_address, port_number);
			while (!ack_received) {			
				try {
					SAWClientSocket.send(sendPacket);

					byte[] b_ack = new byte[1];
					byte[] ackData ;
					DatagramPacket ackPacket ;
					
					ackPacket = new DatagramPacket(b_ack, b_ack.length);
					socket_ack.setSoTimeout(90);	
					socket_ack.receive(ackPacket);
					
					ackData = ackPacket.getData();
					
					ack_received = (ackData[0] == packet_num);
				} catch (SocketTimeoutException e) {
					// TODO Auto-generated catch block
					ack_received = false ;
				}
					
			}
			
			ack_received = false;
			
			int temp = packet_num + 1 ;
			int tempa = temp % 2 ;
			
			
			packet_num = (byte)tempa;

		}
		
		SAWClientSocket.close();
		socket_ack.close();
		return true;
	}
	
	private void refresh() throws Exception{
		dataSentFile = new File(file) ;
		fis_reader = new FileInputStream(dataSentFile);
		
		totalPacket = 0;
		packet_loss = 0;
		
		ip_address = InetAddress.getByName(host);
		
		packet_num = 0;
		
		ack_received = false;
			
		isTransfered = false;
	}
}
