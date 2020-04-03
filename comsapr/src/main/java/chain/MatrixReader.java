package chain;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MatrixReader {
    public static ArrayList<List<String>> read(String pathname) throws FileNotFoundException {
        ArrayList<List<String>> a = new ArrayList<>();
        Scanner input = new Scanner(new File(pathname));
        while(input.hasNextLine()) {
            String row = input.nextLine();
            List<String> outputRow = Arrays.asList(row.split(" "));
            a.add(outputRow);
        }
        return a;
    }

    public static void write(String pathname, List<String> edges) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(pathname));
        outputWriter.write("Ребра:");
        outputWriter.newLine();
        for (String edge: edges) {
            outputWriter.write(edge);
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}
