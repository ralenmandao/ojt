
public class MyDeque extends DLList
{
    public MyDeque()
    {
        super();
    }
    public Object front()
    {
        if(head == null)
            return null;
        return head.data;
    }
    
    public Object back()
    {
        if(head == null) return null;
        return tail.data;
        
    }
    
    public void insertBack(Object element)
    {
        append(element);
    }
    
    public Object removeBack()
    {
        if(head == null)
            return null;
        Object temp = tail.data;
        if(head == tail)
        {
            head = tail = null;
            return temp;
        }
        tail = tail.prev;
        tail.next = null;
        return temp;
    }
    
    public void insertFront(Object element)
    {
        insert(element);
    }
    
    public Object removeFront()
    {
        if(head == null)
            return null;
        Object temp = head.data;
        if(head == tail)
        {
            head = tail = null;
        }
        else
        {
            head = head.next;
            head.prev = null;
        }
        return temp;
    }
}
