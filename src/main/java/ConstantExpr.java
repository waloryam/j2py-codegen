package main.java;

public class ConstantExpr extends Expr {
    private Object value;

    public ConstantExpr(Object value) {
        this.value = value;
    }

    @Override
    public String toCode() {
        if (value instanceof String) {
            return "\"" + value + "\"";
        } else {
            return value.toString();
        }
    }
}
