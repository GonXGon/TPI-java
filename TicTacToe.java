package cs210;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static ArrayList<Integer> player1pos = new ArrayList<Integer>();
	public static ArrayList<Integer> player2pos = new ArrayList<Integer>();
	public static ArrayList<Integer> cpupos = new ArrayList<Integer>();
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Type 'Help' to find out how to play the game.");
		System.out.println("Type 'Play' to play the game.");
		System.out.println("Type 'Win' if you want to know the winning conditions of the game.");
		System.out.println("Type 'Stop' if you want to Stop playing the game :( ");
		while(true) {
			String usertype = scan.nextLine();
			if(usertype.equalsIgnoreCase("Help")) {
				gameRule();
			}
			if(usertype.equalsIgnoreCase("Win")) {
				winningcond();
			}
			if(usertype.equalsIgnoreCase("Play")) {
				Game();
			}
			if(usertype.equalsIgnoreCase("Stop")) {
				break;
			}
		}
	}
	public static void gameRule(){
		System.out.println("To play the game simply use the 1 - 9 Digits to Control"
						+ " where your choosen Symbol will go.");
		System.out.println("{'1','|','2','|','3'}");
		System.out.println("{'-','+','-','+','-'}");
		System.out.println("{'4','|','5','|','6'}");
		System.out.println("{'-','+','-','+','-'}");
		System.out.println("{'7','|','8','|','9'}");
		System.out.println("This table shows where each typed digits will place your symbol.");
	}
	public static void winningcond() {
		System.out.println("To win the Game A player must have following conditions");
		System.out.println("1,2,3");
		System.out.println("4,5,6");
		System.out.println("7,8,9");
		System.out.println("1,4,7");
		System.out.println("2,5,8");
		System.out.println("3,6,9");
		System.out.println("1,5,9");
		System.out.println("3,5,7");
		System.out.print("If the player have symbols in these positions the player WINS.");
	}
	public static void Game() {
		char [][] board = {
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}
				};
		System.out.println("Press 1 if you want to play against a player "
				+ "Press 2 if you want to play against a CPU");
		int whichplayer = scan.nextInt();
		System.out.println("Choose the symbol Player 1 want to play with.");
		char symbolplayer1 = scan.next().charAt(0);
		char symbolplayer2 = ' ';
		if(whichplayer == 1) {
			System.out.println("Choose the symbol Player 2 want to play with.");
			symbolplayer2 = scan.next().charAt(0);
		}
		while(true) {
			int user1 = 0;
			int user2 = 0;
			if(whichplayer == 1) {
				System.out.println("Enter the number where you want to "+symbolplayer1+",between 1-9");
				user1 = scan.nextInt();
				while(player1pos.contains(user1) || player2pos.contains(user1)) {
					System.out.println("Place already taken,choose another position for "+ symbolplayer1);
					user1 = scan.nextInt();
				}
				userinput(board,user1,"player1",symbolplayer1);
				printBoard(board);
				System.out.println("Enter the number where you want to "+symbolplayer2+",between 1-9");
				user2 = scan.nextInt();
				while(player2pos.contains(user2) || player1pos.contains(user2)) {
					System.out.println("Place already taken,choose another position for "+ symbolplayer1);
					user2 = scan.nextInt();
				}
				userinput(board,user2,"player2",symbolplayer2);
				printBoard(board);
				System.out.println(WinningConditions());
				if(WinningConditions() == "Player 1 Won") {
					break;
				}
				if(WinningConditions() == "Player 2 Won") {
					break;
				}
				if(WinningConditions() == "It is a Draw") {
					break;
				}
			}
			if(whichplayer == 2) {
				System.out.println("Enter the number where you want to "+symbolplayer1+",between 1-9");
				user1 = scan.nextInt();
				while(player1pos.contains(user1) || cpupos.contains(user1)) {
					System.out.println("Place already taken,choose another position for "+ symbolplayer1);
					user1 = scan.nextInt();
				}
				userinput(board,user1,"player1",symbolplayer1);
				Random r = new Random();
				int cpuposition = r.nextInt(9)+1;
				while(player1pos.contains(cpuposition) || cpupos.contains(cpuposition)) {
					cpuposition = r.nextInt(9)+1;
				}
				userinput(board,cpuposition,"CPU",symbolplayer1);
				printBoard(board);
				System.out.println(WinningConditions());
				if(WinningConditions() == "Player 1 Won") {
					break;
				}
				if(WinningConditions() == "CPU Won") {
					break;
				}
				if(WinningConditions() == "It is a Draw") {
					break;
				}
			}
		}		
	}
	public static void printBoard(char [][] board) {
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board.length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	public static void userinput(char [][] board,int pos,String player,char symbolplayer1) {
		char character = ' ';
		if(player.equals("player1")) {
			character = symbolplayer1;
			player1pos.add(pos);
		}
		else if(player.equals("player2")) {
			character = symbolplayer1;
			player2pos.add(pos);
		}
		else if(player.equals("CPU")) {
			character = 'O';
			cpupos.add(pos);
		}
		if(pos == 1){
			board[0][0] = character;
		}
		else if(pos == 2){
			board[0][2] = character;
		}
		else if(pos == 3){
			board[0][4] = character;
		}
		else if(pos == 4){
			board[2][0] = character;
		}
		else if(pos == 5){
			board[2][2] = character;
		}
		else if(pos == 6){
			board[2][4] = character;
		}
		else if(pos == 7){
			board[4][0] = character;
		}
		else if(pos == 8){
			board[4][2] = character;
		}
		else if(pos == 9){
			board[4][4] = character;
		}
	}
	public static String WinningConditions() {
		List tRow = (List) Arrays.asList(1,2,3);
		List mRow = (List) Arrays.asList(4,5,6);
		List bRow = (List) Arrays.asList(7,8,9);
		List lCol = (List) Arrays.asList(1,4,7);
		List mCol = (List) Arrays.asList(2,5,8);
		List rCol = (List) Arrays.asList(3,6,9);
		List crossR = (List) Arrays.asList(1,5,9);
		List crossL = (List) Arrays.asList(7,5,3);
		
		List<List> conditions = new ArrayList<List>();
		conditions.add(tRow);
		conditions.add(mRow);
		conditions.add(bRow);
		conditions.add(lCol);
		conditions.add(mCol);
		conditions.add(rCol);
		conditions.add(crossR);
		conditions.add(crossL);
		
		for(List C : conditions) {
			if(player1pos.containsAll(C)) {
				return "Player 1 Won";
			}
			else if(player2pos.containsAll(C)) {
				return "Player 2 Won";
			}
			else if(cpupos.containsAll(C)) {
				return "CPU Won";
			}
			else if(player1pos.size()+player2pos.size() >= 9) {
				return "It is a Draw";
			}
			else if(player1pos.size()+cpupos.size() >= 9) {
				return "It is a Draw";
			}
		}
		return "";
	}
}
