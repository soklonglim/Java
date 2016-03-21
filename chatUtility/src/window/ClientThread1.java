/*
 * 	this is the send mseg class that send all the mseg from client to server
 */



package window;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread1 extends Thread{
	Socket sock;
	PrintWriter pw;
	String OutText;
	ClientWin frame;
	
	//constructor
	public ClientThread1(Socket sock, ClientWin frame)
	{
		this.sock=sock;
		this.frame=frame;
	}

	
	
	public void run()
	{
		try {
			pw=new PrintWriter(sock.getOutputStream());	//for sending mseg to server
		} catch (Exception e) {
			frame.setTitle("Server Disconnected");	//when server is not responding(could be quit)
			frame.textFieldClient.setEditable(false);
		}//write out to server
		
		while(frame.textFieldClient.isEditable()==true)
		{
			frame.setTitle("Connected to Server");	//tell user about connection
			frame.setVisible(true);
			OutText=frame.getOutput();	//get mseg user typed
			pw.println(OutText);	//send mseg
			pw.flush();		//BufferedReader needs flush to send out mseg
		}

	}
}
