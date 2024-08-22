class DeclSeq {
	Decl decl;
	Function func;
	DeclSeq ds;
	
	void parse() {
		if(Parser.scanner.currentToken() == Core.PROCEDURE) {
			func = new Function();

			
			//Add function to map of id -> function
			Id id = func.parse();
			//System.out.println("ID: " + id.getString() + "  Func: " + func.toString());
			if(Memory.funcs.get(id.getString()) != null) {
				System.out.println("ERROR: Every function should have unique name");
			}
			Memory.funcs.put(id.getString(), func);
			if (Parser.scanner.currentToken() != Core.BEGIN) {
				ds = new DeclSeq();
				ds.parse();
			}
			decl = null;
		}else {
			decl = new Decl();
			decl.parse();
			if (Parser.scanner.currentToken() != Core.BEGIN) {
				ds = new DeclSeq();
				ds.parse();
			}
			func = null;
		}
		
	}
	
	void print(int indent) {
		decl.print(indent);
		if (ds != null) {
			ds.print(indent);
		}
	}
	
	void execute() {
		if(decl != null) {
			decl.execute();
		}
		if (ds != null) {
			ds.execute();
		}
	}
}