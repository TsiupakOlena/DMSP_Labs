package com.sapr;

import java.util.ArrayList;
import java.util.List;

public class Postman {

    public static List<String> postman(ArrayList<ArrayList<Integer>> matrix)
            throws NoSolutionFoundException {
        if(!isPairedGraph(matrix)) {
            throw new NoSolutionFoundException("Помилка! Граф непарний! Скоректуйте вхідний файл.");
        }
        ArrayList<Edge> cycles = new ArrayList<>();

        int initialNode = 0;
        int currentNode = initialNode;
        Edge currentEdge;
        List<Integer> currentRow = matrix.get(currentNode);
        for (int i = 0; i < currentRow.size(); i++) {
            int nextNode = currentRow.get(i);
            if (nextNode != 0) {
                currentEdge = new Edge(currentNode, i);
                if (!cycles.contains(currentEdge)) {
                    cycles.add(currentEdge);
                    currentNode = i;
                    break;
                }
            }
        }
        while (currentNode != initialNode) {
            currentRow = matrix.get(currentNode);
            for (int i = 0; i < currentRow.size(); i++) {
                int nextNode = currentRow.get(i);
                if (nextNode != 0) {
                    currentEdge = new Edge(currentNode, i);
                    if (!cycles.contains(currentEdge)) {
                        cycles.add(currentEdge);
                        currentNode = i;
                        break;
                    }
                }
            }
        }
        return convertToStringList(cycles);
    }

    private static List<String> convertToStringList(ArrayList<Edge> edgesList) {
        List<String> stringList = new ArrayList<>();
        edgesList.stream().forEach(o -> stringList.add(o.toString()));
        return stringList;
    }

    private static boolean isPairedGraph(ArrayList<ArrayList<Integer>> matrix) {
        int edgesCount;
        for(List<Integer> row: matrix) {
            edgesCount = 0;
            for(Integer value: row) {
                if (value != 0) {
                    edgesCount++;
                }
            }
            if (edgesCount % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
