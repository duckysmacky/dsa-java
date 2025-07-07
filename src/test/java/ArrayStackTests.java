import io.github.duckysmacky.dsa.collections.stack.ArrayStack;
import io.github.duckysmacky.dsa.collections.stack.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTests {
    Stack<String> stack;

    @BeforeEach
    public void initializeStack() {
        stack = new ArrayStack<>(5);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
    }

    @Test
    public void testGeneralStack() {
        assertEquals(4, stack.size());
        assertFalse(stack.isFull());
        assertFalse(stack.isEmpty());
        assertEquals("[a - b - c > d]", stack.toString());

        stack.clear();
        assertEquals(0, stack.size());
        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
        assertEquals("[]", stack.toString());
    }

    @Test
    public void testPush() {
        final int lastSize = stack.size();
        final String elem = "X";

        stack.push(elem);
        assertEquals("[a - b - c - d > X]", stack.toString());
        assertEquals(lastSize + 1, stack.size());
        assertEquals(elem, stack.peek());

        Executable pushWhenAlreadyFull = () -> stack.push("X");
        assertThrows(IllegalStateException.class, pushWhenAlreadyFull);
        assertFalse(stack.offer("X"));

        stack.clear();
        stack.push(elem);
        assertEquals("[X]", stack.toString());
        assertEquals(elem, stack.peek());
    }

    @Test
    public void testPeek() {
        final String lastTop = "d";
        final String newTop = "X";

        stack.push(newTop);
        assertEquals(newTop, stack.peek());

        stack.pop();
        assertEquals(lastTop, stack.peek());

        stack.clear();
        assertNull(stack.peek());
    }

    @Test
    public void testPop() {
        final String lastTop = stack.peek();
        final String newTop = "X";

        stack.push(newTop);
        assertEquals(newTop, stack.pop());
        assertEquals(lastTop, stack.peek());

        stack.clear();
        Executable popWhenEmpty = () -> stack.pop();
        assertThrows(NoSuchElementException.class, popWhenEmpty);
        assertNull(stack.poll());

        stack.push(newTop);
        assertEquals(newTop, stack.pop());
    }
}
