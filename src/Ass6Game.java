// * Tal Gelerman 322280850

import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

/**
 * ass6 class.
 */
public class Ass6Game {
    /**
     * main.
     *
     * @param args array of strings
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, new Sleeper());
        GameFlow g = new GameFlow(ar, gui.getKeyboardSensor(), gui);
        List<LevelInformation> levelInformationList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if ((args[i].equals("1")) || (args[i].equals("2")) || (args[i].equals("3")) || (args[i].equals("4"))) {
                if (args[i].equals("1")) {
                    levelInformationList.add(new Level1());
                } else if (args[i].equals("2")) {
                    levelInformationList.add(new Level2());
                } else if (args[i].equals("3")) {
                    levelInformationList.add(new Level3());
                } else if (args[i].equals("4")) {
                    levelInformationList.add(new Level4());
                }
            } else {
                continue;
            }
        }
        g.runLevels(levelInformationList);
    }
}
