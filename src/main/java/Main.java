package main.java;

import java.util.Arrays;
import java.util.Collections;

public class Main {
        public static void main(String[] args) {

                Stmt importStmt = new ImportStmt(Arrays.asList("math", "random", "os"));
                System.out.println(importStmt.toCode(0));

                Stmt importMath = new ImportFromStmt(
                                "math",
                                Arrays.asList("sin", "cos", "tan"));
                System.out.println("\n" + importMath.toCode(0));

                Expr df = new NameExpr("df");
                Expr dfFilter = new AttributeExpr(df, "filter");
                Expr dfAge = new AttributeExpr(df, "Age");
                Expr const30 = new ConstantExpr(30);
                Expr compareExpr = new CompareExpr(dfAge, ">", const30);
                Expr filterCall = new CallExpr(dfFilter, Collections.singletonList(compareExpr));
                Expr showAttr = new AttributeExpr(filterCall, "show");
                Expr showCall = new CallExpr(showAttr, Collections.emptyList());
                String code = showCall.toCode();
                System.out.println(code);

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

                Stmt ifStmt2 = new IfStmt(
                                condition,
                                Arrays.asList(
                                                ifBody, ifBody),
                                Arrays.asList(elseBody, ifStmt));

                // Generate code
                System.out.println("\n" + ifStmt2.toCode(0));

                // Iterable: range(5)
                Expr iterable = new CallExpr(
                                new NameExpr("range"),
                                Collections.singletonList(new ConstantExpr(5)));

                // ForStmt
                Stmt forStmt = new ForStmt(
                                new NameExpr("i"),
                                iterable,
                                Collections.singletonList(ifStmt));

                // Generate code
                System.out.println("\n" + forStmt.toCode(0));

                // Create the function body
                Expr returnValue = new ListExpr(Collections.singletonList(new ConstantExpr("dual")));
                Stmt returnStmt = new ReturnStmt(returnValue);

                // Create the function definition
                FunctionDef sourceTablesFun = new FunctionDef(
                                "source_tables",
                                Collections.emptyList(), // No arguments
                                Collections.singletonList(returnStmt));

                // Generate and print the code
                System.out.println(sourceTablesFun.toCode(0));

                 // ForStmt
                 Stmt forStmt2 = new ForStmt(
                        new NameExpr("i"),
                        iterable,
                        Arrays.asList(ifStmt, sourceTablesFun));

        // Generate code
        System.out.println("\n" + forStmt2.toCode(0));


        FunctionDef transformSt1 = new FunctionDef(
                "transform_st1",
                Collections.singletonList(new NameExpr("source_dfs")),
                Arrays.asList(
                    // dual_df = source_dfs["dual"]
                    new AssignStmt(
                        new NameExpr("dual_df"),
                        new CallExpr(
                            new NameExpr("source_dfs"),
                            Collections.singletonList(new ConstantExpr("dual"))
                        )
                    ),
                    
                    // transformation_df = dual_df.alias('dual').select(lit(1).alias('id'))
                    new AssignStmt(
                        new NameExpr("transformation_df"),
                        new CallExpr(
                            new AttributeExpr(
                                new CallExpr(
                                    new AttributeExpr(new NameExpr("dual_df"), "alias"),
                                    Collections.singletonList(new ConstantExpr("dual"))
                                ),
                                "select"
                            ),
                            Collections.singletonList(
                                new CallExpr(
                                    new AttributeExpr(
                                        new CallExpr(
                                            new NameExpr("lit"),
                                            Collections.singletonList(new ConstantExpr(1))
                                        ),
                                        "alias"
                                    ),
                                    Collections.singletonList(new ConstantExpr("id"))
                                )
                            )
                        )
                    ),                    
                    // return transformation_df
                    new ReturnStmt(new NameExpr("transformation_df"))
                )
            );
            // Generate and print the code
            System.out.println(transformSt1.toCode(0));        

        FunctionDef readDataJdbc = new FunctionDef(
            "read_data_jdbc",
            Arrays.asList(new NameExpr("spark"), new NameExpr("jdbc_options")),
            Arrays.asList(
                // mssql = spark.read.format("jdbc").options(**jdbc_options)
                new AssignStmt(
                    new NameExpr("mssql"),
                    new CallExpr(
                        new AttributeExpr(
                            new CallExpr(
                                new AttributeExpr(
                                    new AttributeExpr(new NameExpr("spark"), "read"),
                                    "format"
                                ),
                                Collections.singletonList(new ConstantExpr("jdbc"))
                            ),
                            "options"
                        ),
                        Collections.singletonList(new NameExpr("**jdbc_options"))
                    )
                ),
                
                // source_dataframes = {}
                new AssignStmt(
                    new NameExpr("source_dataframes"),
                    new DictExpr(Collections.emptyList(), Collections.emptyList())
                ),
                
                // for source_table in source_tables():
                new ForStmt(
                    new NameExpr("source_table"),
                    new CallExpr(new NameExpr("source_tables"), Collections.emptyList()),
                    Collections.singletonList(
                        // source_dataframes[source_table] = mssql.option("dbtable", source_table).load()
                        new AssignStmt(
                            new SubscriptExpr(new NameExpr("source_dataframes"), new NameExpr("source_table")),
                            new CallExpr(
                                new AttributeExpr(
                                    new CallExpr(
                                        new AttributeExpr(new NameExpr("mssql"), "option"),
                                        Arrays.asList(new ConstantExpr("dbtable"), new NameExpr("source_table"))
                                    ),
                                    "load"
                                ),
                                Collections.emptyList()
                            )
                        )
                    )
                ),
                
                // return source_dataframes
                new ReturnStmt(new NameExpr("source_dataframes"))
            )
        );
        
        System.out.println(readDataJdbc.toCode(0));
        }
}
