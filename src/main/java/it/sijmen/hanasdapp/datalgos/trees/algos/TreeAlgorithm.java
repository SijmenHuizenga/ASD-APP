package it.sijmen.hanasdapp.datalgos.trees.algos;

import it.sijmen.hanasdapp.datalgos.trees.AbstractTree;

/**
 * Created by Sijmen on 6-3-2017.
 *
 * T = Tree Value Type
 * U = Return Type
 */
public interface TreeAlgorithm<T, U> {

    public U apply(AbstractTree<T> tree);

}