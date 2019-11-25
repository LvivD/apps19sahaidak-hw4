package ua.edu.ucu.iterators;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.utils.Queue;

import java.util.Iterator;

public class RWayTrieWeightIterator implements Iterator<String> {

    private RWayTrieIterator iterator;
    private Queue queue;
    private Queue lettersQueue;


    public RWayTrieWeightIterator(Trie trie, String prefix, int k) {
        iterator = trie.wordsWithPrefix(prefix) ;
    }

    @Override
    public boolean hasNext() {
        return queue.peek() != null;
    }

    @Override
    public String next() {
        RWayTrie.Node node;
        String prevWord;
        if (queue.peek() == null) {
            return null;
        }
        do {
            node = (RWayTrie.Node) queue.dequeue();
            prevWord = (String) lettersQueue.dequeue();
            for (int i = 0; i < RWayTrie.R; i++) {
                if (node.nextNodes[i] != null) {
                    queue.enqueue(node.nextNodes[i]);
                    lettersQueue.enqueue(prevWord + node.nextNodes[i].letter);
                }
            }
        }
        while (node.weight == null);
        return prevWord;
    }

    public static Iterable<String> RWayTrieWeightIterable(Trie trie, String prefix, int k) {
        return () -> new RWayTrieWeightIterator(trie, prefix, k);
    }
}
