/*
 * 	This is the read mseg class that reads mseg from client.
 */

package window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class T1Thread extends Thread{
	
	Socket newSock; 
	ServerWin frame;
	BufferedReader br;
	String InText;  //string to hold incoming text from Client
	
	public T1Thread(Socket newSock, ServerWin frame)
	{
		this.newSock=newSock; //Socket setup
		this.frame=frame;  //Jframe setup
	}
	
	
	public void run()
	{
		try {
			br = new BufferedReader(new InputStreamReader(newSock.getInputStream()));  //read input from Client
		} catch (Exception e1) {
			frame.setTitle("Client Disconnected"); //if input failed grey out text field and change Title
			frame.textField.setEditable(false);
		}
		

		
		
		while(newSock.isConnected())
		{
			try {
				InText=br.readLine(); //set string to input from Client
			} catch (Exception e) {
				frame.setTitle("Client Disconnected"); //if connection fails grey out text field and change Title
				frame.textField.setEditable(false);
				break;
			}
			
			if(frame.Close==true)  //check if server window has been closed
			{
				try {
					newSock.close(); //if window has been closed break connection from Client
				} catch (IOException e) {
					e.printStackTrace(); //error mseg
				}
			}
			frame.IncomingText.setText(InText);  //set incoming Client text to server window
		}
	}
	
}
