import java.util.Vector;
import java.util.EmptyStackException;

/**
 A class of stacks whose entries are stored in a vector.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
public final class VectorStack<T> implements StackInterface<T> {
  private Vector<T> stack;   // Last element is the top entry in stack
  private boolean initialized = false;
  private static final int DEFAULT_CAPACITY = 50;
  private static final int MAX_CAPACITY = 10000;
  
  public VectorStack()  {
    this(DEFAULT_CAPACITY);
  }
  
  public VectorStack(int initialCapacity) {
    checkCapacity(initialCapacity);
    stack = new Vector<T>(initialCapacity); // Size doubles as needed
    initialized = true;
  } 
  
  public void push(T newEntry) {
    checkInitialization();
    stack.add(newEntry);
  } 
  
  public T peek() {
    checkInitialization();
    if (isEmpty())
      throw new EmptyStackException();
    else
      return stack.lastElement();
  } 
  
  public T pop() {
    checkInitialization();
    if (isEmpty())
      throw new EmptyStackException();
    else
      return stack.remove(stack.size() - 1);
  } 
  
  public boolean isEmpty() {
    return stack.isEmpty();
  } 
  
  public void clear() {
    stack.clear(); 
  }   
  // Throws an exception if this object is not initialized.
  private void checkInitialization() {
    if (!initialized)
      throw new SecurityException ("VectorStack object is " +
                                   "not initialized properly.");
  } 
  
  // Throws exception if the client requests a capacity that is too large.
  private void checkCapacity(int capacity) {
    if (capacity > MAX_CAPACITY)
      throw new IllegalStateException("Attempt to create a stack " +
                                      "whose capacity exceeds " +
                                      "allowed maximum.");
  }
  
}
