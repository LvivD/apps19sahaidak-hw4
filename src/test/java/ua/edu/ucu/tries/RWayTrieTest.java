package ua.edu.ucu.tries;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RWayTrieTest {

    private RWayTrie tree;

    @Before
    public void setUp() throws Exception {
        tree = new RWayTrie();
    }

    @Test
    public void testAddFirst() {
        Tuple word = new Tuple("word", 10);
        tree.add(word);
        assertTrue(tree.contains(word.term));
    }

    @Test
    public void testAddWords() {
        tree.add(new Tuple("abc", 3));
        tree.add(new Tuple("abce", 4));
        tree.add(new Tuple("abcd", 4));
        tree.add(new Tuple("abcde", 5));
        tree.add(new Tuple("abcdef", 6));
        assertTrue(tree.contains("abc"));
    }

    @Test
    public void testAddMoreWords() {
        tree.add(new Tuple("word", 10));
        tree.add(new Tuple("array", 1));
        assertTrue(tree.contains("array"));
        assertTrue(tree.contains("word"));
    }

    @Test
    public void testAddWordsWithSamePrefixLongerFirst() {
        tree.add(new Tuple("mapping", 10));
        assertTrue(tree.contains("mapping"));
        tree.add(new Tuple("map", 1));
        assertTrue(tree.contains("map"));
        assertTrue(tree.contains("mapping"));
    }

    @Test
    public void testAddWordsWithSamePrefixShorterFirst() {
        tree.add(new Tuple("map", 10));
        tree.add(new Tuple("mapping", 1));
        assertTrue(tree.contains("map"));
        assertTrue(tree.contains("mapping"));
    }

    @Test
    public void testContainsEmpty() {
        assertFalse(tree.contains("word"));
    }

    @Test
    public void testContainsOneWord() {
        tree.add(new Tuple("word", 12));
        assertTrue(tree.contains("word"));
    }

    @Test
    public void testDeleteEmpty() {
        assertFalse(tree.delete("word"));
    }

    @Test
    public void testDeleteOneWord() {
        tree.add(new Tuple("word", 10));
        assertTrue(tree.delete("word"));
        assertFalse(tree.contains("word"));
    }

    @Test
    public void testDeleteMoreWords() {
        tree.add(new Tuple("word", 10));
        tree.add(new Tuple("newword", 10));
        assertTrue(tree.delete("word"));
        assertFalse(tree.contains("word"));
        assertTrue(tree.delete("newword"));
        assertFalse(tree.contains("newword"));
    }

    @Test
    public void testDeleteWordsWithSamePrefixShorter() {
        tree.add(new Tuple("map", 10));
        tree.add(new Tuple("mapping", 1));
        assertTrue(tree.delete("map"));
        assertFalse(tree.contains("map"));
        assertTrue(tree.contains("mapping"));
    }

    @Test
    public void testDeleteWordsWithSamePrefixLonger() {
        tree.add(new Tuple("map", 10));
        tree.add(new Tuple("mapping", 1));
        assertTrue(tree.delete("mapping"));
        assertFalse(tree.contains("mapping"));
        assertTrue(tree.contains("map"));
    }

    @Test
    public void testWords() {
    }

    @Test
    public void testWordsWithPrefix() {
    }

    @Test
    public void testSize() {
        assertEquals(0, tree.size());
    }
}