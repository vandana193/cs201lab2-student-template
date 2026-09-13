import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
		if (head == null || head.next == null) return;

		// 1. Collect all nodes in their ORIGINAL order
		java.util.List<Node> originalOrder = new java.util.ArrayList<Node>();
		Node curr = head;
		while (curr != null) {
			originalOrder.add(curr);
			curr = curr.next;
		}

		// 2. Create a list of nodes sorted by VALUE (Lowest to Highest)
		java.util.List<Node> sortedOrder = new java.util.ArrayList<Node>(originalOrder);
		
		//sortedOrder.sort((a, b) -> Integer.compare((int) a.getElement(), (int) b.getElement())); 
		
		sortedOrder.sort((a, b) -> {
			Comparable valA = (Comparable) a.getElement();
			Comparable valB = (Comparable) b.getElement();
			return valA.compareTo(valB);
		});
		

		// 3. Map each node to its swapped counterpart
		// (1st lowest <-> 1st highest, 2nd lowest <-> 2nd highest, etc.)
		java.util.Map<Node, Node> swapMap = new java.util.HashMap<>();
		int n = sortedOrder.size();
		for (int i = 0; i < n; i++) {
			Node lowest = sortedOrder.get(i);
			Node highest = sortedOrder.get(n - 1 - i);
			swapMap.put(lowest, highest);
		}

		// 4. Re-link the nodes in original positional order using mapped nodes
		head = swapMap.get(originalOrder.get(0));
		curr = head;
		for (int i = 1; i < originalOrder.size(); i++) {
			curr.next = swapMap.get(originalOrder.get(i));
			curr = curr.next;
		}
		tail = curr;
		curr.next = null;         

    }
   
}

