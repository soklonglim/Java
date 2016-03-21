/*
 * Soklong Lim
 * CSE 223/Programming Assignment 4 (Tic-Tac-Toe Game)
 * May 29, 2015
 * 
 * Description
 * 	this program is about Tic-Tac-Toe Game which allows player to choose either player or computer 
 * move first, choose sign either 'O' or 'X', and the level of computer move. There are three possibilities,
 * player won, computer won, or tie game. the following is the map of the variables and some basics initialization.
 * 
 * 		remember if spot available			remember who took the spot			remember sign of each spot
 * 		S0	|	S1	|	S2					sp0	|	sp1	|	sp2					st0	|	st1	|	st2
 * 		-------------------					-------------------					-------------------
 * 		S3	|	S4	|	S5					sp3	|	sp4	|	sp5					st3	|	st4	|	st5
 * 		-------------------					-------------------					-------------------
 * 		S6	|	S7	|	S8					sp6	|	sp7	|	sp8					st6	|	st7	|	st8
 * 		(true, false)						(0, 1, 2)							('O' or 'X')
 * 
 */

package ticTacToe;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class MyFrame extends JFrame {

	//variables and objects construction block
	private JPanel contentPane;
	private ButtonGroup startGroup = new ButtonGroup();
	private ButtonGroup signGroup = new ButtonGroup();
	private ButtonGroup levelGroup = new ButtonGroup();
	private JLabel lblNewLabel = new JLabel();
	private JPanel playPanel = new JPanel();
	private JPanel resultPanel = new JPanel();
	private JLabel showLevel = new JLabel();
	
	private int x, y, click, level, signState, randSpot, moveFirstState;
	private int sp0, sp1, sp2, sp3, sp4, sp5, sp6, sp7, sp8;	//remember the owner of the taken spot
	int k = 0;
	
	private String dString; //temporary holder of either 'O' or 'X'
	private String st0, st1, st2, st3, st4, st5, st6, st7, st8; //remember the sign of each take spot
	
	private boolean foundSpotFlag=false;	//flag for computer move
	private boolean sign;	//flag of initial setup
	private boolean dFlag = false;	//defend flag
	private boolean oFlag = false; 	//offend flag
	private boolean startFlag = false;
	private boolean playerTurn = false;
	private boolean S0, S1, S2, S3, S4, S5, S6, S7, S8; //true==taken & false==available
	private boolean L012, L036, L048, L147, L258, L246, L345, L678;	//true==taken & false==available
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	public MyFrame() {
		setTitle("Welcome To Tic-Tac-Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		//welcome panel 		
		final JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(102, 205, 170));
		welcomePanel.setBounds(0, 0, 434, 261);
		contentPane.add(welcomePanel);
		welcomePanel.setLayout(null);
		
		JLabel lblStartFirst = new JLabel("Start First");
		lblStartFirst.setForeground(new Color(0, 0, 139));
		lblStartFirst.setBackground(new Color(0, 0, 139));
		lblStartFirst.setBounds(10, 11, 107, 14);
		welcomePanel.add(lblStartFirst);
		
		final JRadioButton rdbtnPlayer = new JRadioButton("Player");
		rdbtnPlayer.setForeground(new Color(199, 21, 133));
		rdbtnPlayer.setBackground(new Color(102, 205, 170));
		rdbtnPlayer.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				moveFirstState = e.getStateChange();	//1==player move first & 2==computer move first
				if(moveFirstState==1){	//set move first state
					playerTurn = true;	//player start first
				} else if(moveFirstState==2) {
					playerTurn = false;	//computer start first
				}
			}
		});
		rdbtnPlayer.setBounds(20, 27, 109, 23);
		rdbtnPlayer.setSelected(true);
		startGroup.add(rdbtnPlayer);
		welcomePanel.add(rdbtnPlayer);
		
		JRadioButton rdbtnComputer = new JRadioButton("Computer");
		rdbtnComputer.setForeground(new Color(199, 21, 133));
		rdbtnComputer.setBackground(new Color(102, 205, 170));
		rdbtnComputer.setBounds(131, 27, 109, 23);
		startGroup.add(rdbtnComputer);
		welcomePanel.add(rdbtnComputer);
		
		JLabel lblChooseSigns = new JLabel("Choose Signs");
		lblChooseSigns.setForeground(new Color(0, 0, 139));
		lblChooseSigns.setBackground(new Color(0, 0, 139));
		lblChooseSigns.setBounds(10, 65, 107, 14);
		welcomePanel.add(lblChooseSigns);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("O");
		rdbtnNewRadioButton.setForeground(new Color(199, 21, 133));
		rdbtnNewRadioButton.setBackground(new Color(102, 205, 170));
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				signState = e.getStateChange(); //1 = '0' , 2 = 'X'
				if(signState==1){	//set selected sign
					sign = true; //'O' selected
				} else {
					sign = false;	//'X' selected
				}
			}
		});
		rdbtnNewRadioButton.setBounds(20, 81, 109, 23);
		rdbtnNewRadioButton.setSelected(true);
		signGroup.add(rdbtnNewRadioButton);
		welcomePanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnX = new JRadioButton("X");
		rdbtnX.setForeground(new Color(199, 21, 133));
		rdbtnX.setBackground(new Color(102, 205, 170));
		rdbtnX.setBounds(131, 81, 109, 23);
		signGroup.add(rdbtnX);
		welcomePanel.add(rdbtnX);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setForeground(new Color(0, 0, 139));
		lblLevel.setBackground(new Color(0, 0, 139));
		lblLevel.setBounds(10, 119, 107, 14);
		welcomePanel.add(lblLevel);
		
		JRadioButton rdbtnAmateur = new JRadioButton("Amateur");
		rdbtnAmateur.setForeground(new Color(199, 21, 133));
		rdbtnAmateur.setBackground(new Color(102, 205, 170));
		rdbtnAmateur.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {	//1=amateur selected, 2=expert selected
				level = e.getStateChange(); //level selection
				if(level==1){
					showLevel.setText("Amateur Level");	//show the computer level while playing
					showLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
					showLevel.setForeground(new Color(255, 150, 10));
				} else {
					showLevel.setText("Expert Level"); //show the computer level while playing
					showLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
					showLevel.setForeground(new Color(255, 150, 10));
				}
			}
		});
		rdbtnAmateur.setBounds(20, 135, 109, 23);
		rdbtnAmateur.setSelected(true);
		levelGroup.add(rdbtnAmateur);
		welcomePanel.add(rdbtnAmateur);
		
		JRadioButton rdbtnExpert = new JRadioButton("Expert");
		rdbtnExpert.setForeground(new Color(199, 21, 133));
		rdbtnExpert.setBackground(new Color(102, 205, 170));
		rdbtnExpert.setBounds(131, 135, 109, 23);
		levelGroup.add(rdbtnExpert);
		welcomePanel.add(rdbtnExpert);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(0, 206, 209));
		btnNewButton.setForeground(new Color(199, 21, 133));
		btnNewButton.setBounds(118, 180, 163, 62);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomePanel.setVisible(false);	//move to next panel
				playPanel.setVisible(true);	//make playing panel visible
				resultPanel.setVisible(true);	//make result and show level visible while playing
				startFlag = true; repaint();	//set flag to draw board game 
				if(playerTurn==false){	//if the game starts by computer move first
					setTitle("Computer Turn"); //change title
					if(sign==true){	//check sign
						dString = "O";
					} else {
						dString = "X";
					}
					if(level==1){ //amateur level selected
						cmptrMove();
					} else {
						cmptrExpert();	//expert level selected
					}
					sign = !sign; playerTurn = true; //switch sign and turn
					setTitle("Player Turn"); //switch title
				}
			}
		});
		welcomePanel.add(btnNewButton);

		//playing panel block
		playPanel.setBackground(new Color(0, 191, 255));
		playPanel.setVisible(false);
		playPanel.addMouseListener(new MouseAdapter() {		//mouse click detection
			@Override
			public void mouseClicked(MouseEvent e) {
				if(winChecker()==0){
					x = e.getX();	//get x coordinate
					y = e.getY();	//get y coordinate
					if(!takenSpot()){	//check if player clicked on the taken spot
						play();			//call play method	
					}
				}
			}
		});
		playPanel.setBounds(0, 0, 261, 261);
		contentPane.add(playPanel);
		playPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		playPanel.setLayout(null);

		//resultPanel block	
		resultPanel.setBounds(300, 25, 125, 196);
		resultPanel.setVisible(false);
		contentPane.add(resultPanel);
		resultPanel.setLayout(null);
		
		//display level block
		showLevel.setBounds(5, 5, 120, 20);
		resultPanel.add(showLevel);

		//game over display	block
		lblNewLabel.setBounds(5, 70, 115, 50);
		resultPanel.add(lblNewLabel);
		lblNewLabel.setVisible(false);
	}
		
	/*
	 * this is the override paint method that draw 4 lines and all
	 * the moves made by player and computer
	 */
	public void paint(Graphics g){
		super.paint(g);	
		if(startFlag==true){	//draw the board game
			g.setColor(Color.RED);
			g.drawLine(90, 0, 90, 290);	//first vertical
			g.setColor(Color.RED);
			g.drawLine(185, 0, 185, 290);	//second vertical			
			g.setColor(Color.RED);
			g.drawLine(0, 115, 266, 115);	//first horizontal
			g.setColor(Color.RED);
			g.drawLine(0, 200, 266, 200);	//second horizontal
		}
	
		g.setFont(new Font("Ariel", 50, 80));

		//the following block is about drawing the taken spot and  
		//remembering all the previous move of both player and computer
		if(S0){
			if(st0.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st0, 23, 100);
		}
		if(S3){
			if(st3.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st3, 23, 190);
		}
		if(S6){
			if(st6.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st6, 23, 275);
		}
		if(S1){
			if(st1.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st1, 110, 100);
		}
		if(S4){
			if(st4.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st4, 110, 190);
		}
		if(S7){
			if(st7.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st7, 110, 275);
		}
		if(S2){
			if(st2.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st2, 200, 100);
		}
		if(S5){
			if(st5.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st5, 200, 190);
		}
		if(S8){
			if(st8.equals("X")){
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.PINK);
			}
			g.drawString(st8, 200, 275);
		} //end of draw either 'O' or 'X'
		
		//draw the winning line 
		Graphics2D g2D = (Graphics2D)g;
		if(L012){
			g2D.setColor(Color.MAGENTA);	//line color
			g2D.setStroke(new BasicStroke(3)); //make thick line
			g2D.drawLine(0, 70, 267, 70);	//line direction
		} else if(L036){
			g2D.setColor(Color.MAGENTA);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(50, 0, 50, 290);	
		} else if(L048){
			g2D.setColor(Color.MAGENTA);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(267, 290, -17, 0);	
		} else if(L147){
			g2D.setColor(Color.MAGENTA);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(140, 0, 140, 290); 
		} else if(L258){
			g2D.setColor(Color.MAGENTA);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(230, 0, 230, 290); 
		} else if(L345){
			g2D.setColor(Color.MAGENTA);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(0, 160, 267, 160); 
		} else if(L678){
			g2D.setColor(Color.MAGENTA);
			g2D.setStroke(new BasicStroke(3));
			g.drawLine(0, 245, 267, 245); 
		} else if(L246){
			g2D.setColor(Color.MAGENTA);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(0, 300, 300, 0); 
		} //end of drawing winning line
	}

	/*
	 * this is the method that detects the location where player clicked the mouse. once
	 * the spot is clicked, the flag of that spot will be set to true and will not register
	 * any click on that spot anymore.
	 */
	public void plyrMove(){	
		switch(click){ //match the spot player click
			case 0:						 								 
					st0 = dString; sp0 = 1;			//remember either player or computer's spot
					S0 = true; break; 		//set flag to be taken and break
			case 1:
					st1 = dString; sp1 = 1;
					S1 = true; break; 
			case 2:
					st2 = dString; sp2 = 1;
					S2 = true; break;
			case 3:
					st3 = dString; sp3 = 1;
					S3 = true; break;  
			case 4:
					st4 = dString; sp4 = 1;
					S4 = true; break;
			case 5:
					st5 = dString; sp5 = 1;
					S5 = true; break;
			case 6:
					st6 = dString; sp6 = 1;
					S6 = true; break; 
			case 7:
					st7 = dString; sp7 = 1;
					S7 = true; break;  
			case 8:
					st8 = dString; sp8 = 1;
					S8 = true; break;
		} //end of switch function
	}
	
	/*
	 * this method is about making the move of computer (amateur level) that will make a random move
	 * based on the generated random number. it will retry with new number if there
	 * is a collision. 
	 */
	public void cmptrMove(){
		randSpot = (int)(Math.random()*100)%9;	//generate random number b/w 0-9
		
		while(foundSpotFlag==false){	//keep finding available spot until succeed
			switch(randSpot){	//match the random number generated to the available spot
				case 0:	
					if(S0==false){
						st0 = dString; sp0 = 2;	//remember either player or computer's spot 
						foundSpotFlag = true; //found spot flag
						S0 = true; break;	//remember taken spot
					} 
				case 1:
					if(S1==false){
						st1 = dString; sp1 = 2;
						foundSpotFlag = true;
						S1 = true; break;
					} 
				case 2:
					if(S2==false){
						st2 = dString; sp2 = 2;
						foundSpotFlag = true;
						S2 = true; break;
					} 
				case 3:
					if(S3==false){
						st3 = dString; sp3 = 2;
						foundSpotFlag = true; 
						S3 = true; break;
					} 
				case 4:
					if(S4==false){
						st4 = dString; sp4 = 2;
						foundSpotFlag = true;
						S4 = true; break;
					} 
				case 5:
					if(S5==false){
						st5 = dString; sp5 = 2;
						foundSpotFlag = true; 
						S5 = true; break;
					} 
				case 6:
					if(S6==false){
						st6 = dString; sp6 = 2;
						foundSpotFlag = true;
						S6 = true; break;
					} 
				case 7:
					if(S7==false){
						st7 = dString; sp7 = 2;
						foundSpotFlag = true; 
						S7 = true; break;
					} 
				case 8:
					if(S8==false){
						st8 = dString; sp8 = 2;
						foundSpotFlag = true; 
						S8 = true; break;
					} 
			} //end of matching spot
			if(foundSpotFlag==false){
				randSpot = (randSpot+2)%9;	//re-generate random number 0-9 when collision
			}
		}
		foundSpotFlag = false; //reset flag for next time use
	} //end of cmptrMove() method
	
	/*
	 * this method is about putting together both computer move method and computer move method
	 * it also controls the sign shifting between each move and call another to check if the winner 
	 * has been found or the game is draw.
	 */
	public void play(){
		setTitle("Player Turn"); //change title
		if(playerTurn==true && winChecker()==0){	//player turn
			if(sign==true){ //check sign
				dString = "O";
			} else {
				dString = "X";
			}
			
			plyrMove(); repaint();	//call player move and repaint the window
			if(winChecker()==1){	//checking winner
				setTitle("Game Over"); //change title
				lblNewLabel.setText("Player Won"); //show winner mseg
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel.setForeground(new Color(255, 0, 0));
				lblNewLabel.setVisible(true); 
			} else if(winChecker()==3){	
				setTitle("Game Over"); //change title 
				lblNewLabel.setText("   Draw"); //show draw mseg
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
				lblNewLabel.setForeground(new Color(255, 0, 0));
				lblNewLabel.setVisible(true);
			} else { //switch sign and turn
				sign = !sign; 
				playerTurn = !playerTurn;
				setTitle("Computer Turn"); //change title
			}
		}

		/*for(int i=0; i<1000000; i++){	//delay loop
			for(int j=0; j<1000; j++){
				for(int k=0; k<10; k++);
			}
		}*/
		
		if(playerTurn==false && winChecker()==0){	//computer turn
			if(sign==true){	//check sign
				dString = "O";
			} else {
				dString = "X";
			}
			
			if(level==1){
				cmptrMove(); repaint(); //call computer move (amateur level) and repaint the window
			} else {
				cmptrExpert(); repaint();	//call computer move (amateur level) and repaint the window
			}
			
			if(winChecker()==2){	//check winner
				setTitle("Game Over"); //change title
				lblNewLabel.setText("Computer Won"); //show winner mseg
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel.setForeground(new Color(255, 0, 0));
				lblNewLabel.setVisible(true);
			} else if(winChecker()==3){
				setTitle("Game Over"); //change title
				lblNewLabel.setText("   Draw"); //show draw mseg
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
				lblNewLabel.setForeground(new Color(255, 0, 0));
				lblNewLabel.setVisible(true);
			} else {	//switch sign and turn
				sign = !sign; 
				playerTurn = !playerTurn;
				setTitle("Player Turn"); //change title
			}
		}
	} //end of play() method
	
	/* 
	 * this method is about checking the winner or tie game
	 * winChecker class
	 * 	return(0) : game continue
	 *  return(1) : player win
	 *  return(2) : computer win
	 *  return(3) : draw
	 */
	public int winChecker(){
		if(sp0==sp1 && sp1==sp2){
			if(sp0==1){
				L012 = true; return(1);
			} else if(sp0==2){
				L012 = true; return(2);
			}
		}
		if(sp0==sp3 && sp3==sp6){
			if(sp0==1){
				L036 = true; return(1);
			} else if(sp0==2){
				L036 = true; return(2);
			}
		}
		if(sp0==sp4 && sp4==sp8){
			if(sp0==1){;
				L048 = true; return(1);
			} else if(sp0==2){
				L048 = true; return(2);
			}
		}
		if(sp3==sp4 && sp4==sp5){
			if(sp3==1){
				L345 = true; return(1);
			} else if(sp3==2){
				L345 = true; return(2);
			}
		}
		if(sp6==sp7 && sp7==sp8){
			if(sp6==1){
				L678 = true; return(1);
			} else if(sp6==2){
				L678 = true; return(2);
			}
		}
		if(sp1==sp4 && sp4==sp7){
			if(sp1==1){
				L147 = true; return(1);
			} else if(sp1==2){
				L147 = true; return(2);
			}
		}
		if(sp2==sp5 && sp5==sp8){
			if(sp2==1){
				L258 = true; return(1);
			} else if(sp2==2) {
				L258 = true; return(2);
			}
		}
		if(sp2==sp4 && sp4==sp6){
			if(sp2==1){
				L246 = true; return(1);
			} else if(sp2==2){
				L246 = true; return(2);
			}
		}
		if(sp0!=0&&sp1!=0&&sp2!=0&&sp3!=0&&sp4!=0&&sp5!=0&&sp6!=0&&sp7!=0&&sp8!=0){
			return(3);
		}
		return(0); //no winner yet
	} //end of winChecker() method
	
	/*
	 * this method is about the computer move (expert level)
	 */
	public void cmptrExpert(){
		//this is called Defend block of expert computer move.
		//it will check and decide either to prevent player from winning or 
		//make next move to win the game
		dFlag = false; oFlag = false;	//reset flag each time the method is called
		if(sp4==sp0 || sp4==sp8 || sp0==sp8){
			if(sp4==sp0 && sp4!=0 && sp8==0){
				if(sp4==2){	//2=make move to win
					st8 = dString; sp8 = 2;  //remember sign, remember the spot's owenr
					S8 = true; oFlag = true; return; //set taken flag on and return when done
				} else if(sp4==1){	//1=make move to prevent player frm winning
					st8 = dString; sp8 = 2;  
					S8 = true; dFlag = true; return;
				}
			} else if(sp4==sp8 && sp4!=0 && sp0==0){
				if(sp4==2){
					st0 = dString; sp0 = 2;  
					S0 = true; oFlag = true; return;
				} else if(sp4==1){
					st0 = dString; sp0 = 2;	  
					S0 = true; dFlag = true; return;
				}
			} else if(sp0==sp8 && sp0!=0 && sp4==0){
				if(sp0==2){
					st4 = dString; sp4 = 2;  
					S4 = true; oFlag = true; return;
				} else if(sp0==1){
					st4 = dString; sp4 = 2;  
					S4 = true; dFlag = true; return;
				}
			}
		}
		if(sp4==sp1 || sp4==sp7 || sp1==sp7){
			if(sp4==sp1 && sp4!=0 && sp7==0){
				if(sp4==2){
					st7 = dString; sp7 = 2; 
					S7 = true; oFlag = true; return;
				} else if(sp4==1){
					st7 = dString; sp7 = 2; 
					S7 = true; dFlag = true; return;
				}
			} else if(sp4==sp7 && sp4!=0 && sp1==0){
				if(sp4==2){
					st1 = dString; sp1 = 2; 
					S1 = true; oFlag = true; return;
				} else if(sp4==1){
					st1 = dString; sp1 = 2; 
					S1 = true; dFlag = true; return;
				}
			} else if(sp1==sp7 && sp1!=0 && sp4==0){
				if(sp1==2){
					st4 = dString; sp4 = 2; 
					S4 = true; oFlag = true; return;
				} else if(sp1==1){
					st4 = dString; sp4 = 2; 
					S4 = true; dFlag = true; return;
				}
			}
		}
		if(sp4==sp2 || sp4==sp6 || sp2==sp6){
			if(sp4==sp2  && sp4!=0 && sp6==0){
				if(sp4==2){
					st6 = dString; sp6 = 2; 
					S6 = true; oFlag = true; return;
				} else if(sp4==1){
					st6 = dString; sp6 = 2; 
					S6 = true; dFlag = true; return;
				}
			} else if(sp4==sp6 && sp4!=0 && sp2==0){
				if(sp4==2){
					st2 = dString; sp2 = 2; 
					S2 = true; oFlag = true; return;
				} else if(sp4==1){
					st2 = dString; sp2 = 2; 
					S2 = true; dFlag = true; return;
				}
			} else if(sp2==sp6 && sp2!=0 && sp4==0){
				if(sp2==2){
					st4 = dString; sp4 = 2; 
					S4 = true; oFlag = true; return;
				} else if(sp2==1){
					st4 = dString; sp4 = 2; 
					S4 = true; dFlag = true; return;
				}
			}
		}
		if(sp4==sp3 || sp4==sp5 || sp3==sp5){
			if(sp4==sp3 && sp4!=0 && sp5==0){
				if(sp4==2){
					st5 = dString; sp5 = 2;
					S5 = true; oFlag = true; return;
				} else if(sp4==1){
					st5 = dString; sp5 = 2;
					S5 = true; dFlag = true; return;
				}
			} else if(sp4==sp5 && sp4!=0 && sp3==0){
				if(sp4==2){
					st3 = dString; sp3 = 2;
					S3 = true; oFlag = true; return;
				} else if(sp4==1){
					st3 = dString; sp3 = 2;
					S3 = true; dFlag = true; return;
				}
			} else if(sp3==sp5 && sp3!=0 && sp4==0){
				if(sp3==2){
					st4 = dString; sp4 = 2;
					S4 = true; oFlag = true; return;
				} else if(sp3==1){
					st4 = dString; sp4 = 2;
					S4 = true; dFlag = true; return;
				}
			}
		}
		if(sp0==sp1 || sp0==sp2 || sp1==sp2){
			if(sp0==sp1 && sp0!=0 && sp2==0){
				if(sp0==2){
					st2 = dString; sp2 = 2;
					S2 = true; oFlag = true; return;
				} else if(sp0==1){
					st2 = dString; sp2 = 2;
					S2 = true; dFlag = true; return;
				}
				
			} else if(sp0==sp2 && sp0!=0 && sp1==0){
				if(sp0==2){
					st1 = dString; sp1 = 2;
					S1 = true; oFlag = true; return;
				} else if(sp0==1){
					st1 = dString; sp1 = 2;
					S1 = true; dFlag = true; return;
				} 
			} else if(sp1==sp2 && sp1!=0 && sp0==0){
				if(sp1==2){
					st0 = dString; sp0 = 2; 
					S0 = true; oFlag = true; return;
				} else if(sp1==1){
					st0 = dString; sp0 = 2;
					S0 = true; dFlag = true; return;
				}
			}
		}
		if(sp0==sp3 || sp0==sp6 || sp3==sp6){
			if(sp0==sp3 && sp0!=0 && sp6==0){
				if(sp0==2){
					st6 = dString; sp6 = 2;
					S6 = true; oFlag = true; return;
				} else if(sp0==1){
					st6 = dString; sp6 = 2;
					S6 = true; dFlag = true; return;
				}
			} else if(sp0==sp6 && sp0!=0 && sp3==0){
				if(sp0==2){
					st3 = dString; sp3 = 2;
					S3 = true; oFlag = true; return;
				} else if(sp0==1){
					st3 = dString; sp3 = 2;
					S3 = true; dFlag = true; return;
				}
				
			} else if(sp3==sp6 && sp3!=0 && sp0==0){
				if(sp3==2){
					st0 = dString; sp0 = 2; 
					S0 = true; oFlag = true; return;
				} else if(sp3==1){
					st0 = dString; sp0 = 2; 
					S0 = true; dFlag = true; return;
				}
			}
		}
		if(sp2==sp5 || sp2==sp8 || sp5==sp8){
			if(sp2==sp5 && sp2!=0 && sp8==0){
				if(sp2==2){
					st8 = dString; sp8 = 2;
					S8 = true; oFlag = true; return;
				} else if(sp2==1){
					st8 = dString; sp8 = 2;
					S8 = true; dFlag = true; return;
				}
			} else if(sp2==sp8 && sp2!=0 && sp5==0){
				if(sp2==2){
					st5 = dString; sp5 = 2;
					S5 = true; oFlag = true; return;
				} else if(sp2==1){
					st5 = dString; sp5 = 2;
					S5 = true; dFlag = true; return;
				}
			} else if(sp5==sp8 && sp5!=0 && sp2==0){
				if(sp5==2){
					st2 = dString; sp2 = 2;
					S2 = true; oFlag = true; return;
				} else if(sp5==1){
					st2 = dString; sp2 = 2;
					S2 = true; dFlag = true; return;
				}
			}
		}
		if(sp6==sp7 || sp6==sp8 || sp7==sp8){
			if(sp6==sp7 && sp6!=0 && sp8==0){
				if(sp6==2){
					st8 = dString; sp8 = 2; 
					S8 = true; oFlag = true; return;
				} else if(sp6==1){
					st8 = dString; sp8 = 2;
					S8 = true; dFlag = true; return;
				}
			} else if(sp6==sp8 && sp6!=0 && sp7==0){
				if(sp6==2){
					st7 = dString; sp7 = 2;
					S7 = true; oFlag = true; return;
				} else if(sp6==1){
					st7 = dString; sp7 = 2;
					S7 = true; dFlag = true; return;
				}
			} else if(sp7==sp8 && sp7!=0 && sp6==0){
				if(sp7==2){
					st6 = dString; sp6 = 2;
					S6 = true; oFlag = true; return;
				} else if(sp7==1){
					st6 = dString; sp6 = 2;
					S6 = true; dFlag = true; return;
				}
			}
		}

		//this is called Offend block of expert computer move.
		//it decides where to move to win the game
		if(dFlag==false && oFlag==false){
			if(sp0==1 || sp2==1 || sp6==1 || sp8==1){
				if(sp3==0){
					st3 = dString; sp3 = 2; S3 = true;
				} else if(sp1==0){
					st1 = dString; sp1 = 2; S1 = true;
				} else if(sp5==0){
					st5 = dString; sp5 = 2; S5 = true;
				} else if(sp7==0){
					st7 = dString; sp7 = 2; S7 = true;
				} else {	//if it get here, it means the game will end up tie. 
					cmptrMove();	//cmptrMove() is called to fill the blank spot
				}
			} else if(sp4==0){
				st4 = dString; sp4 = 2; S4 = true;
			} else {
				if(sp0==0){
					st0 = dString; sp0 = 2; S0 = true;
				} else if(sp2==0){
					st2 = dString; sp2 = 2; S2 = true;
				} else if(sp6==0){
					st6 = dString; sp6 = 2; S6 = true;
				} else if(sp8==0){
					st8 = dString; sp8 = 2; S8 = true;
				} else {	//if it get here, it means the game will end up tie. 
					cmptrMove();	//cmptrMove() is called to fill the blank spot
				}
			}
		}
	} //end of cmptrExpert() method
	
	/*
	 * this method is about checking if player clicks on the taken spot
	 */
	public boolean takenSpot(){
		if(x<82 && y<82 && S0==false){	//player clicked on sp0
			click = 0; return(false);
		} else if(x<82 && (82<y&&y<167) && S3==false){	//player clicked on sp3
			click = 3; return(false);
		} else if(x<82 && y>167 && S6==false){	//player clicked on sp6
			click = 6; return(false);
		} else if((82<x&&x<175) && y<82 && S1==false){	//player clicked on sp1
			click = 1; return(false);
		} else if((82<x&&x<175) && (82<y&&y<167) && S4==false){	//player clicked on sp4
			click = 4; return(false);
		}  else if((82<x&&x<175) && y>167 && S7==false){	//player clicked on sp7
			click = 7; return(false);
		} else if(x>175 && y<82 && S2==false){	//player clicked on sp2
			click = 2; return(false);
		} else if(x>175 && (82<y&&y<167) && S5==false){	//player clicked on sp5
			click = 5; return(false);
		} else if(x>175 && y>167 && S8==false){	//player clicked on sp8
			click = 8; return(false);
		}
		return(true); //user clicked on taken spot
	} //end of takenSpot() method
}