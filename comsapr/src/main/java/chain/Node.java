package chain;

import java.util.Objects;

public class Node {
    private int id;
    private Node previous;

    public Node() {
        previous = null;
    }

    public Node(int id) {
        this.id = id;
        previous = null;
    }

    public Node(int id, Node previous) {
        this.id = id;
        this.previous = previous;
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

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
