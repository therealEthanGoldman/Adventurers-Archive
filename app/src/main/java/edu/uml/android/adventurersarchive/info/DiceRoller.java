package edu.uml.android.adventurersarchive.info;

import java.util.Random;

/**
 * Created by Darin on 11/10/2016.
 */
public class DiceRoller {
    public static Random rand = new Random();

    public static int roll(int sides) {
        return (rand.nextInt(sides) + 1);
    }

    public static int roll(int sides, int times) {
        int sum = 0;
        for(int i = 0; i < times; i++) {
            sum += DiceRoller.roll(sides);
        }
        return sum;
    }

    public static int [] getRolls(int sides, int times) {
        int [] rolls = new int[times];

        for(int i = 0; i < times; i++) {
            rolls[i] = DiceRoller.roll(sides);
        }

        return rolls;
    }
}
