import java.util.NoSuchElementException;

/**
 * Part 1: a stack of ints stored in a singly linked list.
 *
 * The top of the stack is the first node of the list. After push(10),
 * push(20) and push(30), the list looks like this:
 *
 *     top -> [30] -> [20] -> [10] -> null        size = 3
 *
 * Rules: every method must run in O(1) time (no loops), and you may not use
 * any java.util collection. You may add private helper methods.
 */
public class LinkedStack {

    private Node top;   // first node of the list = top of the stack (null when empty)
    private int size;   // number of elements in the stack

    /** Creates an empty stack. (Given: nothing to do here.) */
    public LinkedStack() {
        top = null;
        size = 0;
    }

    /** Adds x to the top of the stack. */
    public void push(int x) {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("push is not implemented yet");
    }

    /**
     * Removes and returns the value on top of the stack.
     *
     * @throws NoSuchElementException if the stack is empty
     */
    public int pop() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("pop is not implemented yet");
    }

    /**
     * Returns the value on top of the stack without removing it.
     *
     * @throws NoSuchElementException if the stack is empty
     */
    public int peek() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("peek is not implemented yet");
    }

    /** Returns true if the stack has no elements. */
    public boolean isEmpty() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("isEmpty is not implemented yet");
    }

    /** Returns the number of elements in the stack. */
    public int size() {
        // TODO: replace this line with your code.
        throw new UnsupportedOperationException("size is not implemented yet");
    }
}
