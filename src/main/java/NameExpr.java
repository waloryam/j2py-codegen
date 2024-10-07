package main.java;

public class NameExpr extends Expr {
    private String id;

    public NameExpr(String id) {
        this.id = id;
    }

    @Override
    public String toCode() {
        return id;
    }
}
