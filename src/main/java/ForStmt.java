package main.java;

import java.util.List;

public class ForStmt extends Stmt {
    private Expr target;
    private Expr iter;
    private List<Stmt> body;

    public ForStmt(Expr target, Expr iter, List<Stmt> body) {
        this.target = target;
        this.iter = iter;
        this.body = body;
    }

    @Override
    public String toCode(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(indentLevel)).append("for ").append(target.toCode()).append(" in ").append(iter.toCode()).append(":\n");
        for (Stmt stmt : body)
            sb.append(stmt.toCode(indentLevel + 1)).append("\n");            
        return sb.toString().replaceAll("\n+$", "\n");
    }
}
