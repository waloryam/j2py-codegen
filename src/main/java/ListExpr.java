package main.java;

import java.util.List;

public class ListExpr extends Expr {
    private List<Expr> elements;

    public ListExpr(List<Expr> elements) {
        this.elements = elements;
    }

    @Override
    public String toCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < elements.size(); i++) {
            sb.append(elements.get(i).toCode());
            if (i < elements.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
