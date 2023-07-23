// Assembler for Hack machine language based on the NAND to Tetris book

import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // Read input file (XXX.asm)
        String filePath = "C:\\Users\\Robert\\Desktop\\Learning CS\\From Nand to Tetris\\Untouched folder\\nand2tetris\\projects\\06\\add\\Add.asm";
        StringBuilder sb = new StringBuilder();

            try {
                // Read the lines one by one, separated by newlines \n
                // As a note, bufferedReader is faster than scanner for larger files.
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                // Handle the exception if the file reading fails.
                e.printStackTrace();
            }
        }
}
