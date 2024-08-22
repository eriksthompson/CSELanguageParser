Erik Thompson

Names of files and description:
Assign.java - Assign class has parse, print, and check semantics method for the assignment production. It has three cases to check for in the grammar.
Call.java - Call class has parse, execute and print methods. It has one case to check for in grammar.
Cmpr.java - Cmpr class has parse, print, and check semantics method for the Cmpr production. It has two cases to check for in the grammar.
Cond.java - Cond class has parse, print, and check semantics method for the Cond production. It has four cases to check for in the grammar. It stores a Cmpr and Cond instance field for parse tree.
Core.java - Core class has the Enumeration for various tokens used in scanner
Decl.java - Decl class has parse, print, and check semantics method for the Decl production. It has two cases to check for in the grammar. It stores a Decl-integer and DeclRecord instance field for parse tree.
DeclInteger.java - DeclInteger class has parse and execute method for the DeclInteger production. It has one case to check for in the grammar. It stores a String name instance field for parse tree.
DeclRecord.java - DeclRecord class has parse and execute method for the DeclRecord production. It has one case to check for in the grammar. It stores a String name instance field for parse tree.
DeclSeq.java - DeclSeq class has parse and execute method for the DeclSeq production. It has three cases to check for in the grammar. It stores a Decl, Function, and DeclSeq instance field for parse tree. It only store one or the other of Decl or Function.
Expr.java - Expr class has parse, and execute method for the Expr production. It has three cases to check for in the grammar. It stores a term and expr instance field for parse tree.
Factor.java - Factor class has parse and execute method for the Factor production. It has four cases to check for in the grammar. It stores a String name and Expr and int constant instance field for parse tree.
Function.java - Function class has parse and execute method for the Function production. It only has one case to check for in the grammar.
If.java - If class has parse and execute method for the If production. It has two cases to check for in the grammar. It stores a Cond and two StmtSeq instance fields for parse tree.
Index.java - Index class has parse and execute method for the Index production. It has two cases to check for in the grammar. It stores a Expr instance field for parse tree.
Loop.java - Loop class has parse and execute method for the Loop production. It has one case to check for in the grammar. It stores a Cond and StmtSeq instance field for parse tree.
Main.java - Main class has main method to call parse and execute on the Procedure object.
Memory.java - Memory class has various methods for storing records and ints. It has three data structures for functions, global scope, and local scope. Added decrementRef method that takes a map of values and decrements every record.
Out.java - Out class has parse and execute method for the Out production. It has one case to check for in the grammar. It stores a Expr instance field for parse tree.
Parser.java - Parser class has a scanner instance and has expectedToken method for syntax checking.
Parameters.java - Parameters class has parse, print, and toList methods. It has two cases to check for in grammar.
Procedure.java - Procedure class has parse, print and execute method for the Procedure production. It has two cases to check for in the grammar. It stores a DeclSeq and StmtSeq instance field for parse tree.
Scanner.java - Scanner class from Project1. Reads in tokens from input. Another option is PerfectProject1 from class files folder.
Stmt.java - Stmt class has parse and execute method for the Stmt production. It has six cases to check for in the grammar. It stores a Assign, If, Loop, Out, Decl, and Call instance field for parse tree.
StmtSeq.java - StmtSeq class has parse and execute method for the StmtSeq production. It has two cases to check for in the grammar. It stores a Stmt and StmtSeq instance field for parse tree.
Term.java - Term class has parse and execute method for the Term production. It has three cases to check for in the grammar. It stores a Factor and Term instance field for parse tree.

Special Features- None

Testing: Tested using Professor's script. No known remaining bugs.