/*
 * 	this is the read mseg class that read all the mseg from server.
 */

package window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread2 extends Thread{
	Socket sock;
	BufferedReader br;
	String InText;
	ClientWin frame;
	
	//constructor
	public ClientThread2(Socket sock, ClientWin frame)
	{
		this.sock=sock;
		this.frame=frame;
	}
	
	public void run()
	{
		try {
			br=new BufferedReader(new InputStreamReader(sock.getInputStream()));	//for reading mseg from server
		} catch (IOException e1) {
			frame.setTitle("Server Disconnected");	//when server is not responding(could be quit)
			frame.textFieldClient.setEditable(false);
		}//get from server
		
		if(sock.isBound()==false)
		{
			frame.setTitle("Server Disconnected");	//when server is not responding(could be quit)
			frame.textFieldClient.setEditable(false);	
		}
		
		while(sock.isConnected())	
		{
			frame.setTitle("Connected to Server");	//tell user about connection
			try {
				InText=br.readLine();	//get incoming mseg from server
			} catch (Exception e) {
				frame.setTitle("Server Disconnected");	//when server is not responding(could be quit)
				frame.textFieldClient.setEditable(false);
				break;
			}
			
			frame.IncomingTextClient.setText(InText); //display incoming mseg in the text field
		}

	}

}
