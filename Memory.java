import java.util.*;

class Memory {
	//scanner is stored here as a static field so it is avaiable to the execute method for factor
	public static Scanner data;
	public static int reachableObj = 0;
	
	// Class and data structures to represent variables
	static class Value {
		Core type;
		int integerVal;
		int[] recordVal;
		Value(Core t) {
			this.type = t;
		}
	}
	
	public static HashMap<String, Function> funcs;
	public static HashMap<String, Value> global;
	public static Stack<Stack<HashMap<String, Value>>> local;
	
	// Helper methods to manage memory
	
	// This inializes the global memory structure
	// Called before executing the DeclSeq
	public static void initializeGlobal() {
		global = new HashMap<String, Value>();
	}
	
	public static void initializeFuncs() {
		funcs = new HashMap<String, Function>();
	}
	
	// Initializes the local data structure
	// Called before executing the main StmtSeq
	public static void initializeLocal() {
		local = new Stack<Stack<HashMap<String, Value>>>();
		local.push(new Stack<HashMap<String, Value>>());
		local.peek().push(new HashMap<String, Value>());
	}
	
	// Pushes a "scope" for if/loop stmts
	public static void pushScope() {
		local.peek().push(new HashMap<String, Value>());
	}
	
	// Pops a "scope"
	public static void popScope() {
		decrementRef(local.peek().pop());
		//local.peek().pop()
		
		
	}
	
	// Handles decl integer
	public static void declareInteger(String id) {
		Value v = new Value(Core.INTEGER);
		if (local != null) {
			local.peek().peek().put(id, v);
		} else {
			global.put(id, v);
		}
	}
	
	//public static ArrayList<Value> copyValues(ArrayList<Value> a){
		
	//}
	
	// Handles decl record
	public static void declareRecord(String id) {
		Value v = new Value(Core.RECORD);
		if (local != null) {
			local.peek().peek().put(id, v);
		} else {
			global.put(id, v);
		}
	}
	
	
	
	// Retrives a value from memory (integer or record at index 0
	public static int load(String id) {
		int value;
		Value v = getLocalOrGlobal(id);
		if (v.type == Core.INTEGER) {
			value = v.integerVal;
		} else {
			value = v.recordVal[0];
		}
		return value;
	}
	
	// Retrieves a record value from the index
	public static int load(String id, int index) {
		Value v = getLocalOrGlobal(id);
		return v.recordVal[index];
	}
	
	// Stores a value (integer or record at index 0
	public static void store(String id, int value) {
		Value v = getLocalOrGlobal(id);
		if (v.type == Core.INTEGER) {
			v.integerVal = value;
		} else {
			v.recordVal[0] = value;
		}
	}
	
	// Stores a value at record index
	public static void store(String id, int index, int value) {
		Value v = getLocalOrGlobal(id);
		v.recordVal[index] = value;
	}
	
	// Handles "new record" assignment
	public static void allocate(String id, int index) {
		Value v = getLocalOrGlobal(id);
		v.recordVal = new int[index + 1];
		//Sets reference count to 1.
		v.recordVal[v.recordVal.length-1] = 1;
		reachableObj++;
		System.out.println("gc:" + reachableObj);
	}
	
	//Decrements all record types in given Map of values.
	public static void decrementRef(HashMap<String, Memory.Value> h) {
		for(Memory.Value v: h.values()) {
			if(v.type == Core.RECORD && v.recordVal != null) {
				v.recordVal[v.recordVal.length-1] = v.recordVal[v.recordVal.length-1] - 1;
				if(v.recordVal[v.recordVal.length-1] == 0) {
					reachableObj--;
					System.out.println("gc:" + reachableObj);
				}
			}
			
		}
	}
	
	// Handles "id := record id" assignment
	public static void alias(String lhs, String rhs) {
		Value v1 = getLocalOrGlobal(lhs);
		Value v2 = getLocalOrGlobal(rhs);
		if(v1.recordVal != null) {
			v1.recordVal[v1.recordVal.length -1 ]--;
			if(v1.recordVal[v1.recordVal.length-1] == 0) {
				reachableObj--;
				System.out.println("gc:" + reachableObj);
			}
		}
		v1.recordVal = v2.recordVal;
		if(v2.recordVal != null) {	
			v2.recordVal[v2.recordVal.length -1 ]++;
		}
		
	}
	
	// Looks up value of the variables, searches local then global
	public static Value getLocalOrGlobal(String id) {
		Value result;
		if (local.peek().size() > 0) {
			if (local.peek().peek().containsKey(id)) {
				result = local.peek().peek().get(id);
			} else {
				HashMap<String, Value> temp = local.peek().pop();
				result = getLocalOrGlobal(id);
				local.peek().push(temp);
			}
		} else {
			result = global.get(id);
		}
		return result;
	}

	public static void decrementGlobalRef() {
		decrementRef(global);
	}

}