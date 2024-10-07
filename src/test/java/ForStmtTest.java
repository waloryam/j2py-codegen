package test.java;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import main.java.CallExpr;
import main.java.CompareExpr;
import main.java.ConstantExpr;
import main.java.Expr;
import main.java.ExprStmt;
import main.java.ForStmt;
import main.java.FunctionDef;
import main.java.IfStmt;
import main.java.ListExpr;
import main.java.NameExpr;
import main.java.ReturnStmt;
import main.java.Stmt;

public class ForStmtTest {

    @Test
    public void testForStmt1() {
        // Expression: x > 10
        Expr condition = new CompareExpr(
                new NameExpr("x"),
                ">",
                new ConstantExpr(10));

        // If body: print("x is greater than 10")
        Stmt ifBody = new ExprStmt(
                new CallExpr(
                        new NameExpr("print"),
                        Arrays.asList(new ConstantExpr("x is greater than 10"))));

        // Else body: print("x is 10 or less")
        Stmt elseBody = new ExprStmt(
                new CallExpr(
                        new NameExpr("print"),
                        Collections.singletonList(new ConstantExpr("x is 10 or less"))));

        // IfStmt
        Stmt ifStmt = new IfStmt(
                condition,
                Arrays.asList(
                        ifBody, ifBody),
                Collections.singletonList(elseBody));

        // Iterable: range(5)
        Expr iterable = new CallExpr(
                new NameExpr("range"),
                Collections.singletonList(new ConstantExpr(5)));

        // Create the function body
        Expr returnValue = new ListExpr(Collections.singletonList(new ConstantExpr("dual")));
        Stmt returnStmt = new ReturnStmt(returnValue);

        // Create the function definition
        FunctionDef sourceTablesFun = new FunctionDef(
                "source_tables",
                Collections.emptyList(), // No arguments
                Collections.singletonList(returnStmt));

        // ForStmt
        Stmt forStmt2 = new ForStmt(
                new NameExpr("i"),
                iterable,
                Arrays.asList(ifStmt, sourceTablesFun));

        // Generate code
        System.out.println("\n" + forStmt2.toCode(0));

        String expectedOutput = """
for i in range(5):
    if x > 10:
        print("x is greater than 10")
        print("x is greater than 10")
    else:
        print("x is 10 or less")

    def source_tables():
        return ["dual"]
""";
        assertEquals(expectedOutput, forStmt2.toCode(0));

    }

}
