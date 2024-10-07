package main.java;

public class CompareExpr extends Expr {
    private Expr left;
    private String op;
    private Expr right;

    public CompareExpr(Expr left, String op, Expr right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public String toCode() {
        return left.toCode() + " " + op + " " + right.toCode();
    }
}
