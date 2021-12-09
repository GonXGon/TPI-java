package Paper;
import java.awt.Button;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Graphics {
	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	static int WIN_WIDTH = gd.getDisplayMode().getWidth();
	static int WIN_HEIGHT = gd.getDisplayMode().getHeight();
    private static Paper paper;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		paper = new Paper(WIN_WIDTH,WIN_HEIGHT,"Rock,Paper,Scissor");
	}

}
class Paper{
	private static ArrayList<String> words = new ArrayList<String>();
	private static boolean rocksenssor = false;
	private static boolean papersenssor = false;
	private static boolean scissorsenssor = false;
	static int playerscore = 0;
	static int compscore = 0;
	JFrame j;
	JPanel panel;
	JLabel label;
	JTextArea tfTarget;
	JTextArea scoreboard;
	
	Panel rock;
	Panel paper;
	Panel scissor;
	
	Panel submit;
	Panel RockPanel;
	Panel PaperPanel;
	Panel ScissorPanel;
	Panel console;
	Panel Score;
	
	Panel extra;
	Button rockbutton,paperbutton,scissorbutton,submitbutton,resetbutton;
	
    String rockimage = "C:\\Users\\shubh\\Desktop\\Eclipse\\RockPaper\\src\\Paper\\rock.jpg";
    String paperimage = "C:\\Users\\shubh\\Desktop\\Eclipse\\RockPaper\\src\\Paper\\paper.png";
    String scissorimage = "C:\\Users\\shubh\\Desktop\\Eclipse\\RockPaper\\src\\Paper\\scissor.jpg";
    
    BufferedImage r = ImageIO.read(new File(rockimage));
    BufferedImage p = ImageIO.read(new File(paperimage));
    BufferedImage s = ImageIO.read(new File(scissorimage));
	
	Paper(int width,int height,String title) throws IOException{
		j = new JFrame();
		j.setBounds(0, 0, width, height);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle(title);
        
        Rock(width,height);
        Rockbutton(width,height);
        
        Papers(width,height);
        Paperbutton(width,height);
        
        Scissor(width,height);
        Scissorbutton(width,height);
        
        Submit(width,height);
        Console(width,height);
        score(width,height);
        
        extra();
	    
        j.add(rock);
        j.add(RockPanel);
        
        j.add(paper);
        j.add(PaperPanel);
        
        j.add(scissor);
        j.add(ScissorPanel);
        
        j.add(submit);
        j.add(console);
        j.add(Score);
        
        j.add(extra);
        j.setVisible(true);
	}
	
	private void Rock(int width,int height) {
		rock = new Panel();
		rock.setBackground(Color.BLACK);
		rock.setBounds(width/20, height/4, 250, 250);
    	label = new JLabel(new ImageIcon(r));
    	rock.add(label);
    	rock.setVisible(true);
	}
	
	private void Rockbutton(int width,int height) {
		RockPanel = new Panel();
		RockPanel.setBackground(Color.black);
		RockPanel.setBounds(width/20, height/2, 250, 50);
		rockbutton = new Button("Rock");
		rockbutton.setBackground(Color.yellow);
		
		RockPanel.add(rockbutton);
		RockPanel.setVisible(true);
		rockbutton.setVisible(true);
		
		rockbutton.addActionListener(new ActionListener() {
            // when the button is clicked, this method runs
            @Override
            public void actionPerformed(ActionEvent e) {
//            	tfTarget.setText("HELLO");
        		tfTarget.setText("You Choose ROCK");
            	words.add("R");
            	rocksenssor = true;
            }
    	});
	}
	
	private void Papers(int width,int height) {
		paper = new Panel();
		paper.setBackground(Color.BLACK);
		paper.setBounds((width/10)*4, height/4, 250, 250);
    	label = new JLabel(new ImageIcon(p));
    	paper.add(label);
		paper.setVisible(true);
	}
	
	private void Paperbutton(int width,int height) {
		PaperPanel = new Panel();
		PaperPanel.setBackground(Color.black);
		PaperPanel.setBounds((width/10)*4, height/2, 250, 50);
		
		paperbutton = new Button("Paper");
		paperbutton.setBackground(Color.yellow);
		
		PaperPanel.add(paperbutton);
		PaperPanel.setVisible(true);
		paperbutton.setVisible(true);
		
		paperbutton.addActionListener(new ActionListener() {
            // when the button is clicked, this method runs
            @Override
            public void actionPerformed(ActionEvent e) {
//            	tfTarget.setText("HELLO");
        		tfTarget.setText("You Choose PAPER");
            	words.add("P");
            	papersenssor = true;
            }
    	});
	}
	
	private void Scissor(int width,int height) {
		scissor = new Panel();
		scissor.setBackground(Color.BLACK);
		scissor.setBounds((width/10)*8, height/4, 250, 250);
    	label = new JLabel(new ImageIcon(s));
    	scissor.add(label);
		scissor.setVisible(true);
	}
	
