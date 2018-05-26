package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Solutions7 extends Exercises7 {

    @Override
    public boolean isHeap(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (array[left] > array[i] || (right < array.length && array[right] > array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <T extends Comparable<T>> SdaHeap<T> createHeap(T[] heap, int capacity) {
        return new FixedSizeSdaHeap<>(heap, capacity);
    }

    private static class FixedSizeSdaHeap<T extends Comparable<T>> implements SdaHeap<T> {

        private T[] heap;
        private int size;

        // zakładamy, że "heap" jest poprawnym kopcem
        public FixedSizeSdaHeap(T[] heap, int capacity) {
            this.heap = Arrays.copyOf(heap, capacity);
            this.size = heap.length;
        }

        @Override
        public void push(T element) {
            if (size == heap.length) {
                throw new IllegalStateException("Heap full");
            }
            heap[size] = element;
            siftUp(size);
            size++;
        }

        private void siftUp(int i) {
            if (i == 0) {
                return;
            }
            int parent = (i - 1) / 2;
            if (heap[parent].compareTo(heap[i]) < 0) {
                swap(parent, i);
                siftUp(parent);
            }
        }

        @Override
        public T pop() {
            if (size == 0) {
                throw new NoSuchElementException("Heap empty");
            }
            T result = heap[0];
            heap[0] = null;
            size--;
            swap(0, size);
            siftDown(0);
            return result;
        }

        private void siftDown(int i) {
            int largest = i;

            int left = 2 * i + 1;
            if (left < heap.length && heap[left] != null && heap[left].compareTo(heap[largest]) > 0) {
                largest = left;
            }

            int right = 2 * i + 2;
            if (right < heap.length && heap[right] != null && heap[right].compareTo(heap[largest]) > 0) {
                largest = right;
            }

            if (i != largest) {
                swap(i, largest);
                siftDown(largest);
            }
        }

        private void swap(int i, int j) {
            T temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        @Override
        public int size() {
            return size;
        }

        public T[] toArray() {
            return Arrays.copyOf(heap, size);
        }
    }

    @Override
    public <T extends Comparable<T>> SdaBst<T> createBst(T[] elements) {
        return new SdaBstImpl<>(elements);
    }

    private static class SdaBstImpl<T extends Comparable<T>> implements SdaBst<T> {

        private Node root;

        public SdaBstImpl(T[] elements) {
            for (T element : elements) {
                insert(element);
            }
        }

        @Override
        public void insert(T element) {
            if (root == null) {
                root = new Node(element, null, null);
            } else {
                insert(root, element);
            }
        }

        private void insert(Node node, T element) {
            if (node.value.compareTo(element) < 0) {
                if (node.right == null) {
                    node.right = new Node(element, null, null);
                } else {
                    insert(node.right, element);
                }
            } else if (node.value.compareTo(element) > 0) {
                if (node.left == null) {
                    node.left = new Node(element, null, null);
                } else {
                    insert(node.left, element);
                }
            } else {
                node.value = element;
            }
        }

        @Override
        public boolean contains(T element) {
            return contains(root, element);
        }

        private boolean contains(Node node, T element) {
            if (node == null) {
                return false;
            } else {
                return node.value == element || contains(node.left, element) || contains(node.right, element);
            }
        }

        @Override
        public void delete(T element) {
            root = delete(root, element);
        }

        private Node delete(Node node, T element) {
            if (node == null) {                             // ¯\_(ツ)_/¯
                return null;
            } else if (node.value.compareTo(element) < 0) { // węzeł do usunięcia znajduje się w prawym poddrzewie
                node.right = delete(node.right, element);
                return node;
            } else if (node.value.compareTo(element) > 0) { // węzeł do usunięcia znajduje się w lewym poddrzewie
                node.left = delete(node.left, element);
                return node;
            } else if (node.left == null) {                 // węzeł do usunięcia ma co najwyżej prawe dziecko
                return node.right;
            } else if (node.right == null) {                // węzeł do usunięcia ma co najwyżej lewe dziecko
                return node.left;
            } else {                                        // węzeł do usunięcia ma dwoje dzieci
                Node successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                delete(node, successor.value);
                node.value = successor.value;
                return node;
            }
        }

        @Override
        public List<T> toList() {
            List<T> list = new ArrayList<>();
            inOrder(root, list::add);
            return list;
        }

        private void inOrder(Node node, Consumer<T> visitor) {
            if (node == null) {
                return;
            }
            inOrder(node.left, visitor);
            visitor.accept(node.value);
            inOrder(node.right, visitor);
        }

        private class Node {

            private T value;
            private Node left;
            private Node right;

            public Node(T value, Node left, Node right) {
                this.value = value;
                this.left = left;
                this.right = right;
            }
        }
    }
}