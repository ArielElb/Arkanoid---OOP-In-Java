import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelFour - last level of the game.
 */
public class LevelFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(-30 + i * 45, 5));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Level Name: Final Four";
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
                surface.setColor(new Color(0x1EC5C8));
                surface.fillRectangle(30, 30, GameLevel.WIDTH, 20);
                surface.fillRectangle(30, 30, 750, 570);
                surface.setColor(Color.black);
                surface.drawText(600, 15, levelName(), 15);
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
        for (int i = 1; i <= 20; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 37, 100), 37, 20),
                    Color.getHSBColor(0.73f, 0.5f, 0.5f)));
        }
        for (int i = 1; i <= 20; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 37, 120),
                    37, 20), Color.PINK));
        }
        for (int i = 1; i <= 20; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 37, 140),
                    37, 20), Color.yellow));
        }
        for (int i = 1; i <= 20; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 37, 160),
                    37, 20), Color.magenta));

        }
        for (int i = 1; i <= 20; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 37, 180),
                    37, 20), Color.green));

        }
        for (int i = 1; i <= 20; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 37, 200),
                    37, 20), Color.gray));

        }

        for (int i = 1; i <= 29; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 37, 220),
                    37, 20), Color.cyan));
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 140;
    }
}
