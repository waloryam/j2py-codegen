package main.java;

import java.util.List;

public class ImportFromStmt extends Stmt {
    private String module;
    private List<String> names;

    public ImportFromStmt(String module, List<String> names) {
        this.module = module;
        this.names = names;        
    }

    @Override
    public String toCode(int indentLevel) {
        StringBuilder sb = new StringBuilder(indent(indentLevel) + "from ");
     
        sb.append(module).append(" import ");
        for (int i = 0; i < names.size(); i++) {
            sb.append(names.get(i));
            if (i < names.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
