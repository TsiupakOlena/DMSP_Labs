package com.sapr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node  implements Comparable<Node> {
    private int id;
    private List<Node> neighbours;

    public Node() {
        neighbours = new ArrayList<>();
    }

    public Node(int id) {
        this.id = id;
        neighbours = new ArrayList<>();
    }

    public Node(int id, List<Node> neighbours) {
        this.id = id;
        this.neighbours = neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    @Override
    public int compareTo(Node n) {
        int id = n.getId();
        if (id == this.getId()) {
            return 0;
        }
        return id < this.getId() ? 1 : -1;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Node neighbour) {
        neighbours.add(neighbour);
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
