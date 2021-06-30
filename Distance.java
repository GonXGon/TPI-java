package project;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Distance {
    private static final int WIN_WIDTH = 1800;
    private static final int WIN_HEIGHT = 990;
    private static Window window;   

    
	public static void main(String[] args) throws IOException {
		window = new Window(WIN_WIDTH, WIN_HEIGHT, "Delivery Service");
		// TODO Auto-generated method stub
	}
}

class Window{
	private static ArrayList<String> data = new ArrayList<String>();
	private static Scanner scan = new Scanner(System.in);
	private static List<Double> coords1 = new ArrayList<Double>();
	private static List<Double> coords2 = new ArrayList<Double>();
	private static List<Integer> time = new ArrayList<Integer>();
	private static List<Integer> time2 = new ArrayList<Integer>();
	private static List<Integer> ordernumber = new ArrayList<Integer>();
	private static List<Double> distance = new ArrayList<Double>();
	private static List<Integer> answer = new ArrayList<Integer>();
	private static double pizzaplacelat = 53.381610;
	private static double pizzaplacelong = -6.593050;
	
	JFrame windowFrame;
	Panel rightPanel;
	Panel bottomPanel;
    JLabel info;
    JTextArea tfTarget; 
    TextArea tfResult;
    Button btnShoot, btnPlayAgain;
    Font font;
    
    String imagepath = "C:\\Users\\shubh\\Desktop\\Second Year\\CS211\\CS211_LAB\\Project\\src\\project\\map.png";
    //if the image does not work have to change the path for the image.
    BufferedImage my = ImageIO.read(new File(imagepath));;
    JLabel picLabel;
    JPanel jPanel;


    
    Window(int width, int height,String title) throws IOException{
    	windowFrame = new JFrame();
        windowFrame.setBounds(0,0, width, height);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        windowFrame.setTitle(title);
        
        createRightPanel();
        createBottomPanel();
        createPanel();
     
        windowFrame.add(rightPanel);
        windowFrame.add(bottomPanel);
        windowFrame.add(jPanel);
        windowFrame.setVisible(true);
        

    }
    
    
    private void createRightPanel() {   	 
    	rightPanel = new Panel();
    	rightPanel.setBackground(Color.RED);
    	rightPanel.setBounds(1475, 0, 310, 900);
    	rightPanel.setLayout(null);
   	 
    	tfTarget = new JTextArea();
    	tfTarget.setBounds(10, 10, 290, 800);
    	tfTarget.setFont(new Font("SansSerif", Font.BOLD, 14));
    	tfTarget.setFocusable(true);
    	tfTarget.setLineWrap(true);
   	 
    	btnShoot = new Button("Show Address");
    	btnShoot.setBounds(10, 815, 290, 80);
    	btnShoot.setBackground(Color.YELLOW);
    	btnShoot.setFocusable(true);
    	btnShoot.setFont(new Font("SansSerif", Font.BOLD, 20));
    	// creates the panel and adds all the above inner components
    	btnShoot.addActionListener(new ActionListener() {
            // this method gets called when the user clicks this button
            @Override
            public void actionPerformed(ActionEvent e) {
            	String p = longestdistance();
            	tfTarget.setText(p);
            }
    	});
    	rightPanel.add(btnShoot);
    	rightPanel.add(tfTarget);

    	tfTarget.setVisible(true);
    	btnShoot.setVisible(true);   	 
    	rightPanel.setVisible(true);
    }
    
