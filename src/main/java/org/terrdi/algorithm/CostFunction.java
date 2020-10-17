package org.terrdi.algorithm;

import org.terrdi.util.MathUtil;

import java.util.Comparator;

/**
 * 损失函数
 */
@FunctionalInterface
public interface CostFunction<E, T extends Comparable<T>> {
    T cost(E cur, E dest);

    default Comparator<E> getComparator(final E dest) {
//        return Comparator.comparing(p -> this.cost(p, dest));
        return (v1, v2) -> MathUtil.toComparableResult(this.cost(v1, dest).compareTo(this.cost(v2, dest)));
    }
}