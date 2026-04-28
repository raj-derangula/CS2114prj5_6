package prj5;

/**
 * Represents a node in a singly linked list.
 * 
 * @param <T>
 *            type of data
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class Node<T>
{
    private T data;
    private Node<T> next;

    /**
     * Creates a new node.
     * 
     * @param data
     *            the data
     */
    public Node(T data)
    {
        this.data = data;
        this.next = null;
    }


    /**
     * Gets the data.
     * 
     * @return data
     */
    public T getData()
    {
        return data;
    }


    /**
     * Gets the next node.
     * 
     * @return next node
     */
    public Node<T> getNext()
    {
        return next;
    }


    /**
     * Sets the next node.
     * 
     * @param nextNode
     *            the next node
     */
    public void setNext(Node<T> nextNode)
    {
        next = nextNode;
    }
}
