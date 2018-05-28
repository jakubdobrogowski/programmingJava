package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.Exercises7.SdaBst;
import pl.kszafran.sda.algo.exercises.Exercises7.SdaHeap;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class Exercises7Test {

    private Exercises7 exercises = new Exercises7();

    @Test
    void test_SdaHeap_isHeap() {
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
    void test_SdaHeap_push() {
        SdaHeap<Integer> heap = exercises.createHeap(new Integer[]{9, 8, 7, 6, 5, 4, 3}, 15);
        heap.push(1);
        assertArrayEquals(new Integer[]{9, 8, 7, 6, 5, 4, 3, 1}, heap.toArray());
        heap.push(6);
        assertArrayEquals(new Integer[]{9, 8, 7, 6, 5, 4, 3, 1, 6}, heap.toArray());
        heap.push(8);
        assertArrayEquals(new Integer[]{9, 8, 7, 6, 8, 4, 3, 1, 6, 5}, heap.toArray());
        heap.push(10);
        assertArrayEquals(new Integer[]{10, 9, 7, 6, 8, 4, 3, 1, 6, 5, 8}, heap.toArray());
        assertEquals(11, heap.size());

        assertThrows(IllegalStateException.class, () -> exercises.createHeap(new Integer[]{5, 4, 3, 2}, 4).push(9));
    }

    @Test
    void test_SdaHeap_pop() {
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

    @Test
    void test_SdaBst_insert() {
        SdaBst<Integer> bst = exercises.createBst(new Integer[]{6, 7, 8, 4, 10, 1, 3, 14, 13});
        assertEquals(List.of(1, 3, 4, 6, 7, 8, 10, 13, 14), bst.toList());
    }

    @Test
    void test_SdaBst_contains() {
        SdaBst<Integer> bst = exercises.createBst(new Integer[]{6, 7, 8, 4, 10, 1, 3, 14, 13});
        assertTrue(bst.contains(8));
        assertTrue(bst.contains(14));
        assertTrue(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(5));
    }

    @Test
    void test_SdaBst_delete() {
        SdaBst<Integer> bst = exercises.createBst(new Integer[]{6, 7, 8, 4, 10, 1, 3, 14, 13});
        bst.delete(3);
        assertEquals(List.of(1, 4, 6, 7, 8, 10, 13, 14), bst.toList());
        bst.delete(8);
        assertEquals(List.of(1, 4, 6, 7, 10, 13, 14), bst.toList());
        bst.delete(6);
        assertEquals(List.of(1, 4, 7, 10, 13, 14), bst.toList());
        bst.delete(3);
        bst.delete(1);
        bst.delete(13);
        bst.delete(14);
        assertEquals(List.of(4, 7, 10), bst.toList());
        bst.delete(4);
        bst.delete(10);
        bst.delete(7);
        assertEquals(List.of(), bst.toList());
    }
}
