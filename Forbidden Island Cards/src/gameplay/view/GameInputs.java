package gameplay.view;

import java.awt.Point;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.Position;

import board.Board;

public class GameInputs {

    private static GameOutputs theOutputs;
    private static Scanner input;

    public GameInputs() {
        input = new Scanner(System.in);
        theOutputs = new GameOutputs();
    }

    public char moveDir() {
        char userInput = 0;
        boolean validInput = false;
        while (!validInput) {
            String userString = input.nextLine();
            try {
                userInput = userString.charAt(0);
            } catch (NumberFormatException e) {
                theOutputs.generalError();
                continue;
            }
            if ((userInput == 'w') || (userInput == 'W')) {
                validInput = true;
                userInput = 'w';
            }
            else if ((userInput == 'a') || (userInput == 'A')) {
                validInput = true;
                userInput = 'a';
            }
            else if ((userInput == 's') || (userInput == 'D')) {
                validInput = true;
                userInput = 's';
            }
            else if ((userInput == 'd') || (userInput == 'D')) {
                validInput = true;
                userInput = 'd';
            }
            else{
                theOutputs.generalError();
            }
        }
        return userInput;

    }

    public boolean floodOrTreasure() {
        theOutputs.printFloodorTreasure();
        return boolYN("Flood", "Treasure");
    }

    public static int getYesOrNo(String n, String y, String other) {
        int userIn = 0;
        theOutputs.option(0,n);
        theOutputs.option(1,y);
        theOutputs.option(2,other);
        while (true) {
            String decision = input.nextLine();
            try {
                userIn = Integer.parseInt(decision);
            } catch (NumberFormatException e) {
                continue;
            }
            switch (userIn) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    theOutputs.generalError();
            }
        }
    }

    public static boolean boolYN(String n, String y) {
        int userIn = 0;
        theOutputs.option(0,n);
        theOutputs.option(1,y);
        while (true) {
            String decision = input.nextLine();
            try {
                userIn = Integer.parseInt(decision);
            } catch (NumberFormatException e) {
                continue;
            }
            switch (userIn) {
                case 0:
                    return false;
                case 1:
                    return true;
                default:
                    theOutputs.generalError();
            }
        }
    }

    public int turnChoice(){
        boolean validInput = false;
        int userInput=-1;
        while (!validInput) {
            String userString = input.nextLine();
            try {
                userInput = Integer.parseInt(userString);
            } catch (NumberFormatException e) {
                theOutputs.generalError();
                continue;
            }
            if ((userInput >= 0) && (userInput < 11)) {
                validInput = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userInput;
    }

    public void confirm(){
        @SuppressWarnings("unused")
        String start = input.nextLine();
    }

    public int playerChoice(int size, List eligible){
        int userIn=0;
        boolean validIn = false;
		while (!validIn) {
			String userString = input.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
                theOutputs.generalError();
				continue;
            }
			if ((userIn >= 0) && (userIn < size && ((eligible.contains(userIn))))) {
				validIn = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userIn;
    }

    public int handChoice(int size){
        int userIn=0;
        boolean validIn = false;
		while (!validIn) {
			String userString = input.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
                theOutputs.generalError();
				continue;
            }
			if ((userIn >= 0) && (userIn < size )) {
				validIn = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userIn;
    }

	public Point selectTile() {
        int[] intArray = {0,0};
        boolean valid = false;
        for(int i=0;i<2;i++){
            if(i==0){
                theOutputs.enterCoords('x');
            }
            if(i==1){
                theOutputs.enterCoords('y');
            }
            valid = false;
            while (!valid) {
                String userString = input.nextLine();
                try {
                    intArray[i] = Integer.parseInt(userString);
                } catch (NumberFormatException e) {
                    theOutputs.generalError();
                    continue;
                }
                if ((intArray[i] >= 0) && (intArray[i] < 6 )) {
                    valid = true;
                }
                else{
                    theOutputs.generalError();
                }
            }
        }
        return new Point(intArray[0],intArray[1]);
    }
}
