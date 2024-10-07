package main.java;

public class ExprStmt extends Stmt {
    private Expr expr;

    public ExprStmt(Expr expr) {
        this.expr = expr;
    }

    @Override
    public String toCode(int indentLevel) {
        return indent(indentLevel) + expr.toCode();
    }
}
