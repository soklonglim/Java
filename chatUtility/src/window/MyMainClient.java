/*
 * 	this is main class that a new socket connected to port 1201.
 * this class also called two other threads, read thread and write thread, to 
 * communicate with the server.
 */

package window;

import java.net.Socket;

public class MyMainClient {

	public static void main(String[] args) throws Exception {
		Socket sock=new Socket("localhost",1201);	//create new socket connecting to port 1201
		ClientWin frame=new ClientWin();	//pop up client chat window
		
		ClientThread1 t1=new ClientThread1(sock, frame); //call write thread to send mseg to server
		t1.start();
		ClientThread2 t2=new ClientThread2(sock, frame); //call read thread to read mseg from server
		t2.start();
	}

}
