package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class SAWReceiver {
	
	private int PacketHist ;
	private boolean isSuccess ;
	private DatagramPacket PacketACK ;
	private int listener_port;
	private DatagramSocket AckSocket;
	private byte db_receive[] ;
	private DatagramPacket p_received ;
	private FileOutputStream FileReceiver ;
	private byte ReceiveData[] ;
	private DatagramSocket SAWReceiverSocket;
	
	public SAWReceiver(int listener_port, String file) throws SocketException, FileNotFoundException {
		this.listener_port = listener_port;
		
		isSuccess = false ;
		
		PacketHist = -1 ;
		
		SAWReceiverSocket = new DatagramSocket(listener_port);
		AckSocket = new DatagramSocket();
		
		FileReceiver = new FileOutputStream(new File(file) , true);
	}
	
	public boolean receive() throws IOException {
		
		while (!isSuccess) {

			db_receive = new byte[1024];
			p_received = new DatagramPacket(db_receive,
						db_receive.length);
			SAWReceiverSocket.receive(p_received);
			ReceiveData = p_received.getData();
			int packet_size = p_received.getLength();
			int packet_num = getNum() ;
			
			if (packet_num != PacketHist) {
					boolean isSuccessOk = ReceiveData[2] > 0 ;
					if (isSuccessOk) {
						isSuccess = true;
					}
					FileReceiver.write(ReceiveData, 3, packet_size - 3);
			}

			InetAddress ipAddress = p_received.getAddress();
					
			byte[] ack_b = new byte[1];
			ack_b[0] = (byte) packet_num;
			PacketACK = new DatagramPacket(ack_b, ack_b.length,
							ipAddress, listener_port + 1);
			AckSocket.send(PacketACK);
					
			PacketHist = packet_num;
					
			if(isSuccess){
				PacketHist = -1;	
				isSuccess = true ;
				FileReceiver.close();
			}
				
		}
		
		SAWReceiverSocket.close();
		AckSocket.close();
		return true;
		
	}
	
	private int getNum(){
		return (0x0000FF00 & (ReceiveData[0] << 8)) | (0x000000FF & ReceiveData[1]) ;
	}
}