package com.sapr;

public class Edge {
    int node1;
    int node2;

    public Edge() { }

    public Edge(int node1, int node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Edge(Edge anotherEdge) {
        this.node1 = anotherEdge.getNode1();
        this.node2 = anotherEdge.getNode2();
    }

    public int getNode1() {
        return node1;
    }

    public void setNode1(int node1) {
        this.node1 = node1;
    }

    public int getNode2() {
        return node2;
    }

    public void setNode2(int node2) {
        this.node2 = node2;
    }

    public void changeDirection() {
        int temp = node1;
        node1 = node2;
        node2 = temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;
        return (node1 == edge.node1 && node2 == edge.node2)
                || (node1 == edge.node2 && node2 == edge.node1);
    }

    public boolean equalsWithDirection(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;
        return (node1 == edge.node1 && node2 == edge.node2);
    }

    @Override
    public String toString() {
        return  node1 + "-" + node2;
    }
}
