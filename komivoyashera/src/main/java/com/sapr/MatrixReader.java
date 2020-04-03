package com.sapr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixReader {
    public static ArrayList<ArrayList<Integer>> read(String pathname) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        Scanner input = new Scanner(new File(pathname));
        while(input.hasNextLine())
        {
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList col = new ArrayList();
            while(colReader.hasNextInt())
            {
                col.add(colReader.nextInt());
            }
            a.add(col);
        }
        return a;
    }
    public static void write(String pathname, List<String> edges) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(pathname));
        outputWriter.write("Шлях:");
        outputWriter.newLine();
        for (int i = 0; i < edges.size(); i++) {
            outputWriter.write(edges.get(i));
            if (i != edges.size() - 1) {
                outputWriter.write(" - ");
            }
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}
