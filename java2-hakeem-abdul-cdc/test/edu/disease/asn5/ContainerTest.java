package edu.disease.asn5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ContainerTest {

    @Test
    void testContainerConstructor() {
        // Test with various types of arguments
        Container<String> container1 = new Container<>("apple", "orange", "banana");
        assertEquals(3, container1.size());

        Container<Integer> container2 = new Container<>(1, 2, 3, 4, 5);
        assertEquals(5, container2.size());

        Container<Double> container3 = new Container<>(3.14, 2.71);
        assertEquals(2, container3.size());

        // Test with a single argument
        Container<Boolean> container4 = new Container<>(true);
        assertEquals(1, container4.size());

        // Test with no arguments
        assertThrows(IllegalArgumentException.class, () -> new Container<>());
    }

    @Test
    void testSize() {
        // Test the size method
        Container<Integer> container = new Container<>(10, 20, 30, 40);
        assertEquals(4, container.size());
    }

    @Test
    void testGet() {
        // Test the get method
        Container<String> container = new Container<>("apple", "orange", "banana");

        // Test valid indices
        assertEquals("apple", container.get(0));
        assertEquals("orange", container.get(1));
        assertEquals("banana", container.get(2));

        // Test invalid indices
        assertThrows(IllegalArgumentException.class, () -> container.get(-1));
        assertThrows(IllegalArgumentException.class, () -> container.get(3));
    }
}
