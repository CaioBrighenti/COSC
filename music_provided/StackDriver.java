/**
 A driver that demonstrates the class VectorStack.
 
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
public class StackDriver {
  
  public static void main(String[] args) {
    testStackOperations();
  } 
  
  public static void testStackOperations() {
    System.out.println("Create a stack: ");
    StackInterface<String> myStack = new VectorStack<String>();
    System.out.println("isEmpty() returns " + myStack.isEmpty());
    
    System.out.println("\nAdd to stack to get\n" +
                       "Joe Jane Jill Jess Jim");
    myStack.push("Jim"); myStack.push("Jess");
    myStack.push("Jill"); myStack.push("Jane"); myStack.push("Joe");
    
    System.out.println("\nisEmpty() returns " + myStack.isEmpty());
    
    System.out.println("\nTesting peek and pop:");
    
    while (!myStack.isEmpty()) {
      String top = myStack.peek();
      System.out.println("\n" + top + " is at the top of the stack.");
      
      top = myStack.pop();
      System.out.println(top + " is removed from the stack.");
    } 
    
    System.out.print("\nThe stack should be empty: ");
    System.out.println("isEmpty() returns " + myStack.isEmpty());
    
    System.out.println("\nAdd to stack to get\n" +
                       "Jim Jess Joe\n");
    myStack.push("Joe"); myStack.push("Jess"); myStack.push("Jim");
    
    System.out.println("\nTesting clear:");
    myStack.clear();
    
    System.out.println("The stack should be empty: ");
    System.out.println("\nisEmpty() returns " + myStack.isEmpty() + "\n");
    
    System.out.println("myStack.peek() returns ");
    
    //UNCOMMENT: to experiment with Exception
    /*
    System.out.println(myStack.peek() + "\n");
    */
  
  } 
}  

/*
 Create a stack:
 isEmpty() returns true
 
 Add to stack to get
 Joe Jane Jill Jess Jim
 
 isEmpty() returns false
 
 Testing peek and pop:
 
 Joe is at the top of the stack.
 Joe is removed from the stack.
 
 Jane is at the top of the stack.
 Jane is removed from the stack.
 
 Jill is at the top of the stack.
 Jill is removed from the stack.
 
 Jess is at the top of the stack.
 Jess is removed from the stack.
 
 Jim is at the top of the stack.
 Jim is removed from the stack.
 
 The stack should be empty: isEmpty() returns true
 
 Add to stack to get
 Jim Jess Joe
 
 
 Testing clear:
 The stack should be empty:
 
 isEmpty() returns true
 
 myStack.peek() returns
 Exception in thread "main" java.util.EmptyStackException
 at VectorStack.peek(VectorStack.java:34)
 at Driver.testStackOperations(Driver.java:50)
 at Driver.main(Driver.java:11)
        */