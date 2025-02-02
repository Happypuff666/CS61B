package deque;

public class LinkedListDeque<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    //create empty deque
    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //add item to first
    public void addFirst(T item) {
        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size++;
    }

    //add item to last
    public void addLast(T item) {
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.\n
    public void printDeque() {
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        T target = sentinel.next.item;
        if (target == null) {
            return null;
        }
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return target;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        T target = sentinel.prev.item;
        if (target == null) {
            return null;
        }
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return target;
    }

    public T get(int index) {
        Node p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0) {
            return sentinel.item;
        }
        sentinel = sentinel.next;
        T result = getRecursive(index - 1);
        sentinel = sentinel.prev;
        return result;
    }

}
