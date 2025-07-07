import io.github.duckysmacky.dsa.collections.stack.ArrayStack;
import io.github.duckysmacky.dsa.collections.stack.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


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

        assertEquals(4, stack.size());
        assertFalse(stack.isFull());
    }

    @Test
    public void testPush() {
        final int lastSize = stack.size();
        final String elem = "X";

        stack.push(elem);
        assertEquals("[a - b - c - d > X]", stack.toString());
        assertEquals(lastSize + 1, stack.size());
        assertEquals(elem, stack.peak());

        Executable pushWhenAlreadyFull = () -> stack.push("X");
        assertThrows(IllegalStateException.class, pushWhenAlreadyFull);

        stack.clear();
        stack.push(elem);
        assertEquals("[X]", stack.toString());
        assertEquals(elem, stack.peak());
    }

    @Test
    public void testPeak() {
        final String lastTop = "d";
        final String newTop = "X";

        stack.push(newTop);
        assertEquals(newTop, stack.peak());

        stack.pop();
        assertEquals(lastTop, stack.peak());

        stack.clear();
        assertNull(stack.peak());
    }

    @Test
    public void testPop() {
        final String lastTop = stack.peak();
        final String newTop = "X";

        stack.push(newTop);
        assertEquals(newTop, stack.pop());
        assertEquals(lastTop, stack.peak());

        stack.clear();
        Executable popWhenEmpty = () -> stack.pop();
        assertThrows(IllegalStateException.class, popWhenEmpty);

        stack.push(newTop);
        assertEquals(newTop, stack.pop());
    }
}
