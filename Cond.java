class Cond {
	Cmpr cmpr;
	Cond cond;
	int option;
	
	void parse() {
		option = 0;
		if (Parser.scanner.currentToken() == Core.NOT){
			option = 1;
			Parser.scanner.nextToken();
			cond = new Cond();
			cond.parse();
		} else {
			cmpr = new Cmpr();
			cmpr.parse();
			if (Parser.scanner.currentToken() == Core.OR) {
				option = 2;
				Parser.scanner.nextToken();
				cond = new Cond();
				cond.parse();
			} else if (Parser.scanner.currentToken() == Core.AND) {
				option = 3;
				Parser.scanner.nextToken();
				cond = new Cond();
				cond.parse();
			}
		}
	}
	
	void print() {
		if (cmpr == null) {
			System.out.print("not ");
			cond.print();
		} else {
			cmpr.print();
			if (cond != null) {
				if (option == 2) System.out.print(" or ");
				if (option == 3) System.out.print(" and ");
				cond.print();
			}
		}
	}
	
	boolean execute() {
		boolean value;
		if (cmpr == null) {
			value = !cond.execute();
		} else {
			value = cmpr.execute();
			if (cond != null) {
				if (option == 2) {
					value = value || cond.execute();
				} else {
					value = value && cond.execute();
				}
			}
		}
		return value;
	}
}