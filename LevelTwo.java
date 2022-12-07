import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the second level of the game.
 */
public class LevelTwo implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(-74 + i * 18, 6));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Level Name: Wide Easy";
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
                surface.setColor(new Color(255, 255, 255));
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
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i * 35, 280), 35, 25), Color.cyan));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + 2 * 35 + i * 35, 280), 35, 25),
                    Color.pink));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 4 * 35 + i * 35, 280), 35, 25),
                    Color.red));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 6 * 35 + i * 35, 280), 35, 25),
                    Color.blue));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 8 * 35 + i * 35, 280), 35, 25),
                    Color.lightGray));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 10 * 35 + i * 35, 280), 35, 25),
                    new Color(149, 149, 204)));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 12 * 35 + i * 35, 280), 35, 25),
                    new Color(225, 134, 0)));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 14 * 35 + i * 35, 280), 35, 25),
                    new Color(139, 255, 40)));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 16 * 35 + i * 35, 280), 35, 25),
                    new Color(230, 93, 93, 255)));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Block(new Rectangle(new Point(30 + i + 18 * 35 + i * 35, 280), 35, 25),
                    new Color(255, 224, 224)));
        }
        for (int i = 0; i < 1; i++) {
            list.add(new Block(new Rectangle(new Point(731, 280), 40, 25), new Color(158,
                    31, 111)));
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 21;
    }
}