	private void Scissorbutton(int width,int height) {
		ScissorPanel = new Panel();
		ScissorPanel.setBackground(Color.black);
		ScissorPanel.setBounds((width/10)*8, height/2, 250, 50);
		
		scissorbutton = new Button("Scissor");
		scissorbutton.setBackground(Color.yellow);
		
		ScissorPanel.add(scissorbutton);
		ScissorPanel.setVisible(true);
		scissorbutton.setVisible(true);
		
		scissorbutton.addActionListener(new ActionListener() {
            // when the button is clicked, this method runs
            @Override
            public void actionPerformed(ActionEvent e) {
//            	tfTarget.setText("HELLO");
        		tfTarget.setText("You Choose SCISSOR");
            	words.add("S");
            	scissorsenssor = true;
            }
    	});
	}
	
	private void Console(int width,int height) {
		console = new Panel();
		console.setBackground(Color.WHITE);
		console.setBounds(width/20, (height/18)*10, 250, 250);
		
		tfTarget = new JTextArea();
		tfTarget.setBackground(Color.WHITE);
    	tfTarget.setBounds(width/20, (height/18)*10, 250, 250);
    	tfTarget.setFont(new Font("SansSerif", Font.BOLD, 14));
    	
//    	tfTarget.setFocusable(true);
//    	tfTarget.setLineWrap(true);
    	
    	tfTarget.setText("Welcome to ROCK, PAPER, SCISSORS !!!!");
    	
    	console.add(tfTarget);
    	tfTarget.setVisible(true);
    	console.setVisible(true);
	}
	
	private void score(int width,int height) {
		Score = new Panel();
		Score.setBackground(Color.WHITE);
		Score.setBounds((width/10)*8, (height/18)*10, 250, 250);
		
		scoreboard = new JTextArea();
		scoreboard.setBackground(Color.WHITE);
		scoreboard.setBounds(width/20, (height/18)*10, 250, 250);
		scoreboard.setFont(new Font("SansSerif", Font.BOLD, 14));
    	
		scoreboard.setFocusable(true);
		scoreboard.setLineWrap(true);
  
    	
    	Score.add(scoreboard);
    	scoreboard.setVisible(true);
    	Score.setVisible(true);
	}
	
	private void Submit(int width,int height) {
		submit = new Panel();
		submit.setBackground(Color.black);
		submit.setBounds((width/10)*4, (height/18)*10, 250, 50);
		submit.setVisible(true);
		
		submitbutton = new Button("Submit");
		submitbutton.setBackground(Color.yellow);
		submitbutton.addActionListener(new ActionListener() {
            // when the button is clicked, this method runs
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(rocksenssor == true) {
            		String r = complayer();
            		if(r == "R") {
            			tfTarget.setText("Computer Choose ROCK ");
            		}
            		else if(r == "P") {
            			tfTarget.setText("Computer Choose PAPER ");
            		}
            		else if(r == "S") {
            			tfTarget.setText("Computer Choose SCISSOR ");
            		}
            	}
            	else {
            		tfTarget.setText("You Choose ");
            	}
       			rules(s(),complayer());
       			scoreboard.setText("PLAYER SCORE: "+ playerscore +" "+
       								"COMPUTER SCORE: "+ compscore );
            	
            }
    	});
		
		resetbutton = new Button("Reset");
		resetbutton.setBackground(Color.yellow);
		
		submit.add(submitbutton);
		submit.add(resetbutton);
		submitbutton.setVisible(true);
		resetbutton.setVisible(true);
	}
	
	private void extra() {
		extra = new Panel();
		extra.setBackground(Color.black);
		extra.setBounds(0, 0, 250, 250);
		extra.setVisible(true);
	}
	
	public static String s() {
		String letter = words.get(0);
		words.remove(0);
		System.out.println(words);
		return letter;
	}
	
	public static String complayer() {
		String l = " ";
		Random rand = new Random();
		int upperbound = 3;
		int random = rand.nextInt(upperbound);
		switch(random) {
		case 0: 
			l = "R";
			break;
		case 1: 
			l = "P";
			break;
		case 2: 
			l = "S";
			break;
		}
		return l;
	}
	
	public static void rules(String letter,String completter) {

		if(letter.equals(completter)) {
			System.out.println("It is A DRAW");
		}
		else if(letter.equals("R")){
			if(completter.equals("S")) {
				playerscore++;
			}
			else if(completter.equals("P")) {
				compscore++;
			}
		}
		else if(letter.equals("P")){
			if(completter.equals("R")) {
				playerscore++;
			}
			else if(completter.equals("S")) {
				compscore++;
			}
		}
		else if(letter.equals("S")){
			if(completter.equals("P")) {
				playerscore++;
			}
			else if(completter.equals("R")) {
				compscore++;
			}
		}
		
	}
	
	
}
