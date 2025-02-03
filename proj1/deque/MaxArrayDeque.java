package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.cmp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T current, result;
        result = this.get(0);
        for (int i = 1; i < size(); i++) {
            current = this.get(i);
            result = cmp.compare(current, result) > 0? current: result;
        }
        return result;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T current, result;
        result = this.get(0);
        for (int i = 1; i < size(); i++) {
            current = this.get(i);
            result = c.compare(current, result) > 0? current: result;
        }
        return result;
    }



}
