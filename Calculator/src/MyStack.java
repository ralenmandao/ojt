
public class MyStack
{
    public SLListNode top;
    public Object pop()
    {
        if(top == null)
            return null;
        Object temp = top.data;
        top = top.next;
        return temp;
    }
    
    public void push(Object element)
    {
        top = new SLListNode(element,top);
    }
    
    public Object top()
    {
        return top == null? null: top.data;
    }
    
    public String toString()
    {
        String out = "MyStack contains: \n";
        SLListNode ref = top;
        if(top == null)
            return out + "0 Nodes";
        else
            out += "front --> \t";
        while(ref.next != null)
        {
            out += ref.data + "\t ---> \t";
            ref = ref.next;
        }
        out += ref.data + "\t ---> \t";
        return out;
    }
}
