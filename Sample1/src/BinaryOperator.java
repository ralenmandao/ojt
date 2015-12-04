public abstract class BinaryOperator extends Operator {
  public BinaryOperator (ExpressionTree left, ExpressionTree right)
  {
    this.left  = left;
    this.right = right;
  }
  
	public ExpressionTree getLeft()
	{return left;}
	
	public ExpressionTree getRight()
	{return right;}
	
	public String postfix()
	{return left.postfix() + right.postfix() + getOpSymbol() + " ";} 
	
	private ExpressionTree left,right;
}

