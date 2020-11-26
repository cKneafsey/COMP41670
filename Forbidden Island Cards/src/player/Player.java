package player;

import java.util.List;
import java.util.Scanner;

import gameplay.Choices;
import pawns.Pawn;
import pawns.*;

import cards.Hand;
import cards.HelicopterLift;
import cards.SandbagsCard;
import cards.TreasureCard;
import cards.TreasureDeckCard;
import enums.TreasureCardEnums;


public class Player {
	// ===========================================================
	// Variable Setup
	// ===========================================================
	private String playerName;
	private Hand playerHand;
	private Pawn playerPawn;
	private int playerNum;

	public Player(int playerNum, String playerName, int adventurerNum) {
		this.playerName = playerName;
		this.playerHand = new Hand();
		this.playerNum = playerNum;
		switch (adventurerNum) {
			case 0:
				playerPawn = new Diver();
				break;
			case 1:
				playerPawn = new Engineer();
				break;
			case 2:
				playerPawn = new Explorer();
				break;
			case 3:
				playerPawn = new Navigator();
				break;
			case 4:
				playerPawn = new Pilot();
				break;
			case 5:
				playerPawn = new Messenger();
				break;
			default:
				System.out.println("I shouldn't be here!");
		}
	}
	
	public String getName() {
		return playerName;
	}
	public int getNum() {
		return playerNum;
	}

	public List<TreasureDeckCard> showHand() {
		return playerHand.getCards();
	}
	
	public Hand getHand() {
		return playerHand;
	}
	
	public int handSize() {
		return playerHand.getCards().size();
	}

	public void drawTreasureCard(TreasureDeckCard card1) {
		playerHand.addCard(card1);
	}
	
	public Pawn getPawn(){
		return playerPawn;
	}

	public String getPlayerType(){
		return playerPawn.getPlayerType();
	}

	public boolean giveTreasureCard(Player plnum, int canum, Scanner inputScanner){
		TreasureDeckCard c1 = playerHand.getCards().get(canum);
		if(!(c1 instanceof TreasureCard)){
			return false;
		}
		plnum.getHand().addCard(c1);
		if(plnum.handSize()>5){
			plnum.discardTreasureCard(inputScanner);
		}
		getHand().getCards().remove(canum);
		return true;
	}

	public void discardTreasureCard(Scanner inputScanner){
		int userIn = 0;
		boolean validIn = false;
		System.out.println("Hey, "+playerName+". Your hand is too big...\n");
		userIn = chooseFromHand(inputScanner, "discard or use?",false);
		if(!(showHand().get(userIn) instanceof TreasureCard)){
			if(Choices.getYesOrNo(inputScanner,"It's use it or lose it! Do you want to use this card now?", "No", "Yes")){
				if((showHand().get(userIn) instanceof SandbagsCard)){
					System.out.println("Not fully decided where Sandbags will be called yet!");
				}
				if((showHand().get(userIn) instanceof HelicopterLift)){
					System.out.println("Not fully decided where Helicopter Lift will be called yet!");
				}
			}
		}
		getHand().removeCard(userIn);
	}

	public int chooseFromHand(Scanner inputScanner, String action, boolean ineligible){
		int userIn = 0;
		for (int i = 0; i < showHand().size(); i++) {
			if (!(ineligible && !(showHand().get(i) instanceof TreasureCard))){
				System.out.println("[" + i + "]: " + showHand().get(i).getName());
			}
		}
		boolean validIn = false;
		while (!validIn) {
			System.out.println("Which of the cards would you like to " + action);
			String userString = inputScanner.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
				continue;
			}
			if ((userIn >= 0) && (userIn <= showHand().size()-1)) {
				validIn = true;
			}
		}
		return userIn;
	}

	public boolean checkHasCard(TreasureCardEnums name){
		if(!getHand().checkContains(name)){
			return false;
		}
		return true;		
	}

	public void helicopterMove(){
		playerPawn.helicopterMove();
	}
}
