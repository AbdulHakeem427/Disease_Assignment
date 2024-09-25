package edu.disease.asn6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Container<T> implements Serializable {
	private static final long serialVersionUID = 1L;

    private List<T> items;

    /**
     * Constructs a Container with the specified items.
     *
     * @param items the items to store in the container
     * @throws IllegalArgumentException if no arguments were supplied
     */
    public Container(T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("At least one argument must be supplied.");
        }
        this.items = new ArrayList<>(Arrays.asList(items));
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Returns the number of values stored in the Container.
     *
     * @return the size of the Container
     */
    public int size() {
        return items.size();
    }

    /**
     * Returns the value at the supplied index.
     *
     * @param index the index of the value to retrieve
     * @return the value at the specified index
     * @throws IllegalArgumentException if the supplied index is invalid
     */
    public T get(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        return items.get(index);
    }

	
}
