/**
 * A node of a singly linked list of ints.
 */
class Node {
    int  data;
    Node next;

    Node(int data)            { this(data, null); }
    Node(int data, Node next) { this.data = data; this.next = next; }
}
