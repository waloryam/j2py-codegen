package main.java;

import java.util.Arrays;
import java.util.Collections;

public class Main2 {
    public static void main(String[] args) {
        // Target: i
        Expr target = new NameExpr("i");

        // Iterable: range(1, 6)
        Expr iterable = new CallExpr(
            new NameExpr("range"),
            Arrays.asList(new ConstantExpr(1), new ConstantExpr(6))
        );

        // Condition: i % 2 == 0
        Expr condition = new CompareExpr(
            new OperatorExpr(
                new NameExpr("i"),
                "%",
                new ConstantExpr(2)
            ),
            "==",
            new ConstantExpr(0)
        );

        // If body: print(f"{i} is even")
        Stmt ifBody = new ExprStmt(
            new CallExpr(
                new NameExpr("print"),
                Collections.singletonList(
                    new CallExpr(
                        new AttributeExpr(
                            new ConstantExpr("{i} is even"),
                            "format"
                        ),
                        Collections.singletonList(new NameExpr("i"))
                    )
                )
            )
        );

        // Else body: print(f"{i} is odd")
        Stmt elseBody = new ExprStmt(
            new CallExpr(
                new NameExpr("print"),
                Collections.singletonList(
                    new CallExpr(
                        new AttributeExpr(
                            new ConstantExpr("{i} is odd"),
                            "format"
                        ),
                        Collections.singletonList(new NameExpr("i"))
                    )
                )
            )
        );

        // IfStmt inside the for loop
        Stmt ifStmt = new IfStmt(
            condition,
            Collections.singletonList(ifBody),
            Collections.singletonList(elseBody)
        );

        // ForStmt
        Stmt forStmt = new ForStmt(
            target,
            iterable,
            Collections.singletonList(ifStmt)
        );

        // Generate code
        String code = forStmt.toCode(0);
        System.out.println(code);
    }
}
