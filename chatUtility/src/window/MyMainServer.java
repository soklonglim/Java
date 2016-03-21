/*	Team Member	: Brandon Romig, Soklong Lim
 * 	Class		: CSE 223
 * 	Programming Assignment 5 (SeverClient Chat Utility)
 * 	Date		:  June 10, 2015
 * 
 * 	Description: this assignment is about server client chat utility. We will have to make a server side and 
 * a client side which could communication with each other via local host or router. The purpose is to extend 
 * and practice the understanding of Thread in Java. 
 * 
 * 
 * 
 * 	this is the main class of the server. first, it creates a server and waits for connection from server.
 * every new client connects to the server, the server window will pop up for communicating with that 
 * specific client. This server can be connected with multiple clients at the same time with multiple server
 * windows popping up each time new connection kicks in.
 */


package window;

import java.net.ServerSocket;
import java.net.Socket;

public class MyMainServer {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket myServer=new ServerSocket(1201);  //create new Server Socket with port # 1201

		int i=0; //flag for Number of clients
		
		while(true)
		{
			Socket newSock=myServer.accept();  //accept connection from Client
			if(myServer.isBound()==true) //if Client has connected increment Client counter by one
			{
				i++;
			}
			ServerThread st=new ServerThread(newSock, i);  //Create new server Thread for each Client
			st.start(); //start thread
		}
	}
}
