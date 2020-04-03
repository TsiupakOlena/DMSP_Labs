package chain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        do {
            List<Integer> nodes = new ArrayList<>();
            Scanner scan = new Scanner(System.in);

            try {
                System.out.println( "Введіть початкову вершину: " );
                nodes.add(scan.nextInt());
                System.out.println( "Введіть кінцеву вершину: " );
                nodes.add(scan.nextInt());
            } catch (Exception exe) {
                System.out.println( " Помилка вводу! Введіть ціле число! Спробуйте ще раз! " );
                continue;
            }
            scan = new Scanner(System.in);
            System.out.println( "Введіть шлях до вхідного файлу: " );
            String file = scan.nextLine();
            ArrayList<List<String>> matrix;
            try {
                matrix = MatrixReader.read(file);
            } catch (FileNotFoundException e) {
                System.out.println( " Помилка читання файлу! Спробуйте ще раз! " );
                continue;
            }
            showMatrix(matrix);
            String outputFile = file.substring(0, file.lastIndexOf("\\") + 1) + "output.txt";
            try {
                MatrixReader.write(outputFile, Chain.increasingChain(matrix, nodes));
            } catch (NoSolutionFoundException e) {
                System.out.println( e.getMessage() );
            } catch (IOException ex) {
                System.out.println( " Помилка запису! " );
            }
            break;
        } while(true);
    }

    private static void showMatrix(ArrayList<List<String>> matrix) {
        System.out.println("-----------------------");
        for (List<String> line: matrix) {
            for (String num: line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}
