// * Tal Gelerman 322280850
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A class that implements the animation interface and can be stopped by pressing a key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private String keyPressed;
    private KeyboardSensor keyboardSensor;
    private boolean running;

    /**
     * key pressed stoppable animation Constructor.
     * @param sensor the keyboard sensor
     * @param key    the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.running = false;
        this.animation = animation;
        this.keyPressed = key;
        this.keyboardSensor = sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.keyPressed)) {
            this.running = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}