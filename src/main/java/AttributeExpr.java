package main.java;

public class AttributeExpr extends Expr {
    private Expr value;
    private String attr;

    public AttributeExpr(Expr value, String attr) {
        this.value = value;
        this.attr = attr;
    }

    @Override
    public String toCode() {
        return value.toCode() + "." + attr;
    }
}
