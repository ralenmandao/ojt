import java.util.Scanner;

public class ExpressionTreeTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Expression Tree Test");
 
        /** make object of ExpressionTree **/
        ExpressionTree et = new ExpressionTree();
 
        System.out.println("\nEnter equation in prefix form");
        et.buildTree(scan.next());
 
        System.out.print("\nPrefix  : ");
        et.prefix();
        System.out.print("\n\nInfix   : ");
        et.infix();
        System.out.print("\n\nPostfix : ");
        et.postfix();
        System.out.println("\n\nEvaluated Result : "+ et.evaluate());
    }
}