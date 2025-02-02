package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first, last;
    private static int shortbound;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = 0;
        last = 0;
        size = 0;
        shortbound = 16;

    }

    public void addFirst(T item) {
        if (size == this.items.length) {
            resize(size * 2);
        }
        first--;
        this.items[adjust(first)] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == this.items.length) {
            resize(size * 2);
        }
        this.items[adjust(last)] = item;
        last++;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (adjust(first) >= adjust(last) && size != 0) {
            for (int i = adjust(first); i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < adjust(last); i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = adjust(first); i < adjust(last); i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        T target = items[adjust(first)];
        if (size == 0) {
            return null;
        }
        if (size <= items.length / 4 && items.length >= shortbound) {
            this.resize(size);
        }
        items[adjust(first)] = null;
        first++;
        size--;
        return target;
    }

    public T removeLast() {
        T target = items[adjust(last - 1)];
        if (size == 0) {
            return null;
        }
        if (size <= items.length / 4 && items.length >= shortbound) {
            this.resize(size);
        }
        items[adjust(last - 1)] = null;
        last--;
        size--;
        return target;
    }

    public T get(int index) {
        return items[adjust(adjust(first) + index)];
    }


    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int j = 0;
        if (adjust(first) >= adjust(last) && size != 0) {
            for (int i = adjust(first); i < items.length; i++) {
                a[j] = items[i];
                j++;
            }
            for (int i = 0; i < adjust(last); i++) {
                a[j] = items[i];
                j++;
            }
        } else {
            for (int i = adjust(first); i < adjust(last); i++) {
                a[j] = items[i];
                j++;
            }
        }
        items = a;
        first = 0;
        last = size;
    }

    private int adjust(int target) {
        if (target >= 0 && target < items.length) {
            return target;
        } else if (target >= items.length) {
            return target - items.length;
        } else {
            return items.length + target;
        }
    }
}
