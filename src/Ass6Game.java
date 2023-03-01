//213073497 Ariel Elbaz.

import java.util.ArrayList;
import java.util.List;

/**
 * class to initialize and run the game.
 */
public class Ass6Game {
    /**
     * main.
     *
     * @param args - the main function
     */
    public static void main(String[] args) {
        LevelInformation lvl1 = new LevelOne();
        LevelInformation lvl2 = new LevelTwo();
        LevelInformation lvl3 = new LevelThree();
        LevelInformation lvl4 = new LevelFour();
        List<LevelInformation> l = new ArrayList<LevelInformation>();
        for (String str : args) {
            if (str.equals("1")) {
                l.add(lvl1);
            }
            if (str.equals("2")) {
                l.add(lvl2);
            }
            if (str.equals("3")) {
                l.add(lvl3);
            }
            if (str.equals("4")) {
                l.add(lvl4);
            }
        }
        if (l.size() == 0) {
            l.add(lvl1);
            l.add(lvl2);
            l.add(lvl3);
            l.add(lvl4);
        }
        GameFlow game = new GameFlow();
        game.runLevels(l);
    }
}
