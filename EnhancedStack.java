import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EnhancedStack.java
 * A generic class, beside keeping a reference to the top of the stack,
 * we also keep a reference to the smallest element in the stack. 
 * Each stack member keeps a reference to the smallest element 
 * in the stack when it was added.
 * 
 * @author Huy Tu
 * @version 2/21/2014
 * 
 * @param <T>
 *            The container for the data in the node.
 */

public class EnhancedStack <T  extends Comparable<?super T>> 
    implements Iterable<T>
{   
    private Node<T> top;
    private Node<T> minimum;
    private int size;

    /**
     * EnhancedStack constructor creates a stack
     * while initialize minimum, top, and size variables.
     */
    public EnhancedStack()
    {
        top = null;
        minimum = null;
        size = 0; 
    }

    /**
     * 
     * @return the top element.
     */
    public T top()
    {
        if (top == null)
        {
            throw new NullPointerException();
        }
        else
        {
            return top.getData();
        }
    }
    
    /**
     * @return the number of elements on the Stack
     */
    public int size()
    {
        return size;
    }
    
    /**
     * 
     * @return if the stack is empty.
     */
    public boolean isEmpty()
    {
        return (top == null);
    }
    
    /**
     * 
     * @return data of the minimum node.
     */
    public T minimum()
    {
        return minimum.getData(); 
    }
    
    /**
     * Adds the data item to the top of the stack.
     * @param data Command Line Argument
     */
    public void push(T data)            
    {
        if (size == 0)
        {
            top = new Node<T>(data, null, null);
            minimum = top; 
        }
        else
        {
            top = new Node<T>(data, top, minimum);
            if (top.getData().compareTo(minimum.getData()) < 0)
            {
                minimum = top;
            }
        }
        size++;

    }
    
    /**
     * Return the element on top of the stack
     * While popping that element out.
     * @return Command Line Argument
     */
    public T pop()            
    {
        T answer;

        if (top == null)
        {    
            throw new EmptyStackException();
        }
        answer = top.getData();
        if (top == minimum)
        {
            minimum = top.small;
        }
        top = top.getLink();
        size--;
        return answer;
    }
    
    /**
     * Implement iterator for the stack.
     * @return Command Line Argument
     */
    public Iterator<T> iterator()
    {
        // TODO Auto-generated method stub
        return (Iterator<T>) new Tools<T>(top);
    }


    /**
     * Node class to create node element in the stack.
     * 
     * @author Huy Tu
     * @param <T> The container for the data in the node.
     */
    private static class Node<T>
    {
        public T data;
        public Node<T> link;
        public Node<T> small;     

        /**
         * Constructor takes a data element and node reference.
         * 
         * @param data
         *            The data for this node.
         * @param link
         *            The next node in the list.
         * @param small
         *            The smallest node in the list.
         */
        public Node(T data, Node<T> link, Node<T> small)
        {
            this.data = data;
            this.link = link;
            this.small = small;
        }

        /**
         * Changes the link of this node.
         * 
         * @param newLink
         *            The new link.
         */
        public void setLink(Node<T> newLink)
        {
            link = newLink;
        }

        /**
         * Changes the data for this node.
         * 
         * @param newData
         *            The new data.
         */
        public void setData(T newData)
        {
            data = newData;
        }

        /**
         * @return the link.
         */
        public Node<T> getLink()
        {
            return link;
        }

        /**
         * @return the data.
         */
        public T getData()
        {
            return data;
        }


    }

    /**
     * Inner iterator class for the Enhanced Stack.
     * 
     * @author Huy Tu
     * @param <T> The container for the data in the node.
     */
    private class Tools<T> implements Iterator<T>
    {
        Node<T> cursor;

        /**
         * Constructor for inner Class Tools<E>.
         * @param head Command Line Argument
         */
        private Tools(Node<T> head)
        {
            cursor = head;
        }


        /**
         * @return Command Line Argument
         * Return the next node.
         */
        public T next()
        {
            if (!hasNext())
            { 
                throw new NoSuchElementException("");
            }
            else
            {
                T node = cursor.getData();
                cursor =  cursor.getLink();
                return node;
            }   
        }

        /**
         * @return Command Line Argument
         * Check if there is a next element of the current node. 
         */
        public boolean hasNext()
        {
            return (cursor != null);
        }

        /**
         * Remove the current element,
         * Throw UnsupportedOperationException().
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

}




