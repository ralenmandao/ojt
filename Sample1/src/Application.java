//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//
// Program        : ExpressionTree Driver
//
// Author         : Richard E. Pattis
//                  Computer Science Department
//                  Carnegie Mellon University
//                  5000 Forbes Avenue
//                  Pittsburgh, PA 15213-3891
//                  e-mail: pattis@cs.cmu.edu
//
// Maintainer     : Author
//
//
// Description:
//
//   Reads an infix expression and creates an ExpresssionTree from it
// (using a Stack). Then evaluates the tree and prints the postfix
// (RPN) form of the expression.
//
//   This code works, but still is in rough form.
//
//
// Known Bugs     : None
//
// Future Plans   : None
//
// Program History:
//   11/19/01: R. Pattis - Operational in Java for 15-111
//
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


import java.util.StringTokenizer;


import java.io.IOException;


public class Application {

	public static int getPrecedence (String op)
	{
	  if (op.equals("+"))
	    return 1;
	  else if (op.equals("-"))
	    return 1;
	  else if (op.equals("*"))
	    return 2;
	  else if (op.equals("/"))
	    return 2;
	  else if (op.equals("^"))
	    return 3;
	  else if (op.equals("~"))
	    return 4;
	  else
	    throw new IllegalArgumentException("getPrecedence - op illegal = " + op);
	}



	public static ExpressionTree parse (StringTokenizer tokens) throws Exception
	{
	  MyStack ops  = new MyStack();
	  MyDeque exps = new MyDeque();
	  
	  ExpressionTree l,r;
	  
	  for (;tokens.hasMoreTokens();) {
	    String token = tokens.nextToken();;

	    //Ignore space
	    if (token.charAt(0) == ' ')
	      ;
	    
	    //Integer
	    else if ('0' <= token.charAt(0) && token.charAt(0) <= '9')
	      exps.insertBack(new Constant(Integer.parseInt(token)));
	    
	    // (
	    else if ( token.equals("(") )
	      ops.push("(");
	    
	    // ;
	    else if ( token.equals(";") )
	      while (ops.top != null) {
	        String op = (String)ops.pop();
	        if ( op.equals("(") )
	          throw new IllegalArgumentException("parse: Bad Expression, too many (");
	        r = (ExpressionTree)exps.removeFront();
	        l = op.equals("~") ? null : (ExpressionTree)exps.removeFront();
	        exps.insertBack( ExpressionTree.makeET(op,l,r) );
	     } 
	     
	    // )
	    else if ( token.equals(")") )
	      for (;;) {
	        if (ops.top == null)
	          throw new IllegalArgumentException("parse: Bad Expression, too many )");
	        String op = (String)ops.pop();
	        if ( op.equals("(") )
	          break;
	        r = (ExpressionTree)exps.removeFront();
	        l = op.equals("~") ? null : (ExpressionTree)exps.removeFront();
	        exps.insertBack( ExpressionTree.makeET(op,l,r));
	      } 
	     
	     // Operator
	     else {     
	      //Pop off all operators of less precedence; then push new operator
	      for (;ops.top != null;) {
	        String op = (String)ops.peek();
	        if ( op.equals("(") || getPrecedence(op) < getPrecedence(token) )
	          break;
	        ops.pop();
	        r = (ExpressionTree)exps.removeFront();
	        l = op.equals("~") ? null : (ExpressionTree)exps.removeFront();
		      exps.insertBack( ExpressionTree.makeET(op,l,r));
        }
	      
	      ops.push(token);
	    }
	   }

	  //Check stack status to return/throw exception
	  if (ops.isEmpty() && exps.getSize() == 1)
	    return (ExpressionTree)exps.removeFront();
	  else
	    throw new IllegalArgumentException("parse: Bad Expression, stacks not reduced at end");
	}
	
	
	public static void main(String[] args)
	{
	  System.out.println("Enter a normal expression");
	  System.out.println("You may use integers, (, ),  and operators ~ (negate) + - * / % ^ (power)");

	  for (;;)
		  try {
	      String          exp    = Prompt.forString("\nExpression");
	      StringTokenizer tokens = new StringTokenizer(exp+";","()+-*/%^~; ",true);
	      
		    //Parse the expresssion, print it in postfix, and evaluate it
		    ExpressionTree et = parse(tokens);
	      System.out.println("Postifx = " + et.postfix());
	      System.out.println("Answer  = " + et.evaluate());
	      
		  }catch (Exception e) {
		    System.out.println(e.getMessage());
	      e.printStackTrace();
	    }
  }
  
}
