package deque;

public class ArrayDeque<T> implements Deque<T> {
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

    @Override
    public void addFirst(T item) {
        if (size == this.items.length) {
            resize(size * 2);
        }
        first--;
        this.items[adjust(first)] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == this.items.length) {
            resize(size * 2);
        }
        this.items[adjust(last)] = item;
        last++;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
        int result;
        if (target >= 0 && target < items.length) {
            result = target;
        } else if (target >= items.length) {
            result =  target - items.length;
        } else {
            result = items.length + target;
        }
        if (result >= items.length || result < 0) {
            result = adjust(result);
        }
        return result;
    }
}
