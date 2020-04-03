package com.sapr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixReader {
    public static List<ArrayList<ArrayList<Integer>>> read(String pathname) throws FileNotFoundException {
        List<ArrayList<ArrayList<Integer>>> a = new ArrayList<>();
        Scanner input = new Scanner(new File(pathname));
        for (int i = 0; i < 2; i++) {
            a.add(readMatrix(input));
        }
        return a;
    }

    private static ArrayList<ArrayList<Integer>> readMatrix(Scanner input) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        while(input.hasNextLine()) {
            Scanner colReader = new Scanner(input.nextLine());
            if (!colReader.hasNextInt()) {
                return a;
            }
            ArrayList<Integer> col = new ArrayList<>();
            while(colReader.hasNextInt()) {
                col.add(colReader.nextInt());
            }
            a.add(col);
        }
        return a;
    }

    public static void write(String pathname, List<String> edges) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(pathname));
        outputWriter.write("Графи ізоморфні. Перенумерування вершин:");
        outputWriter.newLine();
        int i= 0;
        for (String edge: edges) {
            outputWriter.write(edge + " -> " + i);
            outputWriter.newLine();
            i++;
        }
        outputWriter.flush();
        outputWriter.close();
    }
}
