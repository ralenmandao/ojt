
public class DLList {
	DLListNode head;
	DLListNode tail;
	
	public void insert(Object element){
		DLListNode node  = new DLListNode(element);
		if(head == null){
			head = node;
			tail = node;
		}else{
			node.next = head;
			head.prev = node;
			DLListNode temp = head;
			head = node;
			node = temp;
		}
	}
	
	public void append(Object element){
		DLListNode node = new DLListNode(element);
		if(tail == null){
			head = node;
			tail = node;
		}else{
			tail.next = node;
			node.prev = tail;
			DLListNode temp = tail;
			tail = node;
			node = temp;
		}
	}
}
