package prj5;

import java.util.Comparator;

/**
 * A simple singly linked list.
 * 
 * @param <T>
 *            type of data
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class SinglyLinkedList<T>
{
    private Node<T> head;
    private int size;

    /**
     * Adds an item to the list.
     * 
     * @param item
     *            the item
     */
    public void add(T item)
    {
        Node<T> newNode = new Node<T>(item);

        if (head == null)
        {
            head = newNode;
        }
        else
        {
            Node<T> current = head;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        size++;
    }


    /**
     * Gets an item at an index.
     * 
     * @param index
     *            the index
     * @return the item
     */
    public T get(int index)
    {
        Node<T> current = head;

        for (int i = 0; i < index; i++)
        {
            current = current.getNext();
        }

        return current.getData();
    }


    /**
     * Gets the size of the list.
     * 
     * @return size
     */
    public int size()
    {
        return size;
    }


    /**
     * Sorts the list using insertion sort.
     * 
     * @param comparator
     *            the comparator used to compare items
     */
    public void insertionSort(Comparator<T> comparator)
    {
        if (size > 1)
        {
            Node<T> sorted = null;
            Node<T> current = head;

            while (current != null)
            {
                Node<T> next = current.getNext();
                sorted = insertInOrder(sorted, current, comparator);
                current = next;
            }

            head = sorted;
        }

    }


    /**
     * Inserts a node into the sorted part of the list.
     * 
     * @param sorted
     *            the sorted list
     * @param node
     *            the node to insert
     * @param comparator
     *            the comparator used to compare items
     * @return the new start of the sorted list
     */
    private
        Node<T>
        insertInOrder(Node<T> sorted, Node<T> node, Comparator<T> comparator)
    {
        if (sorted == null
            || comparator.compare(node.getData(), sorted.getData()) <= 0)
        {
            node.setNext(sorted);
            return node;
        }

        Node<T> current = sorted;

        while (current.getNext() != null && comparator
            .compare(node.getData(), current.getNext().getData()) > 0)
        {
            current = current.getNext();
        }

        node.setNext(current.getNext());
        current.setNext(node);

        return sorted;
    }
}
