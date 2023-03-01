import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * CountDownAnimation class implements the animation methods.
 */
public class CountdownAnimation implements Animation {
    private Sleeper sleeper;
    private SpriteCollection screen;
    private double numOfSeconds;
    private int countDown;
    private int tempCount;
    private boolean running = true;

    /**
     * Constructor.
     *
     * @param numOfSeconds - num of seconds the countdown will be shown.
     * @param countDown    - the number of second to show.
     * @param screen       - SpriteCollection screen.
     */
    public CountdownAnimation(double numOfSeconds, int countDown, SpriteCollection screen) {
        this.countDown = countDown;
        this.screen = screen;
        this.numOfSeconds = numOfSeconds;
        this.tempCount = this.countDown;
        this.sleeper = new Sleeper();
    }

    /**
     * doOneFrame.
     *
     * @param d - a given surface.
     */
    public void doOneFrame(DrawSurface d) {
        if (this.tempCount == 1) {
            this.running = false;
        }
        this.screen.drawAllOn(d);
        d.setColor(Color.magenta);
        d.drawText(385, 385, this.tempCount + "...", 35);
        if (this.tempCount != this.countDown) {
            this.sleeper.sleepFor((int) (this.numOfSeconds * 1000) / this.countDown);
        }
        this.tempCount--;
    }

    /**
     **
     * @return this.running.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
