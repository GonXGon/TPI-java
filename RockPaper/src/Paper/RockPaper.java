package Paper;
import java.util.Scanner;
import java.util.Random;
public class RockPaper {
	static int playerscore = 0;
	static int compscore = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stu
		texts();
		Scanner scan = new Scanner(System.in);
		String user = scan.nextLine();
		String letter = user.toUpperCase();
		vaild(letter);
		game(letter);
	}
	
	public static void game(String letter) {
		Scanner scan = new Scanner(System.in);
		boolean b = true;
		while(b) {
			String completter = complayer();
			System.out.println("COMPUTER CHOOSE LETTER: "+completter);
			System.out.println(" ");
			rules(letter,completter);
			System.out.println(" ");
			letter = scan.nextLine();
			letter = letter.toUpperCase();
			vaild(letter);
			if(letter.equals("Q")) {
				System.out.println("Quitting!!!!");
				b = false;
			}
		}
	}

	public static void texts() {
		System.out.println("Enter a letter for Rock,Paper,Scissor");
		System.out.println("R: Rock");
		System.out.println("P: Paper");
		System.out.println("S: Scissor");
		System.out.println("Q: QUIT");
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
		
		System.out.println("PLAYER SCORE: "+playerscore);
		System.out.println("COMPUTER SCORE: "+compscore);
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
	
	public static void vaild(String letter) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			if(letter.equals("R") || letter.equals("P") ||letter.equals("S") ||letter.equals("Q")) {
				System.out.println("YOU CHOOSE LETTER: "+letter); 
				break;
			}
			else {
				texts();
				letter = scan.nextLine();
				letter = letter.toUpperCase();
			}
		}
	}
	

}
