public class Negate extends UnaryOperator {
  public Negate (ExpressionTree right)
  {super(right);}
  
	public int evaluate()
	{return - getRight().evaluate();}
	
	public String getOpSymbol()
	{return "~";}
}

