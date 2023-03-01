import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * First level of the game.
 */
public class LevelOne implements LevelInformation {


    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(Velocity.fromAngleAndSpeed(0, 6));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Level Name: Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            /**
             * draw the sprite to the screen.
             *
             * @param surface - the surface we're drawing on.
             */
            @Override
            public void drawOn(DrawSurface surface) {
                surface.setColor(new Color(0, 0, 0));
                surface.fillRectangle(30, 30, GameLevel.WIDTH, 20);
                surface.fillRectangle(30, 30, 750, 570);
                surface.setColor(Color.black);
                surface.drawText(600, 15, levelName(), 15);
                surface.setColor(Color.blue);
                surface.drawLine(230, 180, 570, 180);
                surface.drawLine(400, 30, 400, 330);
                for (int i = 0; i <= 3; i++) {
                    surface.drawCircle(400, 180, i * 40);
                }
            }

            /**
             * notify the sprite that time has passed.
             */
            @Override
            public void timePassed() {

            }
        };

        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        list.add(new Block(new Rectangle(new Point(380, 160),
                40, 40), Color.red));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
