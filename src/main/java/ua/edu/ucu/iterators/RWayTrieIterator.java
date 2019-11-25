package ua.edu.ucu.iterators;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.utils.Queue;

import java.util.Iterator;

public class RWayTrieIterator implements Iterator<String>  {

    private Queue queue;
    private Queue lettersQueue;


    public RWayTrieIterator(RWayTrie.Node head, String prefix) {
//        System.out.println("initialised");
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
//        return queue.peek() != null || ((RWayTrie.Node) queue.peek()).nextNodesSize() > 0;
    }

    @Override
    public String next() {
        RWayTrie.Node node;
        String prevWord;
//        System.out.println(lettersQueue.toString() + " q before");
        if (queue.peek() == null) {
//            System.out.println("get null");
            return null;
        }
        do {
//            System.out.println(((RWayTrie.Node) queue.peek()).letter + " check letter");
            node = (RWayTrie.Node) queue.dequeue();
            if (node == null) {
                return null;
            }
            prevWord = (String) lettersQueue.dequeue();
            for (int i = 0; i < RWayTrie.R; i++) {
                if (node.nextNodes[i] != null) {
//                    System.out.println((char) (i + (int) 'a') + " great letters");
                    queue.enqueue(node.nextNodes[i]);
                    lettersQueue.enqueue(prevWord + node.nextNodes[i].letter);
                }
            }
//            System.out.println(lettersQueue.toString() + " q after letter");
        }
        while (node.weight == null);
//        System.out.println(node.weight + " node weight");
//        System.out.println(prevWord + " prev word");
//        System.out.println(prevWord);
//        System.out.println(lettersQueue.toString() + " q end");
//        System.out.println();
        return prevWord;
    }

    public static Iterable<String> RWayTrieIterable(RWayTrie.Node node, String prefix) {
        return () -> new RWayTrieIterator(node, prefix);
    }

}
