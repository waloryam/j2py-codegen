package main.java;

public abstract class Stmt {
    public abstract String toCode(int indentLevel);

    public static String indent(int indentLevel) {
        return "    ".repeat(indentLevel); // 4 spaces per indent level
    }

}
