public class Power extends BinaryOperator {
  public Power (ExpressionTree left, ExpressionTree right)
  {super(left,right);}
  
	public int evaluate()
	{return (int)Math.pow(getLeft().evaluate(),getRight().evaluate());}
	
	public String getOpSymbol()
	{return "^";}
}