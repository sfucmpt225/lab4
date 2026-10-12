import java.util.NoSuchElementException;

/**
 * Part 2: a queue of ints stored in a circular array with a fixed capacity.
 *
 * The queue's elements sit in count consecutive slots starting at index
 * front, wrapping around from the last index of the array back to index 0.
 * For example, with capacity 4, front = 3 and count = 3:
 *
 *     index:    0     1     2     3
 *     data:  [  5 ][  6 ][    ][  4 ]      queue, front to back: 4 5 6
 *
 * Rules: every method must run in O(1) time (no loops, and never shift the
 * elements), and you may not use any java.util collection. You may add
 * private helper methods.
 */
public class CircularQueue {

    private int[] data;   // the circular array; data.length is the capacity
    private int front;    // index of the front element
    private int count;    // number of elements in the queue

    /**
     * Creates an empty queue that can hold up to capacity elements.
     *
     * @throws IllegalArgumentException if capacity < 1
     */
    public CircularQueue(int capacity) {
        // TODO: check capacity, create the array, and start with an empty queue.
    }

    /**
     * Adds x at the back of the queue.
     *
     * @throws IllegalStateException if the queue is full (the queue is left unchanged)
     */
    public void enqueue(int x) {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("enqueue is not implemented yet");
    }

    /**
     * Removes and returns the value at the front of the queue.
     *
     * @throws NoSuchElementException if the queue is empty
     */
    public int dequeue() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("dequeue is not implemented yet");
    }

    /**
     * Returns the value at the front of the queue without removing it.
     *
     * @throws NoSuchElementException if the queue is empty
     */
    public int peek() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("peek is not implemented yet");
    }

    /** Returns true if the queue has no elements. */
    public boolean isEmpty() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("isEmpty is not implemented yet");
    }

    /** Returns true if the queue holds as many elements as its capacity. */
    public boolean isFull() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("isFull is not implemented yet");
    }

    /** Returns the number of elements in the queue. */
    public int size() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("size is not implemented yet");
    }
}
