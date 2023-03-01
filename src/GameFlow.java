import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.List;

/**
 * GameFlow - creates the game the animation and running all the levels.
 */
public class GameFlow {
    private KeyboardSensor keyboard;
    private Counter score;
    private final Sleeper sleeper;
    private AnimationRunner animationRunner;

    /**
     * Constructor.
     */
    public GameFlow() {
        this.score = new Counter();
        this.sleeper = new Sleeper();
        this.animationRunner = new AnimationRunner(60, this.sleeper);
        this.keyboard = animationRunner.getGui().getKeyboardSensor();

    }

    /**
     * runLevels - running the levels of the game.
     * @param levels - list of level.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (int i = 0; i < levels.size(); i++) {
            LevelInformation levelInformation = levels.get(i);
            GameLevel level = new GameLevel(levelInformation, this.animationRunner, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.getBallsLeft().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.score, !(level.getBallsLeft().getValue() == 0))));
                level.getGameGUI().close();
                /*`
                 * if it's the last level, and you won the game.
                 */
            } else if (i == levels.size() - 1) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.score, true)));
                level.getGameGUI().close();

            }
        }
    }
}
