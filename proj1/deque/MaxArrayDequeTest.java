package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;


public class MaxArrayDequeTest {

    private static class Intcmp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class Strcmp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    public void testIntComparator() {
        Intcmp cmp = new Intcmp();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);
        deque.addFirst(10);
        deque.addLast(20);

        assertEquals((int)deque.max(), 20);

    }

    @Test
    public void testStrComparator() {
        Strcmp cmp = new Strcmp();
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(cmp);
        deque.addFirst("I");
        deque.addLast("have");
        deque.addLast("a");
        deque.addLast("plan");

        assertEquals("plan",(String) deque.max());

    }


}
