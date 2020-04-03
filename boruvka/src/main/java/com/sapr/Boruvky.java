package com.sapr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Boruvky {

    public enum Type {
        MIN, MAX
    }

    public static List<String> boruvky(ArrayList<ArrayList<Integer>> matrix, Type type) {
        int size = matrix.size();
        ArrayList<Tree> allTrees = initialize(matrix.size());
        ArrayList<Tree> currentIterationTrees = new ArrayList<>(allTrees);
        ArrayList<String> edges = new ArrayList<>();
        Set<Integer> alreadyUsed = new HashSet<>();

        while(edges.size() < size - 1) {
            for (Tree tree: currentIterationTrees) {
                if (!alreadyUsed.contains(tree.getId())) {
                    int minMax = type.equals(Type.MIN)? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    int nodeI = -1;
                    int nodeToCompareI = -1;
                    Tree toConnect = null;

                    for (Integer node: tree.getNode()) {
                        for (Tree treeToCompare: currentIterationTrees) {
                            if (treeToCompare.getId() != tree.getId()) {
                                for (Integer nodeToCompare: treeToCompare.getNode()) {
                                    int neighbour = matrix.get(node).get(nodeToCompare);
                                    if (type.equals(Type.MIN) && (neighbour > 0 && neighbour < minMax)) {
                                        minMax = neighbour;
                                        nodeI = node;
                                        nodeToCompareI = nodeToCompare;
                                        toConnect = treeToCompare;
                                    } else if (type.equals(Type.MAX) && (neighbour > 0 && neighbour > minMax)) {
                                        minMax = neighbour;
                                        nodeI = node;
                                        nodeToCompareI = nodeToCompare;
                                        toConnect = treeToCompare;
                                    }
                                }
                            }
                        }
                    }
                    allTrees.add(new Tree(tree, toConnect));
                    allTrees.remove(tree);
                    allTrees.remove(toConnect);
                    alreadyUsed.add(tree.getId());
                    alreadyUsed.add(toConnect.getId());
                    edges.add(nodeI + "-" + nodeToCompareI);
                }
            }
            currentIterationTrees = new ArrayList<>(allTrees);
            currentIterationTrees = mergeTrees(currentIterationTrees, allTrees);
        }
        return edges;
    }

    static ArrayList<Tree> mergeTrees(ArrayList<Tree> currentIterationTrees, ArrayList<Tree> allTrees) {
        for (int i = 0; i < currentIterationTrees.size()-1; i++) {
            for (int j =i+1; j < currentIterationTrees.size(); j++) {
                Tree tree = currentIterationTrees.get(i);
                Tree toCompare = currentIterationTrees.get(j);
                for (Integer node: tree.getNode()) {
                    if (toCompare.getNode().contains(node)) {
                        allTrees.add(new Tree(tree, toCompare));
                        allTrees.remove(tree);
                        allTrees.remove(toCompare);
                        break;
                    }
                }
            }
        }
        return new ArrayList<>(allTrees);
    }

    static ArrayList<Tree> initialize(int count) {
        ArrayList<Tree> list = new ArrayList<>();

        for (int i =0; i<count; i++) {
            ArrayList<Integer> value = new ArrayList<>();
            value.add(i);

            Tree tree = new Tree();
            tree.setNode(value);
            list.add(tree);
        }
        return list;
    }
}
