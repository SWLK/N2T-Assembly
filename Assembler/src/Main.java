// Assembler for Hack machine language based on the NAND to Tetris book

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {

        // Read input file (XXX.asm)
        String filePath = "C:\\Users\\Robert\\Desktop\\Learning CS\\From Nand to Tetris\\Untouched folder\\nand2tetris\\projects\\06\\add\\Add.asm";
        //String filePath = "C:\\Users\\Robert\\Desktop\\Learning CS\\From Nand to Tetris\\Untouched folder\\nand2tetris\\projects\\06\\max\\Max.asm";


        // First pass, focus on the Labels
        int lineCounter = 0;
        int variableCounter = 0;
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

                    // Check for comments before/after a line of code and removes leading and trailing white spaces.
                    line = line.split("//")[0].trim();
                    if(line.isEmpty()){
                        continue;
                    }

                    if ((indexBegin = line.indexOf('(')) != -1){ // searching for '(' as it represents a label

                        indexEnd = line.indexOf(')');
                        symbol = line.substring(indexBegin + 1,indexEnd);

                        // Check if label it is already inside the table
                        if(symbolTable.contains(symbol)){
                            System.out.printf("There is a mistake, a label can only be used once, must be unique. ");

                        } else {
                            // Add Label to symbol table, starting at 16. Remember to 1 one more because these point to the next instruction
                            symbolTable.AddEntry(symbol,  (lineCounter + 1));
                        }

                    }
                    // System.out.println(line);
                    lineCounter++;
                }

            } catch (IOException e) {
                // Handle the exception if the file reading fails.
                e.printStackTrace();
            }


        // Second pass, now focus on the variables and translation
        // Reset variables
        lineCounter = 0;
        variableCounter = 0;
        line = "";
        symbol = "";

        String translatedLine = "";
        int numberInLine = 0;
        String compCommand = "";
        String destCommand = "";
        String jumpCommand = "";

        try {
            // Read the lines one by one, separated by newlines \n
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) { // line is null when we reach the end of file


                // Check for comments before/after a line of code and removes leading and trailing white spaces.
                line = line.split("//")[0].trim();
                if(line.isEmpty()){
                    continue;
                }

                // Create parser object for each line
                Parser parsedLine = new Parser(line);

                // Check if it is an A instruction
                if (parsedLine.instructionType() == 'A'){
                    // Check if it is a number or symbol. Symbols don't start with numbers, so check only the first digit
                    if (Character.isDigit(line.charAt(1))) {
                        // Grab number
                        try {
                            numberInLine = Integer.parseInt(line.substring(1));
                        } catch (NumberFormatException e) {
                            System.out.println(line + "cannot be converted to an integer");
                        }
                        // Translate Number Directly
                        translatedLine = Integer.toBinaryString(numberInLine);

                    // It is a symbol
                    } else {
                        symbol = parsedLine.symbol();
                        // Check if symbol is already on the table
                        if(symbolTable.contains(symbol)){
                            // Translate symbol
                            translatedLine = Integer.toBinaryString(symbolTable.getAddress(symbol));
                        } else {
                            // Add symbol to symbol table
                            symbolTable.AddEntry(symbol, STARTADDRESS + variableCounter++);
                            // Then translate it
                            translatedLine = Integer.toBinaryString(symbolTable.getAddress(symbol));
                        }
                    }
                // Check if it is a L instruction
                } else if (parsedLine.instructionType() == 'L') {
                    // Get symbol/label
                    symbol = parsedLine.symbol();
                    translatedLine = Integer.toBinaryString(symbolTable.getAddress(symbol));


                } else {
                    // It is a C instruction

                    compCommand = parsedLine.comp();
                    //System.out.println(compCommand);
                    destCommand = parsedLine.dest();
                    //System.out.println(destCommand);
                    jumpCommand = parsedLine.jump();
                    //System.out.println(jumpCommand);

                    String compTranslated = Translator.getCompHashMap().get(compCommand);
                    String destTranslated = Translator.getDestHashMap().get(destCommand);
                    String jumpTranslated = Translator.getJumpHashMap().get(jumpCommand);

                    translatedLine = compTranslated + destTranslated + jumpTranslated;
                }
                System.out.println(line);
                System.out.println(translatedLine);
                lineCounter++;
            }
            // symbolTable.printTable();

        } catch (IOException e) {
            // Handle the exception if the file reading fails.
            e.printStackTrace();
        }


    }
}
