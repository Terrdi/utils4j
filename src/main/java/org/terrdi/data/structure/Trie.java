package org.terrdi.data.structure;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Terrdi
 * @since 1.8
 *
 * 前缀树
 * @link https://leetcode-cn.com/problems/implement-trie-prefix-tree/#/description
 */
public class Trie {
    /**
     * store words
     */
    private Map<Character, Trie> trie = null;

    private boolean isEnd;

    /**
     * parent trie
     */
    private Trie parent;

    /**
     * the character find by parent trie
     */
    private Character last;

    /** Initialize your data structure here. */
    public Trie() {
        trie = new ConcurrentHashMap<>();
    }

    /** Inserts a word into the trie. */
    public void insert(CharSequence word) {
        Trie cur = this;
        for (int i = 0;i < word.length();i++) {
            char ch = word.charAt(i);
            cur = cur.putNext(ch);
        }
        cur.end();
    }

    /** Returns if the word is in the trie. */
    public boolean search(CharSequence word) {
        try {
            Trie cur = this;
            for (int i = 0;i < word.length();i++) {
                cur = cur.next(word.charAt(i));
            }
            return cur.isEnd();
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     **/
    public boolean startsWith(CharSequence prefix) {
        try {
            Trie cur = this;
            for (int i = 0;i < prefix.length();i++) {
                cur = cur.next(prefix.charAt(i));
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * set the trie end
     */
    private void end() {
        this.isEnd = true;
    }

    /**
     * @return true if the prefix word exists
     */
    private boolean isEnd() {
        return this.isEnd;
    }

    private Trie putNext(Character ch) {
        trie.putIfAbsent(ch, new Trie());
        return trie.get(ch);
    }

    /**
     * search the child trie for ch
     * @param ch
     * @return the child trie for ch
     * @throws NullPointerException the ch doesn't exists in this trie
     */
    private Trie next(Character ch) {
        return Optional.of(trie.get(ch)).get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Trie trie = this;
        while (Objects.nonNull(trie.parent)) {
            sb.append(trie.last);
            trie = trie.parent;
        }
        return sb.reverse().toString();
    }
}
