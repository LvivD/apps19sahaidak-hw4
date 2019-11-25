package ua.edu.ucu.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testInit() {
        Queue a = new Queue();
        assertEquals("", a.toString());
    }

    @Test
    public void testEnqueueEmpty() {
        Queue a = new Queue();
        a.enqueue(1);
        assertEquals("1", a.toString());
    }

    @Test
    public void testEnqueue() {
        Queue a = new Queue();
        a.enqueue(1);
        a.enqueue(2);
        assertEquals("1, 2", a.toString());
    }

    @Test
    public void testPeekEmpty() {
        Queue a = new Queue();
        Object b = a.peek();
        assertNull(b);
    }

    @Test
    public void testPeek() {
        Queue a = new Queue();
        a.enqueue(1);
        a.enqueue(3);
        a.enqueue(2);
        Object b = a.peek();
        assertEquals(1, b);
    }

    @Test
    public void testDequeueEmpty() {
        Queue a = new Queue();
        Object b = a.dequeue();
        assertNull(b);
        assertEquals("", a.toString());
    }

    @Test
    public void testDequeue() {
        Queue a = new Queue();
        a.enqueue(1);
        a.enqueue(3);
        a.enqueue(2);
        Object b = a.dequeue();
        assertEquals(1, b);
        assertEquals("3, 2", a.toString());
    }

    @Test
    public void testToStringEmpty() {
        Queue a = new Queue();
        assertEquals("", a.toString());
    }

    @Test
    public void testToString() {
        Queue a = new Queue();
        a.enqueue(1);
        a.enqueue(3);
        a.enqueue(2);
        assertEquals("1, 3, 2", a.toString());
    }

}