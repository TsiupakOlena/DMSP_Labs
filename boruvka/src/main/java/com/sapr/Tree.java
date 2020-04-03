package com.sapr;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private static int count=0;
    private int id;
    private List<Integer> node;

    public Tree() {
        id = count++;
        node = new ArrayList<Integer>();
    }

    public Tree(Tree one, Tree two) {
        this();
        node.addAll(one.getNode());
        node.addAll(two.getNode());
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", node=" + node +
                '}';
    }

    public int getId() {
        return id;
    }

    public List<Integer> getNode() {
        return node;
    }

    public void setNode(List<Integer> node) {
        this.node = node;
    }
}
