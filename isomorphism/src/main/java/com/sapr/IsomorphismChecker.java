package com.sapr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsomorphismChecker {

    private static List<Node> nodeList;

    public static List<String> check(ArrayList<ArrayList<Integer>> firstMatrix,
                                ArrayList<ArrayList<Integer>> secondMatrix)
            throws NoSolutionFoundException {
        if (firstMatrix.equals(secondMatrix)) {
            throw new NoSolutionFoundException("Графи не ізоморфні!");
        } else if (firstMatrix.size() != secondMatrix.size())  {
            throw new NoSolutionFoundException("Графи не ізоморфні!");
        }
        List<Node> nodes = matrixToNodeList(secondMatrix);

        if(areIsomorphic(firstMatrix, secondMatrix, nodes, 0)) {
            List<String> result = new ArrayList<>();
            nodeList.forEach( o -> result.add(String.valueOf(o.getId())));
            return result;
        } else {
            throw new NoSolutionFoundException("Графи не ізоморфні!");
        }
    }

    private static boolean areIsomorphic(ArrayList<ArrayList<Integer>> firstMatrix,
                                         ArrayList<ArrayList<Integer>> secondMatrix,
                                         List<Node> nodes, int i) {
        if (i > secondMatrix.size() -1) {
            return false;
        } else if (i == 0) {
            boolean result = areIsomorphic(firstMatrix, secondMatrix, nodes, i + 1);
            if (result) {
                return true;
            }
        }
            for (int j = i + 1; j < secondMatrix.size(); j++) {
                int iId = nodes.get(i).getId();
                nodes.get(i).setId(nodes.get(j).getId());
                nodes.get(j).setId(iId);
                if (nodeListToMatrix(nodes).equals(firstMatrix)) {
                    nodeList = nodes;
                    return true;
                } else {
                    boolean result = areIsomorphic(firstMatrix, secondMatrix, nodes, i + 1);
                    if (result) {
                        return true;
                    }
                }
                nodes = matrixToNodeList(secondMatrix);
            }

        return false;
    }

    private static ArrayList<ArrayList<Integer>> nodeListToMatrix(List<Node> nodess) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        Node[] nodeArray = new Node[nodess.size()];
        nodess.toArray(nodeArray);
        Arrays.sort(nodeArray);
        List<Node> nodes = Arrays.asList(nodeArray);

        for (int i = 0; i < nodes.size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            Node node = nodes.get(i);

            for (int j = 0; j < nodes.size(); j++) {
                if (node.getNeighbours().contains(new Node(j))) {
                    row.add(1);
                } else {
                    row.add(0);
                }
            }
            matrix.add(row);
        }
        return matrix;
    }

    private static List<Node> matrixToNodeList(ArrayList<ArrayList<Integer>> secondMatrix) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < secondMatrix.size(); i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < secondMatrix.size(); i++) {
            Node current = nodes.get(i);
            ArrayList<Integer> relationships = secondMatrix.get(i);
            for (int j = 0; j < relationships.size(); j++) {
                int relationship = relationships.get(j);
                if (relationship == 1) {
                    current.addNeighbour(nodes.get(j));
                }
            }
        }
        return nodes;
    }
}
