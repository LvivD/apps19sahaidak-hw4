package ua.edu.ucu.iterators;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.utils.Queue;

import java.util.Iterator;

public class RWayTrieIterator implements Iterator<String>  {

    private Queue queue;
    private Queue lettersQueue;


    public RWayTrieIterator(RWayTrie.Node head, String prefix) {
        queue = new Queue();
        lettersQueue = new Queue();
        queue.enqueue(head);
        lettersQueue.enqueue(prefix);
    }

    private void step() {

    }

    @Override
    public boolean hasNext() {
        if (queue.peek() != null && ((RWayTrie.Node) queue.peek()).letter == 'H' && ((RWayTrie.Node) queue.peek()).nextNodesSize() == 0) {
            return false;
        }
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
            if (node == null) {
                return null;
            }
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

    public static Iterable<String> RWayTrieIterable(RWayTrie.Node node, String prefix) {
        return () -> new RWayTrieIterator(node, prefix);
    }

}
