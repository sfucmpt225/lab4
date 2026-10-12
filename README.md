# lab4
CMPT225 Lab4

In this lab you build two classic data structures yourself: a **stack** stored in a singly linked list and a **queue** stored in a circular array. Then you use your stack to solve two small problems. 

## Objectives
- Implementing a stack with a singly linked list, with every operation in O(1) time.
- Implementing a queue with a circular array, with every operation in O(1) time.
- Handling the edge cases (empty stack, empty queue, full queue) by throwing exceptions.
- Using a stack to reverse the order of things.

## Files
| File | What to do |
|:--|:--|
| `Node.java` | Given. The usual `Node` class. Do not change it. |
| `LinkedStack.java` | **Part 1.** Complete the methods marked `TODO`. |
| `CircularQueue.java` | **Part 2.** Complete the constructor and the methods marked `TODO`. |
| `StackApps.java` | **Part 3.** Complete the two methods. |
| `SampleTests.java` | Given. Quick checks to run to ensure your program works properly. |

Each method you need to write currently throws an `UnsupportedOperationException`. Replace that line with your code. 

## Lab Rules
1. **Keep the given interface.** Do not rename the classes or methods, change any parameter or return type, or add a `package` line. Use the given fields. You may add `private` helper methods.
2. **No library collections.** Do not use anything from `java.util` except `NoSuchElementException`: no `ArrayList`, `LinkedList`, `ArrayDeque`, `Stack`, `Arrays` and so on.
3. **O(1) operations.** Every method in Parts 1 and 2 must run in O(1) time: no loops, and never shift elements.
4. **Use your stack in Part 3.** Both Part 3 methods must use a `LinkedStack`.

## Part 1: `LinkedStack` (15 marks)
A stack of `int` values stored in a singly linked list of `Node`s. The **top of the stack is the first node of the list**, so every operation works at the front of the list and nothing ever walks down it.

```text
After push(10), push(20), push(30):    top → [30] → [20] → [10] → ∅      size = 3
After pop() returns 30:                top → [20] → [10] → ∅             size = 2
```

The fields are given:

```java
private Node top;   // first node of the list = top of the stack (null when empty)
private int size;   // number of elements in the stack
```

| Method | What it does |
|:--|:--|
| `void push(int x)` | Adds `x` on top of the stack. |
| `int pop()` | Removes and returns the top value. Throws `NoSuchElementException` if the stack is empty. |
| `int peek()` | Returns the top value **without removing it**. Throws `NoSuchElementException` if the stack is empty. |
| `boolean isEmpty()` | Returns `true` if the stack has no elements. |
| `int size()` | Returns the number of elements. |

The constructor is given: a new stack has `top == null` and `size == 0`.

> **Hint:** `push` creates a new node whose `next` is the old top. Draw the list before and after each operation to visualize what's happening internally.

## Part 2: `CircularQueue` (20 marks)

A queue of `int` values stored in an array with a fixed capacity. If the front of the queue always stayed at index 0, every `dequeue` would have to shift the remaining elements left, which takes O(n) time. Instead, the queue's elements can start anywhere in the array and **wrap around** from the last index back to index 0.

The fields are given:

```java
private int[] data;   // the circular array; data.length is the capacity
private int front;    // index of the front element
private int count;    // number of elements in the queue
```

The elements sit in `count` consecutive slots starting at `front`, wrapping around:

- the front element is `data[front]`;
- `enqueue` writes to the first free slot, at index `(front + count) % data.length`;
- `dequeue` moves the front forward: `front = (front + 1) % data.length`.

**Example** with capacity 4:

| Operation | Returns | `front` | `count` | `data[0]` | `data[1]` | `data[2]` | `data[3]` | Queue (front → back) |
|:--|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--|
| `new CircularQueue(4)` | | 0 | 0 | – | – | – | – | (empty) |
| `enqueue(1)` … `enqueue(4)` | | 0 | 4 | 1 | 2 | 3 | 4 | 1 2 3 4 (full) |
| `dequeue()` | 1 | 1 | 3 | – | 2 | 3 | 4 | 2 3 4 |
| `enqueue(5)` | | 1 | 4 | **5** | 2 | 3 | 4 | 2 3 4 5 (full) |
| `dequeue()` | 2 | 2 | 3 | 5 | – | 3 | 4 | 3 4 5 |

