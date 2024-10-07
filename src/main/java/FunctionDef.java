package main.java;

import java.util.List;

public class FunctionDef extends Stmt {
    private String name;
    private List<Expr> args;
    private List<Stmt> body;

    public FunctionDef(String name, List<Expr> args, List<Stmt> body) {
        this.name = name;
        this.args = args;
        this.body = body;
    }

    @Override
    public String toCode(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(indentLevel)).append("def ").append(name).append("(");
        
        // Add arguments if any
        for (int i = 0; i < args.size(); i++) {
            sb.append(args.get(i).toCode());
            if (i < args.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("):\n");

        // Add function body
        for (Stmt stmt : body) {
            sb.append(stmt.toCode(indentLevel + 1)).append("\n");
        }

        return sb.toString();
    }

    
}
