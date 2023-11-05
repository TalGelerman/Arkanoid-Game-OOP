// * Tal Gelerman 322280850
/**
 * Counter class.
 */
public class Counter {
    private int currentCount;

    /**
     * counter constructor.
     */
    public Counter() {
        this.currentCount = 0;
    }

    /**
     * add number to current count.
     *
     * @param number a number
     */
    public void increase(int number) {
        currentCount = currentCount + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number a number
     */
    void decrease(int number) {
        currentCount = currentCount - number;
    }

    /**
     * get current count.
     *
     * @return current count
     */
    int getValue() {
        return this.currentCount;
    }
}
