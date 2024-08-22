import java.util.*;
public class Call implements Stmt{
	Parameters p;
	Id id;
	public void parse() {
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		p = new Parameters();
		p.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	@Override
	public void execute() {
		//Do 5 things to execute function.
		
		Function f = Memory.funcs.get(id.getString());
		if(f == null) {
			System.out.println("ERROR: function call does not have valid target.");
			System.exit(0);
		}
		
		//System.out.println("CALL ID: " + id.getString()); //+ "  Func: " + f.toString());
		//f.execute(p);
		Parameters formal = f.p;
		
		ArrayList<String> formal1 = formal.toList();
		ArrayList<String> params = p.toList();
		
		//Error checking for duplicate formal params
		Set<String> checkDuplicates = new HashSet<>();
		for(int i = 0; i < formal1.size(); i++) {
			checkDuplicates.add(formal1.get(i));
		}
		if(checkDuplicates.size() < formal1.size()) {
			System.out.println("ERROR: Duplicate formal parameters");
			System.exit(0);
		}
		
		//Error checking number of parameters
		if(formal1.size() != params.size()) {
			System.out.println("ERROR: Number of arguments passed doesn't match function.");
			System.exit(0);
		}
		
		// Push stack of stack of maps
		Stack<HashMap<String, Memory.Value>> frame = new Stack<HashMap<String, Memory.Value>>();
		frame.push(new HashMap<String, Memory.Value>());
		for(int i = 0;i < formal1.size(); i++) {
			Memory.Value v = Memory.getLocalOrGlobal(params.get(i));
			Memory.Value v1 = new Memory.Value(Core.RECORD);
			v1.recordVal = v.recordVal;
			//Increment reference count
			v.recordVal[v.recordVal.length -1 ]++;
			frame.peek().put(formal1.get(i), v1);
		}
		Memory.local.push(frame);
		//Execute ss
		f.ss.execute();
		Memory.local.pop();
		
		//Decrement ref count for every record inside frame. Destroys frame.
		while(frame.size() > 0) {
			Memory.decrementRef(frame.pop());
		}
		
	}
	
	
	
	@Override
	public void print(int indent) {
		// TODO Auto-generated method stub
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		id.print();
		System.out.println("(");
		p.print();
		System.out.println(");");
	}
}
