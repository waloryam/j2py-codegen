package main.java;
public class AssignStmt extends Stmt {
    private Expr target;
    private Expr value;

    public AssignStmt(Expr target, Expr value) {
        this.target = target;
        this.value = value;
    }

    @Override
    public String toCode(int indentLevel) {
        return indent(indentLevel) + target.toCode() + " = " + value.toCode();
    }
}
