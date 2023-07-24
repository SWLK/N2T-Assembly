import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String,Integer> symbolTable;

    // Construct symbol table with pre-defined symbols
    public SymbolTable() {
        this.symbolTable = new HashMap<>();
        this.symbolTable.put("SP", 0);
        this.symbolTable.put("LCL", 1);
        this.symbolTable.put("ARG", 2);
        this.symbolTable.put("THIS", 3);
        this.symbolTable.put("THAT", 4);
        this.symbolTable.put("SCREEN", 16384);
        this.symbolTable.put("KBD", 24576);
        for (int i = 0; i < 16; i++){
            this.symbolTable.put("R" + i, i);
        }
    }

    // Adds a new symbol and address to the symbol table.
    public void AddEntry(String symbol, int address) {
        this.symbolTable.put(symbol, address);
    }

    // Check if the symbol is already inside the table.
    public boolean contains(String symbol){
        return this.symbolTable.containsKey(symbol);
    }

    // Check symbol's address and return it
    public int getAddress(String symbol){
        return this.symbolTable.get(symbol);
    }

    public void printTable(){
        this.symbolTable.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }
}
