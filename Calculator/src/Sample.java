
public class Sample {
	public static void main(String[] args){
		MyStack stack = new MyStack();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		String lol = (String) stack.pop();
		while(lol != null){
			System.out.println(lol);
			 lol = (String) stack.pop();
		}
	}
	
	public static int evaluatePostfix(String[] expr) {
		MyStack nums = new MyStack();
	    for(int i = 0; i < expr.length; i++) {
	        String elt = expr[i];
	        if(elt.equals("+")) {
	            int b = (int) nums.pop();
	            int a = (int) nums.pop();
	            nums.push(new Integer(a + b));
	        } else if(elt.equals("-")) {
	            int b = (int) nums.pop();
	            int a = (int) nums.pop();
	            nums.push(new Integer(a - b));
	        } else if(elt.equals("*")) {
	            int b = (int) nums.pop();
	            int a = (int) nums.pop();
	            nums.push(new Integer(a * b));
	        } else if(elt.equals("/")) {
	            int b = (int) nums.pop();
	            int a = (int) nums.pop();
	            nums.push(new Integer(a / b));
	        } else { // it must be a number
	            int x = Integer.parseInt(elt);
	            nums.push(new Integer(x));
	        }
	    }
	    return (int) nums.pop();
	}
}
