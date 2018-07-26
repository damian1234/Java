package cs.tcd.ie;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import tcdIO.*;
import java.util.Timer;
import java.util.TimerTask;

public class Client extends Node {
	static final int DEFAULT_SRC_PORT = 50000;
	static final int DEFAULT_DST_PORT = 50001;
	static final String DEFAULT_DST_NODE = "localhost";
	int[] ack;
	Timer myTimer;
	int grouping;
	Terminal terminal;
	InetSocketAddress dstAddress;
	ReSender myReSender;
	//int index = 0;
	/**
	 * Constructor
	 * 	 
	 * Attempts to create socket at given port and create an InetSocketAddress for the destinations
	 */
	Client(Terminal terminal, String dstHost, int dstPort, int srcPort) {
		try {
			this.terminal= terminal;
			dstAddress= new InetSocketAddress(dstHost, dstPort);
			socket= new DatagramSocket(srcPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}


	/**
	 * Assume that incoming packets contain a String and print the string.
	 */
	public synchronized void onReceipt(DatagramPacket packet) {
		myTimer.cancel();
		StringContent content= new StringContent(packet);
		String msg = (content.toString()).substring(0, 1);
		int index = Integer.parseInt(msg);
		ack[index] = 0;
		terminal.println("ack" + content.toString());
	}
	
	public synchronized void start() throws Exception {
		byte[] data= null;
		DatagramPacket packet= null;
		data= (terminal.readString("String to send: ")).getBytes();
		grouping = selectGrouping(data);
		ack = new int[grouping];
		for(int i = 0; i < grouping; i++){
			ack[i] = -1;
		}
		for(int j = 0; j*grouping < data.length; j++){
			for(int i = 0; (i < grouping)&&((i + (j*grouping))<data.length); i++){
				if(ack[i] == -1){							//first iteration it sends all packets. if ack[i] == -1 then 
					myTimer = new Timer();					//the packet at i didnt send and then will be sent again
					myReSender = new ReSender();
					byte[] array = new byte[2];
					array[0]= (byte) (i);
					array[1]= data[i+(j*grouping)];
					terminal.println("Sending packet...");
					packet= new DatagramPacket(array, array.length, dstAddress);
					myReSender.getIndex(i);
					socket.send(packet);
					myTimer.scheduleAtFixedRate(myReSender, 100, 100);
					this.wait(500);
				}
			}
			
			this.wait(1000);
			boolean resend = false;
			for(int i = 0;( i <grouping)&&((i + (j*grouping))<data.length); i++){				//checking all packets were sent successfully 
				if(ack[i] ==-1){
					resend = true;
				}
			}
			if(resend){						//re-send failed packets 
				j--;
			}
			else{
				for(int i = 0; i < grouping; i++){
					ack[i] = -1;
				}
			}
		}
	}

	public int selectGrouping(byte[] data){
		if(data.length < 3){
			return 1;
		}
		int i = 3;
		while(((data.length%i)!= 0)&&(i < 6)){ 
			i++;
		}
		return i;
	}
	private class ReSender extends TimerTask{
		int i;
		public void getIndex( int index){
			i = index;
		}
		public void run() {
			ack[i] = -1;
		}
	}

	/**
	 * Test method
	 * 
	 * Sends a packet to a given address
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Client");		
			(new Client(terminal, DEFAULT_DST_NODE, DEFAULT_DST_PORT, DEFAULT_SRC_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
