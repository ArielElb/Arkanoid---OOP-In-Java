import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * decorator class.
 **/
public class KeyPressStoppableAnimation implements Animation {
    //    private boolean isAlreadyPressed;
    private Animation decorated;
    private String keyPressed;
    private KeyboardSensor sensor;
    private boolean stop;

    protected KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.decorated = animation;
        this.keyPressed = key;
        this.stop = false;
        this.sensor = sensor;
//        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.decorated.doOneFrame(d);
        if (this.sensor.isPressed(this.keyPressed)) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}