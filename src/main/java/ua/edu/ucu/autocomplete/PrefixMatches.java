package ua.edu.ucu.autocomplete;

import ua.edu.ucu.iterators.RWayTrieWeightIterator;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int numberOfWordsAdded = 0;
        for (String word: strings) {
            String[] words = word.split(" ");
            for (String singleWord: words) {
                trie.add(new Tuple(singleWord, singleWord.length()));
                numberOfWordsAdded += 1;
            }
        }
        return numberOfWordsAdded;
    }

    public boolean contains(String word) {
        return this.trie.contains(word);
    }

    public boolean delete(String word) {
        return this.trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return this.trie.wordsWithPrefix(pref);
        }
        return null;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() >= 2 && k > 0) {
            return RWayTrieWeightIterator.RWayTrieWeightIterable(this.trie, pref, k);
//            return null;
        }
        return null;
    }

    public int size() {
        return this.trie.size();
    }
}
