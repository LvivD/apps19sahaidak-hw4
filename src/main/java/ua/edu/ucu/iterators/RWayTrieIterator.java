package ua.edu.ucu.iterators;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.utils.Queue;

import java.util.Iterator;
import java.util.Map;

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
        if (queue.peek() != null && ((RWayTrie.Node) queue.peek()).letter == 'H' && ((RWayTrie.Node) queue.peek()).nextNodes.isEmpty()) {
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
            for (Map.Entry nextMapElem: node.nextNodes.entrySet()) {
                RWayTrie.Node nextNode = (RWayTrie.Node) nextMapElem.getValue();
                queue.enqueue(nextNode);
                lettersQueue.enqueue(prevWord + nextNode.letter);
            }
        }
        while (node.weight == null);
        return prevWord;
    }

    public static Iterable<String> RWayTrieIterable(RWayTrie.Node node, String prefix) {
        return () -> new RWayTrieIterator(node, prefix);
    }

}
