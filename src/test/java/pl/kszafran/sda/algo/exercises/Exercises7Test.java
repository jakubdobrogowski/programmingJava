package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.Exercises7.SdaHeap;
import pl.kszafran.sda.algo.solutions.Solutions7;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class Exercises7Test {

    private Exercises7 exercises = new Solutions7();

    @Test
    void test_isHeap() {
        assertTrue(exercises.isHeap(new int[]{9, 8, 7, 6, 5, 4, 3}));
        assertTrue(exercises.isHeap(new int[]{9, 8, 7, 6, 5, 4, 3, 2}));
        assertFalse(exercises.isHeap(new int[]{0, 1, 2, 0, 4, 5, 6, 7, 8, 9}));
        assertTrue(exercises.isHeap(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        assertFalse(exercises.isHeap(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 10}));
        assertFalse(exercises.isHeap(new int[]{5, 5, 5, 6, 6, 6, 6, 7, 7, 1}));
        assertTrue(exercises.isHeap(new int[]{9, 3, 9, 2, 1, 6, 7, 1, 2, 1}));
        assertFalse(exercises.isHeap(new int[]{8, 7, 6, 1, 2, 3, 4, 2, 1, 2}));
        assertTrue(exercises.isHeap(new int[]{10}));
        assertTrue(exercises.isHeap(new int[]{10, 8}));
        assertTrue(exercises.isHeap(new int[0]));
    }

    @Test
    void test_push() {
        SdaHeap<Integer> heap = exercises.createHeap(new Integer[]{9, 8, 7, 6, 5, 4, 3}, 15);
        heap.push(1);
        assertArrayEquals(heap.toArray(), new Integer[]{9, 8, 7, 6, 5, 4, 3, 1});
        heap.push(6);
        assertArrayEquals(heap.toArray(), new Integer[]{9, 8, 7, 6, 5, 4, 3, 1, 6});
        heap.push(8);
        assertArrayEquals(heap.toArray(), new Integer[]{9, 8, 7, 6, 8, 4, 3, 1, 6, 5});
        heap.push(10);
        assertArrayEquals(heap.toArray(), new Integer[]{10, 9, 7, 6, 8, 4, 3, 1, 6, 5, 8});
        assertEquals(11, heap.size());

        assertThrows(IllegalStateException.class, () -> exercises.createHeap(new Integer[]{5, 4, 3, 2}, 4).push(9));
    }

    @Test
    void test_pop() {
        SdaHeap<Integer> heap = exercises.createHeap(new Integer[]{9, 3, 9, 2, 1, 6, 7, 1, 2, 1}, 15);
        assertEquals(9, (int) heap.pop());
        assertEquals(9, (int) heap.pop());
        assertEquals(7, (int) heap.pop());
        assertEquals(6, (int) heap.pop());
        assertEquals(6, heap.size());
        assertEquals(3, (int) heap.pop());
        assertEquals(2, (int) heap.pop());
        assertEquals(2, (int) heap.pop());
        assertEquals(1, (int) heap.pop());
        assertEquals(1, (int) heap.pop());
        assertEquals(1, (int) heap.pop());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, heap::pop);
    }
}
