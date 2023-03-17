import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @Test
    public void testPush() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        assertEquals(1, stack.count());
    }

    @Test
    public void testPull() {
        Stack<String> stack = new Stack<>();
        stack.push("Hola");
        assertEquals("Hola", stack.pull());
        assertEquals(0, stack.count());
    }

    @Test
    public void testPeek() {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(10);
        assertEquals(10, (int) stack.peek());
        assertEquals(2, stack.count());
    }

    @Test
    public void testCount() {
        Stack<String> stack = new Stack<>();
        stack.push("Hola");
        stack.push("Mundo");
        assertEquals(2, stack.count());
    }

    @Test
    public void testIsEmpty() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

}