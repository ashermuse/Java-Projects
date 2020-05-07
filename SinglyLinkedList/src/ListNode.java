
public class ListNode<T> {

	private T data;
	private ListNode<T> next;
	
	public ListNode(T data) {
		this.data = data;
		this.next = null;
	}
	
	public T getData() {
		return this.data;
	}
	
	public ListNode<T> getNext() {
		return this.next;
	}
	
	public void setNext(ListNode<T> newNext) {
		this.next = newNext;
	}

}