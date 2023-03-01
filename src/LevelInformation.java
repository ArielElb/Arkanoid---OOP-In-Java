import java.util.List;

/**
 * the information that each level contains.
 */
public interface LevelInformation {
    /**
     * number of balls.
     *
     * @return num of balls.
     */
    int numberOfBalls();

    /**
     * he initial velocity of each ball.
     *
     * @return - list of velocities of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddle speed.
     *
     * @return int speed of the paddle.
     */
    int paddleSpeed();

    /**
     * paddle Width - the width of the paddle.
     *
     * @return - int the width of the paddle.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return string - the level name.
     */
    String levelName();

    /**
     * returns a sprite with the background of the level.
     *
     * @return Sprite - background,
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return - list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     *
     * @return int - num of blocks.
     */
    int numberOfBlocksToRemove();
}