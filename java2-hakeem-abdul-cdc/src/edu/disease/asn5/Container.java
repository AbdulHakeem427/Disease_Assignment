package edu.disease.asn5;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;



public class Container<T> implements Serializable {

    private T[] items;
     
    
    

	/**
     * Constructs a Container with the specified items.
     *
     * @param items the items to store in the container
     * @throws IllegalArgumentException if no arguments were supplied
     */
    @SafeVarargs
    public Container(T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("At least one argument must be supplied.");
        }
        this.items = items;
    }
    
    public List<T> getItems() {
        return Arrays.asList(items);
    }
    public void setItems(T[] items) {
        this.items = items;
    }


    /**
     * Returns the number of values stored in the Container.
     *
     * @return the size of the Container
     */
    public int size() {
        return items.length;
    }

    /**
     * Returns the value at the supplied index.
     *
     * @param index the index of the value to retrieve
     * @return the value at the specified index
     * @throws IllegalArgumentException if the supplied index is invalid
     */
    public T get(int index) {
        if (index < 0 || index >= items.length) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        return items[index];
    }

	
}