`enqueue(5)` writes to index (1 + 3) % 4 = **0**: the queue has wrapped around. A `–` marks a slot that is not part of the queue; whatever value is left there does not matter.

| Method | What it does |
|:--|:--|
| `CircularQueue(int capacity)` | Creates an empty queue that can hold up to `capacity` elements. Throws `IllegalArgumentException` if `capacity < 1`. |
| `void enqueue(int x)` | Adds `x` at the back. Throws `IllegalStateException` if the queue is full, and leaves the queue unchanged. |
| `int dequeue()` | Removes and returns the front value. Throws `NoSuchElementException` if the queue is empty. |
| `int peek()` | Returns the front value **without removing it**. Throws `NoSuchElementException` if the queue is empty. |
| `boolean isEmpty()` | Returns `true` if the queue has no elements. |
| `boolean isFull()` | Returns `true` if the queue holds `capacity` elements. |
| `int size()` | Returns the number of elements. |

> **Hint:** In `enqueue`, `dequeue` and `peek`, check for the edge case first and throw. Only then change the state of the queue object. 

## Part 3: Using your stack (10 marks)

Both methods are `static` and go in `StackApps.java`. Each one must use a `LinkedStack`.

### (a) `static String toBinary(int n)` (5 marks)

Returns `n` written in binary (base 2), with no leading zeros. Throws `IllegalArgumentException` if `n < 0`.

To convert `n`, divide it by 2 again and again. Each remainder `n % 2` is one bit, but the bits come out **last bit first**. Push each remainder onto a stack, then pop them all to build the string in the right order:

```text
n = 13:   13 % 2 = 1,  13 / 2 = 6
           6 % 2 = 0,   6 / 2 = 3
           3 % 2 = 1,   3 / 2 = 1
           1 % 2 = 1,   1 / 2 = 0   (stop)

pushed: 1, 0, 1, 1    popped: 1, 1, 0, 1    result: "1101"
```

| Call | Result |
|:--|:--|
| `toBinary(0)` | `"0"` |
| `toBinary(1)` | `"1"` |
| `toBinary(6)` | `"110"` |
| `toBinary(13)` | `"1101"` |
| `toBinary(255)` | `"11111111"` |
| `toBinary(-4)` | throws `IllegalArgumentException` |

Do not use `Integer.toBinaryString`, `Integer.toString(n, 2)` or `String.format`.

### (b) `static void reverse(CircularQueue q)` (5 marks)

Reverses the order of the elements in `q`, so the element at the back ends up at the front. Use a `LinkedStack` as your only extra storage.

```text
before:  q (front → back) = 1 2 3 4
after:   q (front → back) = 4 3 2 1
```

The method must change `q` itself. Writing `q = new CircularQueue(...)` inside the method does **not** change the caller's queue, because Java passes the reference by value.

> **Hint:** A stack gives values back in the opposite order from the one they went in.

## Testing
`SampleTests.java` prints `PASS` or `FAIL` for 14 quick checks. Also, try testing the following edge cases yourself:
- `pop()` and `peek()` on an empty stack, including one that has just been emptied;
- a queue that is filled to capacity, emptied, then filled again;
- a queue whose elements wrap around the end of the array many times (for example, capacity 3 with many enqueues and dequeues);
- `new CircularQueue(0)`, and a queue of capacity 1;
- `toBinary(0)`, a large `n` such as `Integer.MAX_VALUE`, and a negative `n`;
- `reverse` on an empty queue, a one-element queue and a full queue.

## Rubric
### 

## Submission
Zip the project directory and submit the ZIP file to Canvas.
**Please do not remove any of the build artifacts or the manifest files. Zip the project directory as is.**
**Your program must compile and run successfully. If your program does not compile or crashes during execution, it will receive a grade of zero.**

## Deadline
Sunday, October 11, 2026, at 11:59 PM PDT