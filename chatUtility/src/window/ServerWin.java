/*
 * 	this is the class that make a server chat window. there are two text fields.
 * 1) for sending mseg to client(editable), 2) for displaying mseg from client(un-editable)
 */


package window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ServerWin extends JFrame {

	public int i; //Client counter
	public boolean Close=false; //flag for closing the server window
	public int keycode; // flag to check for server hitting the enter key
	public JPanel contentPane;      //
	public JTextField textField;    // JFrame setup
	public JTextField IncomingText; //
	String Output; //string to hold output from server

	//constructor
	public ServerWin(int i) {
		this.i=i; //setup Client counter
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Close=true;  //check if the server window ha been closed
			}
		});

		setTitle("Waiting for a Client");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Dispose on close will close the window without quitting the program
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("You are now talking to Client #" + (i)); //set title of window with Client number
	

		
		JLabel lblBroadcast = new JLabel("Broadcast");
		lblBroadcast.setBounds(10, 26, 80, 14);
		contentPane.add(lblBroadcast);
		
		textField = new JTextField();
		textField.setBounds(10, 51, 414, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIncomingMseg = new JLabel("Incoming Mesg");
		lblIncomingMseg.setBounds(10, 106, 86, 14);
		contentPane.add(lblIncomingMseg);
		
		IncomingText = new JTextField();
		IncomingText.setEditable(false); 
		IncomingText.setBounds(10, 131, 414, 28);
		contentPane.add(IncomingText);
		IncomingText.setColumns(5);
		
		textField.addKeyListener(new KeyAdapter()
		{
		@Override
			public void keyPressed(KeyEvent arg0)
			{
				keycode=arg0.getKeyCode();
				if(keycode ==10) //check to see if server has hit the enter key
				{
					Output=textField.getText();  //grab text from field
					System.out.println(textField.getText());
					textField.setText(""); //reset field to blank
				}
			}
		});


		
		JButton btnSelfdestruction = new JButton("Self-Destruction");
		btnSelfdestruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);  //Self-Destruction button will crash the server
			}
		});
				
		
		btnSelfdestruction.setBounds(132, 192, 156, 32);
		contentPane.add(btnSelfdestruction);
	}
		
	public String getOutput()	//method to get the typed mseg from server on the text field
	{
		while(keycode != 10) 
		{
			Output=textField.getText(); 
		}
		return Output;
	}

}
