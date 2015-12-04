public class Constant extends ExpressionTree {
  public Constant (int value)
  {this.value = value;}
  
	public int evaluate()
	{return value;}
	
	public String postfix()
	{return ""+value+" ";}
	
	private int value;
}

