package com.sapr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
            ArrayList<ArrayList<Integer>> matrix;
            try {
                matrix = MatrixReader.read(file);
            } catch (FileNotFoundException e) {
                System.out.println( " Помилка читання файлу! Спробуйте ще раз! " );
                continue;
            }
            showMatrix(matrix);
            String outputFile = file.substring(0, file.lastIndexOf("\\") + 1) + "output.txt";
            try {
                MatrixReader.write(outputFile, Komivoyashera.komivoyashera(matrix));
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
        for (ArrayList<Integer> line: matrix) {
            for (Integer num: line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}
