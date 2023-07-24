public class Parser {
    private String line;
    public Parser(String line) {
        this.line = line;
    }

    public char instructionType(){
        char firstChar = line.charAt(0);

        if ( firstChar == '@' ) {
            return 'A';
        } else if ( firstChar == '(' ) {
            return 'L';
        } else {
            return 'C';
        }
    }

    public String symbol(){
      int indexBegin, indexEnd;
      String symbol = "";

        if (this.instructionType() == 'L') {
            indexBegin = line.indexOf('(');
            indexEnd = line.indexOf(')');
            symbol = line.substring(indexBegin + 1,indexEnd);

        } else if ( this.instructionType() == 'A') {
            symbol = line.substring(1);
        } else {
            // Instruction is of type C, doenst have a symbol
        }
      return symbol;
    }

    public String dest(){

        return null;
    }
    public String comp(){

        return null;
    }
    public String jump(){

        return null;
    }
}
