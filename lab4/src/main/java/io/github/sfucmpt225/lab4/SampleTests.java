import java.util.NoSuchElementException;

/**
 * Quick checks for the Stacks and Queues assignment.
 */
public class SampleTests {

    private static int passed = 0;
    private static int total = 0;

    /** One check. It returns true if it passes; it may also throw an exception. */
    private interface Check {
        boolean run();
    }

    public static void main(String[] args) {
        System.out.println("Part 1: LinkedStack");

        check("push 1, 2, 3 then pop three times gives 3, 2, 1", () -> {
            LinkedStack s = new LinkedStack();
            s.push(1);
            s.push(2);
            s.push(3);
            return s.pop() == 3 && s.pop() == 2 && s.pop() == 1;
        });

        check("peek() returns the top value without removing it", () -> {
            LinkedStack s = new LinkedStack();
            s.push(5);
            s.push(8);
            return s.peek() == 8 && s.peek() == 8 && s.pop() == 8 && s.peek() == 5;
        });

        check("a new stack is empty and has size 0", () -> {
            LinkedStack s = new LinkedStack();
            return s.isEmpty() && s.size() == 0;
        });

        check("size() and isEmpty() after 3 pushes and 1 pop", () -> {
            LinkedStack s = new LinkedStack();
            s.push(1);
            s.push(2);
            s.push(3);
            s.pop();
            return s.size() == 2 && !s.isEmpty();
        });

        check("pop() on an empty stack throws NoSuchElementException",
              () -> throwsException(NoSuchElementException.class, () -> new LinkedStack().pop()));

        System.out.println();
        System.out.println("Part 2: CircularQueue");

        check("enqueue 1, 2, 3 then dequeue three times gives 1, 2, 3", () -> {
            CircularQueue q = new CircularQueue(5);
            q.enqueue(1);
            q.enqueue(2);
            q.enqueue(3);
            return q.dequeue() == 1 && q.dequeue() == 2 && q.dequeue() == 3;
        });

        check("peek() returns the front value without removing it", () -> {
            CircularQueue q = new CircularQueue(5);
            q.enqueue(5);
            q.enqueue(8);
            return q.peek() == 5 && q.peek() == 5 && q.dequeue() == 5 && q.peek() == 8;
        });

        check("isEmpty(), isFull() and size() as a capacity-3 queue fills up", () -> {
            CircularQueue q = new CircularQueue(3);
            boolean ok = q.isEmpty() && !q.isFull() && q.size() == 0;
            q.enqueue(1);
            ok = ok && !q.isEmpty() && !q.isFull() && q.size() == 1;
            q.enqueue(2);
            q.enqueue(3);
            return ok && !q.isEmpty() && q.isFull() && q.size() == 3;
        });

        check("the wrap-around example from the instructions (capacity 4)", () -> {
            CircularQueue q = new CircularQueue(4);
            q.enqueue(1);
            q.enqueue(2);
            q.enqueue(3);
            q.enqueue(4);
            boolean ok = q.dequeue() == 1;
            q.enqueue(5);   // goes into index 0
            return ok && q.dequeue() == 2 && q.dequeue() == 3
                      && q.dequeue() == 4 && q.dequeue() == 5 && q.isEmpty();
        });

        check("dequeue() on an empty queue throws NoSuchElementException",
              () -> throwsException(NoSuchElementException.class, () -> new CircularQueue(3).dequeue()));

        check("enqueue() on a full queue throws IllegalStateException", () -> {
            CircularQueue q = new CircularQueue(2);
            q.enqueue(1);
            q.enqueue(2);
            return throwsException(IllegalStateException.class, () -> q.enqueue(3));
        });

        System.out.println();
        System.out.println("Part 3: StackApps");

        check("toBinary(6) returns \"110\"", () -> "110".equals(StackApps.toBinary(6)));

        check("toBinary(13) returns \"1101\"", () -> "1101".equals(StackApps.toBinary(13)));

        check("reverse() turns 1 2 3 4 into 4 3 2 1", () -> {
            CircularQueue q = new CircularQueue(5);
            for (int i = 1; i <= 4; i++) {
                q.enqueue(i);
            }
            StackApps.reverse(q);
            return q.dequeue() == 4 && q.dequeue() == 3 && q.dequeue() == 2
                && q.dequeue() == 1 && q.isEmpty();
        });

        System.out.println();
        System.out.println(passed + " of " + total + " checks passed.");
    }

    /** Runs one check and prints PASS or FAIL (with the exception, if one was thrown). */
    private static void check(String description, Check c) {
        total++;
        String result;
        try {
            if (c.run()) {
                passed++;
                result = "PASS  " + description;
            } else {
                result = "FAIL  " + description;
            }
        } catch (Throwable e) {
            result = "FAIL  " + description + "\n        threw " + e;
        }
        System.out.println("  " + result);
    }

    /**
     * Returns true if running code throws an exception of the given type,
     * false if it throws nothing. Any other exception is passed on to check().
     */
    private static boolean throwsException(Class<? extends Throwable> type, Runnable code) {
        try {
            code.run();
        } catch (Throwable e) {
            if (type.isInstance(e)) {
                return true;
            }
            throw e;   // a different exception: let check() report it
        }
        return false;  // nothing was thrown
    }
}
