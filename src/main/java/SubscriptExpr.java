package main.java;

public class SubscriptExpr extends Expr {
    private Expr value;
    private Expr slice;

    public SubscriptExpr(Expr value, Expr slice) {
        this.value = value;
        this.slice = slice;
    }

    @Override
    public String toCode() {
        return value.toCode() + "[" + slice.toCode() + "]";
    }
}
