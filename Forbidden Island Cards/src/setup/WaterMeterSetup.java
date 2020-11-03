package setup;

import java.util.Scanner;

import gameplay.WaterMeter;

public class WaterMeterSetup {

    private WaterMeter setupWaterMeter;
    boolean validWaterLevel = false;

    public WaterMeterSetup() {
		this.setupWaterMeter = WaterMeter.getInstance();
	}

	protected void createWaterLevel(Scanner user) {
		int level=0;
		boolean waterlevelselected = false;
		while (!waterlevelselected) {
			while (!validWaterLevel) {
				level = getDifficulty(user);
			}
            setupWaterMeter.setWatermeter(level);
            waterlevelselected = true;
		}
		
	}

	protected int getDifficulty(Scanner user) {
        String userString;
        System.out.println("What difficulty level would you like to play at? The options are: ");
        System.out.println("[0] - Novice, Flood Level set to 2");
        System.out.println("[1] - Normal, Flood Level set to 2");
        System.out.println("[2] - Elite, Flood Level set to 3");
        System.out.println("[3] - Legendary, Flood Level set to 3");
        userString = user.nextLine();
		return setDifficulty(userString);
    }
    
    public int setDifficulty(String userString) {
		int waterlevel =0;
		try {
			waterlevel = Integer.parseInt(userString);
		} catch (NumberFormatException e) {
			return waterlevel;
		}

		if ((waterlevel >= 0) && (waterlevel <= 3)) {
			validWaterLevel = true;
		}
		else{
			System.out.println("Incorrect input\n");
		}
		return waterlevel;
	}


}
