import java.util.ArrayList;
import java.util.HashMap;

public class Parameters {
	Id id;
	Parameters p;
	void parse() {
		id = new Id();
		id.parse();
		p = null;
		if(Parser.scanner.currentToken() == Core.COMMA) {
			Parser.scanner.nextToken();
			p = new Parameters();
			p.parse();
		}
		
	}
	
	public void createParams(Frame f) {
		HashMap<String, Memory.Value> h = f.local.pop();
		Memory.Value v = new Memory.Value(Core.RECORD);
		h.put(id.getString(), v);
		
		f.local.push(h);
		if(p != null) {
			p.createParams(f);
		}
	}
	
	public void print() {
		id.print();
		if(p != null) {
			System.out.println(" , ");
			p.print();
		}
	}

	public ArrayList<String> toList(){
		ArrayList<String> a = new ArrayList<>();
		a.add(id.getString());
		if(p != null) {
			a.addAll(p.toList());
		}
		return a;
	}
	
	public void copyValues(Frame f, Parameters p2) {
		Memory.alias(id.getString(), p2.id.getString());
		if(p != null) {
			p.copyValues(f, p2.p);
		}
		
	}
	
	/*public ArrayList<Id> retParams(ArrayList<Id> a){
		ArrayList<Id> a1 = new ArrayList<>();
		if(p == null) {
			
			a1.add(id);
			return a1;
		}else {
			retParams(a);
		}
	}*/
}
