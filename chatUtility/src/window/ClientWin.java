/*
 * 	this is the class that make a client chat window. there are two text fields.
 * 1) for sending mseg to server(editable), 2) for displaying mseg from server(un-editable)
 */

package window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientWin extends JFrame {
	public String Output;
	public String Input;
	public int keycode;
	public JPanel contentPane;
	public JTextField textFieldClient;
	public JTextField IncomingTextClient;
	
	//constructor
	public ClientWin() {
		setTitle("Looking for Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBroadcast = new JLabel("Outgoing Mesg");
		lblBroadcast.setBounds(10, 26, 80, 14);
		
		contentPane.add(lblBroadcast);
		
		textFieldClient = new JTextField();
		textFieldClient.setBounds(10, 51, 414, 28);
		contentPane.add(textFieldClient);
		textFieldClient.setColumns(10);
		textFieldClient.addKeyListener(new KeyAdapter()
		{
		@Override
		public void keyPressed(KeyEvent arg0)
		{
			keycode=arg0.getKeyCode();	//10 is the code of enter key
			if(keycode ==10)
			{
				Output=textFieldClient.getText(); //get entry text
				//System.out.println(textFieldClient.getText());
				textFieldClient.setText(""); //clear the entry text each time after user hit enter(after sending the mseg)
			}
		}
		});

		JLabel lblIncomingMseg = new JLabel("Incoming Mesg");
		lblIncomingMseg.setBounds(10, 106, 86, 14);
		contentPane.add(lblIncomingMseg);
		
		IncomingTextClient = new JTextField();
		IncomingTextClient.setEditable(false);
		IncomingTextClient.setBounds(10, 131, 414, 28);
		contentPane.add(IncomingTextClient);
		IncomingTextClient.setColumns(5);
	}

	public String getOutput()		//method to get the typed mseg from client on the text field
	{	
		while(keycode != 10)
		{
			Output=textFieldClient.getText();
		}
		return Output;
	}

}
