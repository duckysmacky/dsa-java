import io.github.duckysmacky.dsa.collections.list.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTests {
    LinkedList<String> list;

    @BeforeEach
    public void initiateList() {
        list = new LinkedList<>();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
    }

    @Test
    public void testGeneralList() {
        assertEquals("[a -> b -> c]", list.toString());
        assertEquals(3, list.size());
    }

    @Test
    public void testAddStart() {
        list.addFirst("x");
        assertEquals("[x -> a -> b -> c]", list.toString());
    }

    @Test
    public void testAddEnd() {
        list.addFirst("x");
        assertEquals("[a -> b -> c -> x]", list.toString());
    }

    @Test
    public void testAddAt() {
        list.add(2, "x");
        assertEquals("[a -> b -> x -> c]", list.toString());
    }

    @Test
    public void testReverse() {
        list.reverse();
        assertEquals("[c -> b -> a]", list.toString());
    }
}
