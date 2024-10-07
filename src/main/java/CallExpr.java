package main.java;

import java.util.List;

public class CallExpr extends Expr {
    private Expr func;
    private List<Expr> args;

    public CallExpr(Expr func, List<Expr> args) {
        this.func = func;
        this.args = args;
    }

    @Override
    public String toCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(func.toCode());
        sb.append("(");
        for (int i = 0; i < args.size(); i++) {
            sb.append(args.get(i).toCode());
            if (i < args.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
