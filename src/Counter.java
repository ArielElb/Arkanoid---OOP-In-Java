/**
 * Counter - hold an integer to count something.
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     *
     * @param number - number to add
     */
    void increase(int number) {
        counter += number;
    }


    /**
     * subtract number from current count.
     *
     * @param number - subtract this number
     */
    void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     *
     * @return int count
     */
    int getValue() {
        return counter;
    }
}

