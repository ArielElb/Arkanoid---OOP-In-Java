//213073497 Ariel Elbaz.
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner - running the animation of the game.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     * @param framesPerSecond .
     * @param s - sleeper timer.
     */
    public AnimationRunner(int framesPerSecond, Sleeper s) {
        this.sleeper = s;
        this.gui =  new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * getter of the GUI.
     * @return GUI.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * running a given animation.
     * @param animation - given animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }
}