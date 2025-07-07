import io.github.duckysmacky.dsa.collections.queue.LinkedQueue;
import io.github.duckysmacky.dsa.collections.queue.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedQueueTests {
    Queue<String> queue;

    @BeforeEach
    public void initializeQueue() {
        queue = new LinkedQueue<>();
        queue.add("first");
        queue.add("middle");
        queue.add("last");
    }

    @Test
    public void testGeneralQueue() {
        assertEquals(3, queue.size());
        assertFalse(queue.isFull());
        assertFalse(queue.isEmpty());
        assertEquals("[first < middle < last]", queue.toString());

        queue.clear();
        assertEquals(0, queue.size());
        assertFalse(queue.isFull());
        assertTrue(queue.isEmpty());
        assertEquals("[]", queue.toString());
    }

    @Test
    public void testAdd() {
        final int lastSize = queue.size();

        queue.add("final");
        assertEquals("[first < middle < last < final]", queue.toString());
        assertEquals(lastSize + 1, queue.size());

        assertTrue(queue.offer("more"));

        queue.clear();
        queue.add("only");
        assertEquals("[only]", queue.toString());
        assertEquals("only", queue.peek());
    }

    @Test
    public void testPeek() {
        final String first = "first";
        final String second = "middle";

        assertEquals(first, queue.peek());

        queue.remove();
        assertEquals(second, queue.peek());

        queue.clear();
        assertNull(queue.peek());
    }

    @Test
    public void testPop() {
        final String first = "first";
        final String second = "middle";

        assertEquals(first, queue.peek());
        assertEquals(first, queue.remove());
        assertEquals(second, queue.peek());
        assertNotNull(queue.poll());

        queue.add("final");
        assertEquals("[last < final]", queue.toString());
        assertEquals("last", queue.remove());
        assertEquals("[final]", queue.toString());

        queue.clear();
        Executable removeWhenEmpty = () -> queue.remove();
        assertThrows(NoSuchElementException.class, removeWhenEmpty);
        assertNull(queue.poll());
    }
}
