import io.github.duckysmacky.dsa.collections.list.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTests {
    ArrayList<String> list;

    @BeforeEach
    void initializeList() {
        list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
    }

    @Test
    void testInitialization() {
        assertEquals(4, list.size());
        assertFalse(list.isEmpty());
        assertEquals(ArrayList.of("a", "b", "c", "d"), list);
    }

    @Test
    void testClear() {
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertEquals(new ArrayList<>(), list);
    }

    @Test
    void testAdd() {
        list.add("x");
        assertEquals(ArrayList.of("a", "b", "c", "d", "x"), list);
        assertEquals(5, list.size());

        list.add("y");
        assertEquals(ArrayList.of("a", "b", "c", "d", "x", "y"), list);
        assertEquals(6, list.size());
    }

    @Test
    void testAddAtIndex() {
        list.add(2, "x");
        assertEquals(ArrayList.of("a", "b", "x", "c", "d"), list);
        assertEquals(5, list.size());

        list.add(0, "y");
        assertEquals(ArrayList.of("y", "a", "b", "x", "c", "d"), list);
        assertEquals(6, list.size());

        list.add(3, "z");
        assertEquals(ArrayList.of("y", "a", "b", "z", "x", "c", "d"), list);
        assertEquals(7, list.size());
    }

    @Test
    void testRemove() {
        String removed = list.remove();
        assertEquals("d", removed);
        assertEquals(ArrayList.of("a", "b", "c"), list);
        assertEquals(3, list.size());

        removed = list.remove();
        assertEquals("c", removed);
        assertEquals(ArrayList.of("a", "b"), list);
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveAtIndex() {
        String removed = list.remove(1);
        assertEquals("b", removed);
        assertEquals(ArrayList.of("a", "c", "d"), list);
        assertEquals(3, list.size());

        removed = list.remove(3);
        assertEquals("d", removed);
        assertEquals(ArrayList.of("a", "c"), list);
        assertEquals(2, list.size());

        removed = list.remove(0);
        assertEquals("a", removed);
        assertEquals(ArrayList.of("c"), list);
        assertEquals(1, list.size());
    }

    @Test
    void testGrow() {
        list = new ArrayList<>(2);
        list.add("x");
        list.add("y");
        list.add("z");
    }
}
