package ua.edu.ucu.iterators;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.utils.Queue;

import java.util.Iterator;

public class RWayTrieWeightIterator implements Iterator<String> {

    private Iterator<String> iterator;
    private int k;
    private String next;

    public RWayTrieWeightIterator(Trie tree, String prefix, int key) {
        iterator = tree.wordsWithPrefix(prefix).iterator();
        if (iterator.hasNext()) {
            next = iterator.next();
        }
        k = key - 1;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String next() {
        String toReturn = next;
        if (iterator.hasNext() && k >= 0) {
            String newNext;
            newNext = iterator.next();

            if (newNext.length() != next.length()) {
                k -= 1;
            }
            if (k >= 0) {
                next = newNext;
            }
            else {
                next = null;
            }
        }
        else {
            next = null;
        }
        return toReturn;
    }

    public static Iterable<String> RWayTrieWeightIterable(Trie tree, String prefix, int k) {
        return () -> new RWayTrieWeightIterator(tree, prefix, k);
    }
}
