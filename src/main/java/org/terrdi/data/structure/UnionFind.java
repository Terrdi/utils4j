package org.terrdi.data.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 并查集
 * @author Terrdi
 * @since 1.8
 */
public class UnionFind<T> {
    /**
     * 并查集的一个节点
     * @param <T>
     */
    class UnionFindNode<T> {
        /**
         * 并查集的父节点
         * 当当前节点是根节点的时候等于自己
         */
        UnionFindNode<T> parent = this;

        /**
         * 当前节点存储的数据
         */
        final T data;

        public UnionFindNode(T data) {
            this.data = data;
        }
    }

    /**
     * 当前并查集集合的数量
     */
    private int count;

    /**
     * 存储并查集的结点
     */
    private Map<T, UnionFindNode<T>> union = new HashMap<>();

    public UnionFind(Iterable<T> collection) {
        for (T data : collection) {
            this.union.put(data, new UnionFindNode<>(data));
            this.count++;
        }
    }

    /**
     * 获取对应数据的根节点
     * @param data
     * @return
     */
    public T getParent(T data) {
        UnionFindNode<T> node = Objects.requireNonNull(this.union.get(data), data + " don't exist in the union find");
        UnionFindNode<T> tmp = node;
        while (tmp.parent != tmp) {
            tmp = tmp.parent;
        }
        node.parent = tmp;
        return tmp.data;
    }

    public int getCount() {
        return this.count;
    }

    public void union(T data1, T data2) {
        UnionFindNode<T> node1 = Objects.requireNonNull(this.union.get(data1), data1 + " don't exist in the union find"),
                         node2 = Objects.requireNonNull(this.union.get(data2), data2 + " don't exist in the union find");
        while (node1.parent != node1) {
            node1 = node1.parent;
        }
        while (node2.parent != node2) {
            UnionFindNode<T> tmp = node2;
            node2 = node2.parent;
            tmp.parent = node1;
        }
        if (node1 != node2) {
            this.count--;
            node2.parent = node1;
        }
    }
}
