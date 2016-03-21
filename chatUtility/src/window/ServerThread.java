/*
 * 	this is the class that calls two other classes for server to communicate with client.
 * 1) for sending mseg to client, 2) for reading mseg from client
 */

package window;

import java.net.Socket;


public class ServerThread extends Thread{
	
	Socket newSock;
	ServerWin frame;
	int i=0;
	
	public ServerThread(Socket newSock, int i) { //constructor for server thread
		this.newSock=newSock; //accepted socket from Client
		this.i=i; //Client counter
		frame=new ServerWin(i); //Jframe setup
		frame.setVisible(true); //Show Jframe

	}

	public void run()
	{
		T1Thread t1=new T1Thread(newSock, frame);  //create one thread for input
		t1.start();  //run input thread
		T2Thread t2=new T2Thread(newSock, frame);  //create one thread for output
		t2.start(); //run output thread
	}


}


