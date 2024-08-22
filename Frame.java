import java.util.HashMap;
import java.util.Stack;


public class Frame {
	public HashMap<String, Memory.Value> global;
	public Stack<HashMap<String, Memory.Value>> local;
	
	
	
	public Frame() {
		global = new HashMap<String, Memory.Value>();
		local = new Stack<HashMap<String, Memory.Value>>();
		local.push(new HashMap<String, Memory.Value>());
	}
}
