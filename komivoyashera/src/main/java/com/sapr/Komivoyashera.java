package com.sapr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Komivoyashera {

    private static boolean visited[];
    private static LinkedList<Integer> path;
    private static int n;

    private static boolean hamilton(int curr, ArrayList<ArrayList<Integer>> matrix) {
        path.addLast(curr);
        if (path.size() == n) {
            if (matrix.get(path.getFirst()).get(path.getLast()) != 0) {
                return true;
            } else {
                path.removeLast();
                return false;
            }
        }
        visited[curr] = true;
        for (int next = 0; next < n; ++next) {
            if (matrix.get(curr).get(next) > 0 && !visited[next]) {
                if (hamilton(next, matrix)) {
                    return true;
                }
            }
        }
        visited[curr] = false;
        path.removeLast();
        return false;
    }

    public static List<String> komivoyashera (ArrayList<ArrayList<Integer>> matrix)
            throws NoSolutionFoundException {
        List<String> result = null;
        int mintLengthOfPath = Integer.MAX_VALUE;
        n = matrix.size();

        for (int i = 0; i < matrix.size(); i++) {
            visited = new boolean[matrix.size()];
            path = new LinkedList<>();

            if (hamilton(i, matrix)) {
                int weight = 0;
                int count = 0;
                while (count < matrix.size()-1) {
                    weight += matrix.get(path.get(count)).get(path.get(count + 1));
                    count++;
                }
                System.out.println("Вага: " + weight + " Шлях: " + path);
                if (weight < mintLengthOfPath) {
                    mintLengthOfPath = weight;
                    result = new ArrayList<>();
                    List<String> finalResult = result;
                    path.forEach(
                            o -> finalResult.add(o.toString())
                    );
                    result = finalResult;
                }
            }
        }
        if (result == null) {
            throw new NoSolutionFoundException("Hемає рішення");
        }
        return result;
    }
}
