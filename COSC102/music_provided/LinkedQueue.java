/**
 A class that implements the ADT queue by using a chain of nodes
 that has both head and tail references.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */

public final class LinkedQueue<T> implements QueueInterface<T> {
  
  private Node firstNode; // References node at front of queue
  private Node lastNode;  // References node at back of queue
  private int count;
  
  public LinkedQueue() {
    firstNode = null;
    lastNode = null;
    count = 0;
  } 
  
  public void enqueue(T newEntry) {
    Node newNode = new Node(newEntry, null);
    
    if (isEmpty()) firstNode = newNode;
    else lastNode.setNextNode(newNode);
    
    lastNode = newNode;
    count++;
  } 
  
  public T getFront() {
    if (isEmpty()) throw new EmptyQueueException();
    else
      return firstNode.getData();
  }  
  
  public T dequeue() {
    T front = getFront();  // Might throw EmptyQueueException
    assert firstNode != null;
    firstNode.setData(null);
    firstNode = firstNode.getNextNode();
    
    if (firstNode == null) lastNode = null;
    count--;
    return front;
  }  
  
  public boolean isEmpty() {
    return (firstNode == null) && (lastNode == null);
  }  
  
  public void clear() {
    firstNode = null; 
    lastNode = null;
    count = 0;
  }  
  
  public int size() { return count; } 

  private class Node {
    private T    data; // Entry in queue
    private Node next; // Link to next node
    
    private Node(T dataPortion) {
      this(dataPortion, null);
    } 
    
    private Node(T dataPortion, Node linkPortion) {
      data = dataPortion;
      next = linkPortion; 
    } 
    
    private T getData() { return data; } 
    
    private void setData(T newData) { data = newData; }
    
    private Node getNextNode() { return next; }
    
    private void setNextNode(Node nextNode) { next = nextNode; }

  }
  
} 

/**
   A class of runtime exceptions thrown by methods to
   indicate that a queue is empty.
*/
class EmptyQueueException extends RuntimeException {
  
   public EmptyQueueException() { this(null); }
   
   public EmptyQueueException(String message) {
      super(message);
   } 
} 


