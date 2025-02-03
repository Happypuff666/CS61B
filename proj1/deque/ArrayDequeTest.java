package deque;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    // 测试多次添加和删除操作
    @Test
    public void testMultipleAddAndRemove() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(25);

        // 当前队列：5, 10, 20, 25
        assertEquals(4, deque.size());
        assertEquals((Integer) 5, deque.get(0)); // 第一个元素应该是5
        assertEquals((Integer) 10, deque.get(1)); // 第二个元素应该是10
        assertEquals((Integer) 20, deque.get(2)); // 第三个元素应该是20
        assertEquals((Integer) 25, deque.get(3)); // 第四个元素应该是25

        assertEquals((Integer) 5, deque.removeFirst()); // 移除并返回5
        assertEquals((Integer) 25, deque.removeLast()); // 移除并返回25
        assertEquals(2, deque.size()); // 现在队列中应该有2个元素
        assertEquals((Integer) 10, deque.get(0)); // 第一个元素应该是10
        assertEquals((Integer) 20, deque.get(1)); // 第二个元素应该是20
    }

    // 测试循环数组的情况：前端和后端交替添加
    @Test
    public void testCircularArrayBehavior() {
        Deque<Integer> deque = new ArrayDeque<>();

        // 添加足够的元素，触发数组循环行为
        deque.addLast(10); // 队列: [10]
        deque.addLast(20); // 队列: [10, 20]
        deque.addFirst(5); // 队列: [5, 10, 20]
        deque.addLast(30); // 队列: [5, 10, 20, 30]
        deque.addFirst(2); // 队列: [2, 5, 10, 20, 30]

        // 当前队列：2, 5, 10, 20, 30
        assertEquals(5, deque.size());
        assertEquals((Integer) 2, deque.get(0)); // 第一个元素应该是2
        assertEquals((Integer) 5, deque.get(1)); // 第二个元素应该是5
        assertEquals((Integer) 10, deque.get(2)); // 第三个元素应该是10
        assertEquals((Integer) 20, deque.get(3)); // 第四个元素应该是20
        assertEquals((Integer) 30, deque.get(4)); // 第五个元素应该是30

        // 移除前端和后端的元素，测试数组循环
        deque.removeFirst(); // 移除并返回2
        deque.removeLast(); // 移除并返回30

        // 当前队列：5, 10, 20
        assertEquals(3, deque.size());
        assertEquals((Integer) 5, deque.get(0)); // 第一个元素应该是5
        assertEquals((Integer) 10, deque.get(1)); // 第二个元素应该是10
        assertEquals((Integer) 20, deque.get(2)); // 第三个元素应该是20
    }

    // 测试动态调整大小：数组大小变化
    @Test
    public void testResizeArray() {
        Deque<Integer> deque = new ArrayDeque<>();

        // 向Deque中添加大量元素，测试数组扩展
        for (int i = 0; i < 1000; i++) {
            deque.addLast(i);
        }

        assertEquals(1000, deque.size()); // 应该有1000个元素
        assertEquals((Integer) 0, deque.get(0)); // 第一个元素应该是0
        assertEquals((Integer) 999, deque.get(999)); // 最后一个元素应该是999

        // 移除元素，测试数组缩小
        for (int i = 0; i < 900; i++) {
            deque.removeFirst();
        }

        assertEquals(100, deque.size()); // 应该剩下100个元素
        assertEquals((Integer) 900, deque.get(0)); // 第一个元素应该是100
        assertEquals((Integer) 999, deque.get(99)); // 最后一个元素应该是999
    }

    // 测试边界情况：添加和删除后边界
    @Test
    public void testBoundaryRemoval() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(10);
        deque.addFirst(20);

        // 当前队列：20, 10
        assertEquals((Integer) 20, deque.removeFirst()); // 移除并返回20
        assertEquals((Integer) 10, deque.removeLast());  // 移除并返回10

        assertTrue(deque.isEmpty()); // 队列应该是空的
    }

    // 测试get方法的边界情况
    @Test
    public void testGetOutOfBounds() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(10);
        deque.addLast(20);

        assertNull(deque.get(2)); // 索引越界，应该返回null
        assertNull(deque.get(-1)); // 索引越界，应该返回null
    }

    // 测试性能：快速测试大量操作
    @Test(timeout = 1000)  // 如果运行时间超过1秒则测试失败
    public void testPerformance() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100000; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 100000; i++) {
            deque.removeFirst();
        }
        assertTrue(deque.isEmpty());
    }
}
