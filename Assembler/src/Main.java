// Assembler for Hack machine language based on the NAND to Tetris book

import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // Read input file (XXX.asm)
        //String filePath = "C:\\Users\\Robert\\Desktop\\Learning CS\\From Nand to Tetris\\Untouched folder\\nand2tetris\\projects\\06\\add\\Add.asm";
        String filePath = "C:\\Users\\Robert\\Desktop\\Learning CS\\From Nand to Tetris\\Untouched folder\\nand2tetris\\projects\\06\\max\\Max.asm";


        // First pass, focus on the Labels
        int lineCounter = 0;
        String line = "";
        String symbol = "";
        int indexBegin;
        int indexEnd;
        final int STARTADDRESS = 16;
        SymbolTable symbolTable = new SymbolTable();

            try {
                // Read the lines one by one, separated by newlines \n
                // As a note, bufferedReader is faster than scanner for larger files.
                BufferedReader br = new BufferedReader(new FileReader(filePath));

                while ((line = br.readLine()) != null) { // line is null when we reach the end of file

                    // Check for comments before/after a line of code
                    line = line.split("//")[0].trim();
                    if(line.isEmpty()){
                        continue;
                    }

                    if ((indexBegin = line.indexOf('(')) != -1){ // searching for '(' as it represents a label

                        indexEnd = line.indexOf(')');
                        symbol = line.substring(indexBegin + 1,indexEnd);

//                        // Check if label it is already inside the table
//                        if(symbolTable.contains(symbol)){
//                            // Translate Label
//
//                        } else {
//                            // Add Label to symbol table, starting at 16
//                            symbolTable.AddEntry(symbol,  STARTADDRESS + lineCounter++);
//                        }

                        symbolTable.AddEntry(symbol,  STARTADDRESS + lineCounter++);

                    }
                    System.out.println(line);
                }
                symbolTable.printTable();




            } catch (IOException e) {
                // Handle the exception if the file reading fails.
                e.printStackTrace();
            }
        }
}
