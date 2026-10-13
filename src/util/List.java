package util;

import java.util.Iterator;

/**
 * A generic resizable array list that can hold any type of object.
 * The initial capacity is 4 and it grows by 4 when the list is full.
 *
 * @param <E> the type of objects in the list.
 * @author Alisha Sharma
 */
public class List<E> implements Iterable<E> {
    private static final int CAPACITY = 4;
    private static final int NOT_FOUND = -1;

    private E[] objects; //E is the name for the generic type
    private int size;    //number of elements in the array

    /**
     * Creates an empty list with capacity of 4.
     */
    @SuppressWarnings("unchecked")
    public List() {
        objects = (E[]) new Object[CAPACITY];
        size = 0;
    }

    /**
     * Finds an object in the list.
     *
     * @param e the object to search for.
     * @return the index of the object or -1 if not in the list
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Increases capacity of the list by 4.
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        E[] bigger = (E[]) new Object[objects.length + CAPACITY];
        for (int i = 0; i < size; i++) {
            bigger[i] = objects[i];
        }
        objects = bigger;
    }

    /**
     * Checks if an object is in the list.
     *
     * @param e the object to search for.
     * @return true if the object is in the list otherwise false.
     */
    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }

    /**
     * Adds an object to end of the list.
     *
     * @param e the object to add.
     */
    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size] = e;
        size++;
    }

    /**
     * Removes the first occurrence of an object and shifts the objects after
     * it one position to the left. It does nothing if the object is not found.
     *
     * @param e the object to remove.
     */
    public void remove(E e) {
        int index = find(e);
        if (index == NOT_FOUND) {
            return;
        }
        for (int i = index; i < size - 1; i++) {
            objects[i] = objects[i + 1];
        }
        objects[size - 1] = null;
        size--;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list has no objects otherwise false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of objects in the list.
     *
     * @return the number of objects.
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator for traversing the list with a for-each loop
     *
     * @return an iterator over the objects in the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>();
    }

    /**
     * Returns the object at an index.
     *
     * @param index the index of the object.
     * @return the object at the index or null if the index is out of range.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return objects[index];
    }

    /**
     * Replaces the object at an index. does nothing if the index is
     * out of range.
     *
     * @param index the index to put the object at.
     * @param e     the object to put in the list.
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            return;
        }
        objects[index] = e;
    }

    /**
     * Returns the index of an object in the list.
     *
     * @param e the object to search for.
     * @return the index of the object or -1 if not in the list.
     */
    public int indexOf(E e) {
        return find(e);
    }

    /**
     * An iterator for traversing the list one object at a time.
     *
     * @param <E> the type of objects in the list
     */
    private class ListIterator<E> implements Iterator<E> {
        int current = 0; //current index when traversing the list (array)

        /**
         * Checks if there are more objects to traverse.
         *
         * @return true if there is another object or false if at the end of the list.
         */
        @Override
        public boolean hasNext() {
            return current < size;
        }

        /**
         * Returns the object at the current index then moves to the next one.
         *
         * @return the object at the current index.
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            E object = (E) objects[current];
            current++;
            return object;
        }
    }
}