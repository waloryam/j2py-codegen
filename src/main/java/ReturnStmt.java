package main.java;

public class ReturnStmt extends Stmt {
    private Expr value;

    public ReturnStmt(Expr value) {
        this.value = value;
    }

    @Override
    public String toCode(int indentLevel) {
        return indent(indentLevel) + "return " + value.toCode();
    }
}
