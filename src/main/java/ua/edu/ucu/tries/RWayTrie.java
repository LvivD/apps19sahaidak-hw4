package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.RWayTrieIterator;

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
        public Node[] nextNodes;
        public boolean last;
        
        public Node(char letter) {
            this.letter = letter;
            this.nextNodes = new Node[R];
            for (int i = 0; i < R; i++) {
                this.nextNodes[i] = null;
            }
            this.weight = null;
        }

        public Node addNext(char letter) {
            Node newNode = new Node(letter);
            this.nextNodes[getIntFromChar(letter)] = newNode;
            return newNode;
        }

        public void addFromLetter(int index, String word, Object weight) {
            Node node = this;
            for (int i = index; i < word.length(); i++) {
                node = node.addNext(word.charAt(i));
            }
            node.weight = weight;

        }

        public Node nextNodeContains(char letter) {
            return this.nextNodes[getIntFromChar(letter)];
        }

        public int nextNodesSize() {
            int numberOfNodes = 0;
            for (int i = 0; i < R; i++) {
                if (this.nextNodes[i] != null) {
                    numberOfNodes += 1;
                }
            }
            return numberOfNodes;
        }
    }



    @Override
    public void add(Tuple t) {
        String word = t.term;
        Object weight = t.weight;
        Node thisNode = this.head;
        Node nextNode = this.head.nextNodeContains(word.charAt(0));
        int index = 0;
        while (nextNode != null && index < word.length() - 1) {
            thisNode = nextNode;
            index += 1;
            nextNode = thisNode.nextNodeContains(word.charAt(index));
        }
//        System.out.println(index);
        if (index == word.length() - 1) {
//            System.out.println(index + " " + (word.length() - 1));
//            System.out.println(thisNode.letter + " " + thisNode.weight);
//            System.out.println(nextNode);
            if (nextNode != null && nextNode.weight != null) {
                throw new RuntimeException("Two same words!");
            }
            if (nextNode != null) {
                nextNode.weight = weight;
            }
            else {
                thisNode.addFromLetter(index, word, weight);
            }
        }
        else if (index < word.length() - 1) {
//            System.out.println(index + " " + word);
            thisNode.addFromLetter(index, word, weight);
        }
        else {
            throw new RuntimeException("index > word len");
        }
        size += 1;
    }

    @Override
    public boolean contains(String word) {
        Node node = this.head;
//        System.out.println(node.letter);
        for (int i = 0; i < word.length(); i++) {

            node = node.nextNodeContains(word.charAt(i));

            if (node == null) {
                return false;
            }
//            System.out.println(node.letter);
        }
        return node.weight != null;
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
        for (i = 0; i < word.length() - 1; i++) {
//            System.out.println(i + " i");
            iterNode = iterNode.nextNodeContains(word.charAt(i));
            if (iterNode.nextNodesSize() > 1 || iterNode.weight != null) {
//                System.out.println(letterIndex + " letter index");
                notToDeleteNode = iterNode;
                letterIndex = i;
            }
        }
        iterNode = iterNode.nextNodeContains(word.charAt(i));
//        System.out.println(iterNode.letter);
//        System.out.println(iterNode.weight);
        if (iterNode.nextNodesSize() > 0) {
//            System.out.println("rem weight");
//            System.out.println(iterNode.letter);
//            System.out.println(iterNode.weight);
            iterNode.weight = null;
            size -= 1;
            return true;
        }
//        System.out.println("del let");
//        System.out.println(notToDeleteNode.nextNodeContains(word.charAt(letterIndex)).letter + " letter to rem");
        notToDeleteNode.nextNodes[getIntFromChar(word.charAt(letterIndex))] = null;
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
        Node nextNode;
        for (int i = 0; i < s.length(); i++) {
//            System.out.println(i + " " + s.charAt(i) + " " + getIntFromChar(s.charAt(i)));
//            System.out.println(this.contains("abcd"));
            nextNode = iterNode.nextNodes[getIntFromChar(s.charAt(i))];
//            System.out.println(nextNode);
            if (nextNode == null) {
                return null;
            }
            iterNode = nextNode;
        }
//        System.out.println("get to the end");
//        System.out.println(iterNode.letter + " "+ s);
        return RWayTrieIterator.RWayTrieIterable(iterNode, s);
    }

    @Override
    public int size() {
        return size;
    }

    private int getIntFromChar(char letter) {
        return (int) letter - (int) 'a';
    }

}
