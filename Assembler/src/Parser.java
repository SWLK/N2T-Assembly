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
            return "null";
        }
      return symbol;
    }

    public String dest(){
        int indexBegin, indexEnd;
        String command = "";

        // check for " = ", it is always the first part of the command line
        if ((indexEnd = line.indexOf("=")) != -1 ) {
            command = line.substring(0, indexEnd);
        } else {
            // if there is no "=", then the value is not stored
            command = "null";
        }
        return command;
    }

    public String comp(){

        String command = line;

        if (line.contains("=")) {
            command = command.substring(command.indexOf('=') + 1);
        }
        if (line.contains(";")) {
            command = command.substring(0, command.indexOf(';'));
        }
        return command;
    }

    public String jump(){
        int indexBegin, indexEnd;
        String command = "";

        // check for " ; ", it is always the last part of the command line
        if ((indexBegin = line.indexOf(";")) != -1 ) {
            command = line.substring(indexBegin + 1);
        } else {
            // if there is no ";", then the value is not stored
            command = "null";
        }
        return command;
    }
}
