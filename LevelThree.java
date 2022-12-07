import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the 3rd level of the game.
 */
public class LevelThree implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(-30 + i * 45, 6));
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
        return "Level Name: Normal";
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
                surface.setColor(new Color(28, 127, 29));
                surface.fillRectangle(30, 30, 750, 570);
                surface.setColor(new Color(0, 0, 0));
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
        for (int i = 1; i <= 12; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 45, 140), 45, 20),
                    Color.getHSBColor(0.73f, 0.5f, 0.5f)));
        }
        for (int i = 1; i <= 11; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 45, 160),
                    45, 20), Color.PINK));
        }
        for (int i = 1; i <= 10; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 45, 180),
                    45, 20), Color.yellow));
        }
        for (int i = 1; i <= 9; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 45, 200),
                    45, 20), Color.red));

        }
        for (int i = 1; i <= 8; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 45, 220),
                    45, 20), Color.green));
        }
        for (int i = 1; i <= 7; i++) {
            list.add(new Block(new Rectangle(new Point(770 - i * 45, 240),
                    45, 20),
                    Color.getHSBColor(0.57f, 0.7f, 0.9f)));
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 57;
    }
}
