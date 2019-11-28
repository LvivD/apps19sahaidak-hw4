package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.RWayTrieIterator;

import java.util.HashMap;

public class RWayTrie implements Trie{

    public static int R = 26;  //number of letters in English alphabet
    public final Node head;     // make head private
    private int size = 0;

    public RWayTrie() {
        head = new Node('H');
    }

    public class Node {


        public final char letter;
        public Object weight;
        public HashMap<Character, Node> nextNodes;
        public boolean last;

        public Node(char letter) {
            this.letter = letter;
            this.nextNodes = new HashMap<Character, Node>();
            this.weight = null;
            this.last = false;
        }

        public Node addNext(char letter) {
            if (!this.nextNodes.containsKey(letter)) {
                this.nextNodes.put(letter, new Node(letter));
            }
            return this.nextNodes.get(letter);
        }

//        public void addFromLetter(int index, String word, Object weight) {
//            Node node = this;
//            for (int i = index; i < word.length(); i++) {
//                node = node.addNext(word.charAt(i));
//            }
//            node.weight = weight;
//        }

//        public Node nextNodeContains(char letter) {
//            return this.nextNodes[getIntFromChar(letter)];
//        }
    }



    @Override
    public void add(Tuple t) {
        String word = t.term;
        Object weight = t.weight;
        Node iterNode = this.head;
        for (int i = 0; i < word.length(); i++) {
            iterNode = iterNode.addNext(word.charAt(i));
        }
        iterNode.last = true;
        iterNode.weight = weight;
        size += 1;
    }

    @Override
    public boolean contains(String word) {
        Node iterNode = this.head;
        for (int i = 0; i < word.length(); i++) {
            if (iterNode.nextNodes.containsKey(word.charAt(i))) {
                iterNode = iterNode.nextNodes.get(word.charAt(i));
            }
            else {
                return false;
            }
        }
        return iterNode.last;
    }

    @Override
    public boolean delete(String word) {
        if (! this.contains(word)) {
            return false;
        }
        Node iterNode = this.head;
        Node notToDeleteNode = this.head;
        int letterIndex = 0;
        int i;
        for (i = 0; i < word.length(); i++) {
            if (iterNode.nextNodes.containsKey(word.charAt(i))) {
                iterNode = iterNode.nextNodes.get(word.charAt(i));
            }
            else {
                System.out.println("No such words");
                return false;
            }
            if (iterNode.last && i !=word.length() - 1) {
                notToDeleteNode = iterNode;
                letterIndex = i;
            }
        }
        if (iterNode.nextNodes.isEmpty()) {
            notToDeleteNode.nextNodes.remove(word.charAt(letterIndex));
            size -= 1;
            return true;
        }
        iterNode.last = false;
        iterNode.weight = null;
        size -= 1;
        return true;

    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node iterNode = this.head;
        for (int i = 0; i < s.length(); i++) {
            if (iterNode.nextNodes.containsKey(s.charAt(i))) {
                iterNode = iterNode.nextNodes.get(s.charAt(i));
            }
            else {
                return null;
            }
        }
        return RWayTrieIterator.RWayTrieIterable(iterNode, s);
    }

    @Override
    public int size() {
        return size;
    }

}
