public abstract class UnaryOperator extends Operator {
  public UnaryOperator (ExpressionTree right)
  {this.right = right;}
  
	public ExpressionTree getRight()
	{return right;}
	
	
	public String postfix()
	{return right.postfix() + getOpSymbol() + " ";} 
	
	private ExpressionTree right;
}


