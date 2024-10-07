package main.java;

import java.util.List;

public class IfStmt extends Stmt {
    private Expr test;
    private List<Stmt> body;
    private List<Stmt> orelse; // Optional else clause

    public IfStmt(Expr test, List<Stmt> body, List<Stmt> orelse) {
        this.test = test;
        this.body = body;
        this.orelse = orelse;
    }

    @Override
    public String toCode(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        // 'if' statement
        sb.append(indent(indentLevel)).append("if ").append(test.toCode()).append(":\n");
        for (Stmt stmt : body) {
            sb.append(stmt.toCode(indentLevel + 1)).append("\n");
        }

        // 'else' clause, if present
        if (orelse != null && !orelse.isEmpty()) {
            sb.append(indent(indentLevel)).append("else:\n");
            for (Stmt stmt : orelse) {
                sb.append(stmt.toCode(indentLevel + 1)).append("\n");
            }
        }

        return sb.toString();
    }
}
