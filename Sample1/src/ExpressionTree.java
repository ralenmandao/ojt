//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//
// Classes for ExpressionTrees: I put each of these in its own file.
//
//                            ExpressionTree
//
//
// Constant      UnaryOperator           BinaryOperator
// 
//                 Negate      Add Subtract Multiply Divide Power
//
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


public abstract class ExpressionTree {
	public abstract int    evaluate();
	public abstract String postfix();
	
	//This is called a "Factory" Method. It constructs an expression tree
	public static ExpressionTree makeET(String op, ExpressionTree left, ExpressionTree right)
	{
	  if (op.equals("+"))
	    return new Add(left,right);
	  else if (op.equals("-"))
	    return new Subtract(left,right);
	  else if (op.equals("*"))
	    return new Multiply(left,right);
	  else if (op.equals("/"))
	    return new Divide(left,right);
	  else if (op.equals("^"))
	    return new Power(left,right);
	  else if (op.equals("~"))
	    return new Negate(right);
	  else
	    throw new IllegalArgumentException("ExpressionTree.makeET: illegal operators = " + op);
	}
}
