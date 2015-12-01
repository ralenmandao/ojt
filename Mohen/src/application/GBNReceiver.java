package application;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GBNReceiver {

	private int port;
	private int prevPacket = 0;
	private DatagramSocket socketReceived;
	private DatagramSocket socketAck;
	private boolean isTransfered = false;
	private FileOutputStream fosReceived ;
	private String filePath;

	public GBNReceiver(int port, String filePath) throws SocketException {
		this.port = port;
		this.filePath = filePath;
		socketReceived = new DatagramSocket(port);
		socketAck = new DatagramSocket();
	}

	public boolean receive() throws Exception{

		while (!isTransfered) {
			byte buffer[] = new byte[1024];
			DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
			socketReceived.receive(receivedPacket);
			fosReceived = new FileOutputStream(new File(filePath) , true);
			byte receivedData[] = receivedPacket.getData();
		
			int currentPacketSize = receivedPacket.getLength();
			int packetNum = (0x0000FF00 & (receivedData[0] << 8)) | (0x000000FF & receivedData[1]);
			if (packetNum == (prevPacket + 1)) {
				if (receivedData[2] > 0) {
					isTransfered = true;
				}
				fosReceived.write(receivedData, 3, currentPacketSize - 3);
				prevPacket++;
							
			}
	
			InetAddress sender = receivedPacket.getAddress();
			byte[] ack = new byte[2];
			ack[1] = (byte) (prevPacket >>> 8);
			ack[0] = (byte) prevPacket;
						
			DatagramPacket ackPacket = new DatagramPacket(ack, ack.length,
						sender, port + 1);
			socketAck.send(ackPacket);
						
			if(isTransfered){
				fosReceived.close();
				prevPacket = 0;
				isTransfered = true ;
			}
		}
		
	socketReceived.close();
	socketAck.close();
	return true;
		
	}
}
