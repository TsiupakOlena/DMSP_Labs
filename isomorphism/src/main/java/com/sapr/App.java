package com.sapr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println( "Введіть шлях до вхідного файлу: " );
            String file = scan.nextLine();
            List<ArrayList<ArrayList<Integer>>> matrix;
            try {
                matrix = MatrixReader.read(file);
            } catch (FileNotFoundException e) {
                System.out.println( " Помилка читання файлу! Спробуйте ще раз! " );
                continue;
            }
            for (int i = 0; i < 2; i++ ) {
                showMatrix(matrix.get(i));
            }
            String outputFile = file.substring(0, file.lastIndexOf("\\") + 1) + "output.txt";
            try {
                MatrixReader.write(outputFile, IsomorphismChecker.check(matrix.get(0), matrix.get(1)));
            } catch (NoSolutionFoundException e) {
                System.out.println( e.getMessage() );
            } catch (IOException ex) {
                System.out.println( " Помилка запису! " );
            }
            break;
        } while(true);
    }

    private static void showMatrix(ArrayList<ArrayList<Integer>> matrix) {
        System.out.println("-----------------------");
        for (List<Integer> line: matrix) {
            for (Integer num: line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}
