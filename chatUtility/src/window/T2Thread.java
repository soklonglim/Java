/*
 * 	This is the send mseg class that sends mseg from server to client.
 */

package window;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class T2Thread extends Thread{

	Socket newSock;
	ServerWin frame;
	PrintWriter pw;
	String OutText; //String to hold output text from server

	public T2Thread(Socket newSock, ServerWin frame)
	{
		this.newSock=newSock;  //socket setup
		this.frame=frame;  //Jframe setup
	}
	
	public void run ()
	{
		try {
			pw = new PrintWriter(newSock.getOutputStream()); //print output from server
		} catch (Exception e) {
			frame.setTitle("Client Disconnected"); //if failed the Client has broken connection
			frame.textField.setEditable(false);
		}
	
		while(newSock.isConnected())
		{
			OutText=frame.getOutput(); //set string to server output
			pw.println(OutText); //print output
			pw.flush(); //push output to Client
			
			if(frame.Close==true)  //check if server window has been closed
			{
				try {
					newSock.close(); //break connection from Client
				} catch (IOException e) {
					e.printStackTrace();	//error mseg 
				}
			}
		}

	}

}
