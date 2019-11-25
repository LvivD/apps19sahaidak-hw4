package ua.edu.ucu.iterators;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;

import static org.junit.Assert.*;

public class RWayTrieIteratorTest {

    private RWayTrieIterator iter;
    private RWayTrie tree;

    @Before
    public void setUp() throws Exception {
        tree = new RWayTrie();
        iter = new RWayTrieIterator(tree.head, "");
    }

    @Test
    public void hasNextOneElemTrue() {
        tree.add(new Tuple("word", 1));
        assertTrue(iter.hasNext());
    }

    @Test
    public void hasNextEmpty() {
        assertFalse(iter.hasNext());
    }

    @Test
    public void nextEmpty() {
        assertNull(iter.next());
    }
}