package main.java;

import java.util.List;

public class ImportStmt extends Stmt {
    private List<String> names;

    public ImportStmt(List<String> names) {
        this.names = names;
    }

    @Override
    public String toCode(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(indentLevel)).append("import ");
        
        for (int i = 0; i < names.size(); i++) {
            sb.append(names.get(i));
            if (i < names.size() - 1) {
                sb.append(", ");
            }
        }
        
        return sb.toString();
    }
}
