package chain;

import java.util.*;

public class Chain {

    public static List<String> increasingChain(ArrayList<List<String>> matrix, List<Integer> nodes)
            throws NoSolutionFoundException {

        ArrayList<Node> allNodes = initialize(matrix.size());
        Queue<Node> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();
        int currentNode = nodes.get(0);
        int lastNode = nodes.get(1);
        Node node = new Node(currentNode);
        allNodes.remove(currentNode);

        while (true) {
            if (currentNode == lastNode) {
                while (node != null) {
                    Node tempNode = node.getPrevious();
                    if (tempNode == null) {
                        Collections.reverse(result);
                        return result;
                    }
                    String s = tempNode.getId() + "-" +  node.getId();
                    result.add(s);
                    node = tempNode;
                }
                Collections.reverse(result);
                return result;

            } else if (allNodes.size() == 0) {
                throw new NoSolutionFoundException("Помилка! Рішення Немає!");
            }

            List<String> row = matrix.get(currentNode);
            for (int i = 0; i < row.size(); i++) {
                String edge = row.get(i);
                if (edge.contains("I")) {
                    Node outNode = new Node(i, node);
                    if (allNodes.contains(outNode)) {
                        queue.add(outNode);
                        allNodes.remove(outNode);
                    }
                }
            }

            for (int i = 0; i < matrix.size(); i++) {
                String edge = matrix.get(i).get(currentNode);
                if (edge.contains("R")) {
                    Node outNode = new Node(i, node);
                    if (allNodes.contains(outNode)) {
                        queue.add(outNode);
                        allNodes.remove(outNode);
                    }
                }
            }

            if (queue.isEmpty()) {
                throw new NoSolutionFoundException("Рішення не існує!");
            }
            node = queue.poll();
            currentNode = node.getId();
        }
    }

    static ArrayList<Node> initialize(int count) {
        ArrayList<Node> list = new ArrayList<>();
        for (int i =0; i < count; i++) {
            list.add(new Node(i));
        }
        return list;
    }
}
