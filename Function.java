import java.util.*;
public class Function {
	StmtSeq ss;
	Parameters p;
	Id id;
	Frame[] frames;
	int i;
	
	public Function() {
		frames = new Frame[20];
		i = -1;
		
		//Push ss onto stack.
		/*
		 * Frame f = new Frame(); f.local = Memory.local; pushFrame(f);
		 */
	}
	Id parse() {
		
		Parser.expectedToken(Core.PROCEDURE);
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		p = new Parameters();
		p.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.IS);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
		
		
		return id;
	}

	/*
	 * public void pushFrame(Frame f) { i++; frames[i] = f; Memory.local = f.local;
	 * if(i >= 20) { System.out.println("ERROR: Function Stackoverflow error.");
	 * System.exit(0); } } public void popFrame() { i--; if(i < -1) {
	 * System.out.println("ERROR: Function stack underflow error."); System.exit(0);
	 * } Memory.local = frames[i].local; //return frames[i]; }
	 */
	public void execute(Parameters p1) {
		
		
		/*
		 * Frame f = new Frame(); //Create formal parameters and copy over values of
		 * arguments this.p.createParams(f); this.p.copyValues(f, p1); pushFrame(f);
		 * ss.execute(); popFrame();
		 */
		
	}
}
