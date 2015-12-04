public class Divide extends BinaryOperator {
  public Divide (ExpressionTree left, ExpressionTree right)
  {super(left,right);}
  
	public int evaluate()
	{return getLeft().evaluate() / getRight().evaluate();}
	
	public String getOpSymbol()
	{return "/";}
}