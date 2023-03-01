import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

/**
 * Game class - the class the responsible for the game.
 */
public class GameLevel implements Animation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private LevelInformation levelInformation;
    private Counter ballsLeft;
    private AnimationRunner runner;
    private boolean running;
    private Counter score;
    private Counter blocksLeft;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gameGUI;
    private KeyboardSensor keyboard;


    /**
     * Constructor.
     *
     * @param levelInformation - a given level with his info.
     * @param animationRunner  - the class that runs the game.
     * @param score            - the score of the game.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner animationRunner, Counter score) {
        this.score = score;
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.gameGUI = runner.getGui();
        this.keyboard = this.gameGUI.getKeyboardSensor();
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.scoreIndicator = new ScoreIndicator(this.score);
        this.blocksLeft = new Counter();
        this.ballsLeft = new Counter();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * add a collidable to the environment.
     *
     * @param object -  the object to add
     */
    public void addCollidable(Collidable object) {
        this.environment.addCollidable(object);
    }

    /**
     * add Sprite to the game.
     *
     * @param s - the sprite we want to add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * method to initialize the game.
     */
    public void initialize() {
        this.running = true;
        scoreIndicator.addToGame(this);
        initBackGround();
        initPaddle();
        initBlocks();
        initBalls();
        setBounds();
        initDeathRegion();
    }

    /**
     * function to set the borders of the screen.
     */
    public void setBounds() {
        Block leftWall = new Block(new Rectangle(new Point(0, 20), 30, HEIGHT),
                Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(770, 20), 30, HEIGHT), Color.GRAY);
        Block topBorder = new Block(new Rectangle(new Point(0, 20), 770, 30),
                Color.GRAY);
        for (Block block : Arrays.asList(leftWall, rightWall, topBorder)) {
            block.addToGame(this);
        }
    }

    /**
     * initBackGround - add the lvl background as a sprite.
     */
    public void initBackGround() {
        addSprite(levelInformation.getBackground());
    }

    /**
     * init the blocks rows.
     */
    public void initBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, blocksLeft);
        List<Block> blockList = levelInformation.blocks();
        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
            blockList.get(i).addToGame(this);
            blocksLeft.increase(1);
            blockList.get(i).addHitListener(blockRemover);
            blockList.get(i).addHitListener(scoreTrackingListener);
        }


    }

    /**
     * init the paddle.
     */
    public void initPaddle() {
        Paddle paddle = new Paddle(new Block(new Rectangle(new Point(
                (WIDTH - this.levelInformation.paddleWidth()) / 2, 570),
                levelInformation.paddleWidth(),
                Paddle.HEIGHT), Color.getHSBColor(0.8f, 0.23f, 0.8f)), levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    private void initDeathRegion() {
        Block deathRegion = new Block(new Rectangle(new Point(30, HEIGHT), 750, 15),
                Color.getHSBColor(0.73f, 0.5f, 0.1f));
        BallRemover ballRemover = new BallRemover(this, ballsLeft);
        addSprite(deathRegion);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);
    }

    /**
     * init the balls of the game.
     */
    public void initBalls() {
        List<Velocity> velocityList = levelInformation.initialBallVelocities();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 450), 5, Color.white, this.environment);
            ball.setVelocity(velocityList.get(i));
            ballsLeft.increase(1);
            ball.addToGame(this);
        }
    }

    /**
     * getter .
     *
     * @return the gameGUI.
     */
    public GUI getGameGUI() {
        return gameGUI;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.runner.run(this);
    }

    /**
     * function to close the game.
     */
    public void closeGame() {
        if (ballsLeft.getValue() == 0) {
            this.running = false;
        }
        if (blocksLeft.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
    }

    /**
     * remove a collidable from the game.
     *
     * @param c - collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }


    /**
     * remove a sprite from the game.
     *
     * @param s - sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, new PauseScreen()));
        }
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, WIDTH, 20);
        d.setColor(Color.getHSBColor(0.5f, 0.9f, 0.12f));
        d.fillRectangle(30, 30, 750, 570);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        closeGame();
    }


    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * getter of the balls.
     *
     * @return Counter - ballsLeft
     */
    public Counter getBallsLeft() {
        return this.ballsLeft;
    }
}