package main.java;

import java.util.List;

public class DictExpr extends Expr {
    private List<Expr> keys;
    private List<Expr> values;

    public DictExpr(List<Expr> keys, List<Expr> values) {
        this.keys = keys;
        this.values = values;
    }

    @Override
    public String toCode() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < keys.size(); i++) {
            sb.append(keys.get(i).toCode()).append(": ").append(values.get(i).toCode());
            if (i < keys.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