    private void createBottomPanel() {  
    	bottomPanel = new Panel();
    	bottomPanel.setBounds(0, 0, 300, 900);
    	bottomPanel.setBackground(Color.RED);
    	bottomPanel.setLayout(null);
   	 
    	tfResult = new TextArea();
    	tfResult.setBounds(10, 10, 290, 800);
    	tfResult.setFont(new Font("SansSerif", Font.BOLD, 14));

    	btnPlayAgain = new Button("Submit");
    	btnPlayAgain.setBounds(10, 815, 275, 80);
    	btnPlayAgain.setBackground(Color.YELLOW);
    	btnPlayAgain.setFont(new Font("SansSerif", Font.BOLD, 24));
    	
    	btnPlayAgain.addActionListener(new ActionListener() {
            // when the button is clicked, this method runs
            @Override
            public void actionPerformed(ActionEvent e) {
            	String sc = tfResult.getText();
            	Input(sc);
            }
    	});

    	bottomPanel.add(btnPlayAgain);
    	bottomPanel.add(tfResult);
   	 
    	bottomPanel.setVisible(true);
    	tfResult.setVisible(true);
    	btnPlayAgain.setVisible(true);
    }
    private void createPanel() {  
    	jPanel = new JPanel();
    	picLabel = new JLabel(new ImageIcon(my));
    	jPanel.setBackground(Color.ORANGE);
    	jPanel.add(picLabel);
    	windowFrame.add(jPanel);
    	windowFrame.setVisible(true);
    }
	 
	 public static void Input(String sc) {
		 String [] s = sc.split("\n");
		 for(int i = 0;i < 40;i++) {
			 data.add(s[i]);
		 }
		 Coordinates(data);
		 longestdistance();
		 
	 }
	 public static void Coordinates(ArrayList<String> data) {
		 for(int i = 0;i < data.size();i++) {
			 String temp = data.get(i);
			 String temp2 = temp.substring(temp.lastIndexOf(',')+1,temp.length());
			 temp = temp.substring(0,temp.lastIndexOf(','));
			 String temp3 = temp.substring(temp.lastIndexOf(',')+1,temp.length());
			 temp = temp.substring(0,temp.lastIndexOf(','));
			 String temp4 = temp.substring(temp.lastIndexOf(',')+1,temp.length());
			 String temp5 = temp.substring(0,temp.indexOf(','));
			 double coord1 = Double.parseDouble(temp2);
			 double coord2 = Double.parseDouble(temp3);
			 int coord3 = Integer.parseInt(temp4);
			 int coord4 = Integer.parseInt(temp5);
			 coords2.add(coord1);
			 coords1.add(coord2);
			 time.add(coord3);
			 time2.add(coord3);
			 ordernumber.add(coord4);
		 }
	 }
	 public static double haversine(double lat1, double lon1,double lat2, double lon2){
	     // distance between latitudes and longitudes
		// distance between latitudes and longitudes
	        double dLat = Math.toRadians(lat2 - lat1);
	        double dLon = Math.toRadians(lon2 - lon1);
	 
	        // convert to radians
	        lat1 = Math.toRadians(lat1);
	        lat2 = Math.toRadians(lat2);
	 
	        // apply formulae
	        double a = Math.pow(Math.sin(dLat / 2), 2) +
	                   Math.pow(Math.sin(dLon / 2), 2) *
	                   Math.cos(lat1) *
	                   Math.cos(lat2);
	        double rad = 6371;
	        double c = 2 * Math.asin(Math.sqrt(a));
	        return rad * c;
	 }
	 
	 public static double smallest(List<Double> distance) {
		 double min = distance.get(0);
		 for (int i = 1; i < ordernumber.size(); i++) {
			 	if (distance.get(i) < min) {
			 		min = distance.get(i);
	            }
	        }
		 return min;
	 }
	 
	 public static String longestdistance() {
		 int index = 0;
		 for(int i = 0 ;i < time2.size();i++) {	 
			 distance.add(haversine(pizzaplacelat,pizzaplacelong,coords1.get(i),coords2.get(i)));
		 }
		 for(int i = 0 ;i < time2.size();i++) {	 
			 while(distance.get(i) != 10000.0) {
				 double min = smallest(distance);
				 index = distance.indexOf(min);
				 distance.set(index, 10000.0);
				 answer.add(ordernumber.get(index));
			 }
		 }
	      String delim = ",";
	      String res = answer.stream()
	                        .map(Object::toString)
	                        .collect(Collectors.joining(delim));
		 return res;
	 }
    
}


