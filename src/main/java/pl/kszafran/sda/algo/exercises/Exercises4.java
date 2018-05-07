package pl.kszafran.sda.algo.exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Zaimplementuj poniższe metody operujące na liście wiązanej jednokierunkowej.
 */
public class Exercises4 {

    /**
     * Tworzy nową listę zawierającą podane elementy.
     */
    public <T> SdaList<T> createList(T... elements) {  //metoda

        return new SdaLinkedList<>(elements);
    }

    public interface SdaList<T> extends Iterable<T> {  //tu jest interfejs

        /**
         * Zwraca true jeśli lista jest pusta.
         */
        boolean isEmpty();

        /**
         * Zwraca rozmiar listy (ilość elementów).
         */
        int size();

        /**
         * Zwraca pierwszy element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        T getFirst();

        /**
         * Zwraca ostatni element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        T getLast();

        /**
         * Pobiera element listy pod podanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        T get(int index);

        /**
         * Usuwa wszystkie elementy z listy.
         */
        void clear();

        /**
         * Dodaje nowy element na początku listy.
         */
        void addFirst(T element);

        /**
         * Dodaje nowy element na końcu listy.
         */
        void addLast(T element);

        /**
         * Usuwa pierwszy element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        void removeFirst();

        /**
         * Usuwa ostatni element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        void removeLast();

        ////////////////////////////////////////////
        //                                        //
        // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
        //                                        //
        ////////////////////////////////////////////

        /**
         * Zwraca iterator po elementach listy.
         *
         * @see java.lang.Iterable
         */
        @Override
        Iterator<T> iterator();

        /**
         * Zamienia element pod podanym indeksem.
         */
        void setAt(int index, T element);

        /**
         * Dodaje nowy element pod wskazanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        void addAt(int index, T element);

        /**
         * Usuwa element pod wskazanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        void removeAt(int index);
    }

    private static class SdaLinkedList<T> implements SdaList<T> {

        private Node<T> head; // pole typu Node w klasie SdaLinkedList

        public SdaLinkedList(T[] elements) {

            for (int i = elements.length - 1; i >= 0; i--) {

                head = new Node<>(elements[i], head);
            }
        }

        @Override
        public boolean isEmpty() {

            if (head == null) {
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            int COUNTER = 0;
            Node<T> iter = head;
            while (iter != null) {

                iter = iter.next;
                COUNTER++;
            }
            return COUNTER;
        }

        @Override
        public T getFirst() {

            if (head == null) {

                throw new NoSuchElementException();
            }

            return head.element;
        }

        @Override
        public T getLast() {

            if (head == null) {

                throw new NoSuchElementException();
            }
            if (head.next == null) {

                return head.element;
            }
            Node<T> iter = head.next;
            while (iter.next != null) {

                iter = iter.next;
            }
            return iter.element;
        }

        @Override
        public T get(int index) {

            int COUNTER = 0;
            if (head == null) {
                throw new IndexOutOfBoundsException();
            }

            Node<T> iter = head;
            Node<T> iterSec = head.next;
            while (iterSec != null) {

                iterSec = iterSec.next;

                if (index == COUNTER) {
                    break;
                }
                iter = iter.next;
                COUNTER++;
            }
            if (index < 0 || index > COUNTER) {

                throw new IndexOutOfBoundsException();
            }

            return iter.element;
        }

        @Override
        public void clear() {

            head = null;
        }

        @Override
        public void addFirst(T element) {

            head = new Node<>(element, head);
        }

        @Override
        public void addLast(T element) {

            if (head == null) {

                addFirst(element);
                return;
            }
            Node<T> iter = head;
            while (iter.next != null) {

                iter = iter.next;
            }

            iter.next = new Node<>(element, null);
        }

        @Override
        public void removeFirst() {

            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            if (head.next == null) {
                head = null;
                return;
            }
            // head = new Node<>(null, head);

            head = head.next;
        }

        @Override
        public void removeLast() {

            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            if (head.next == null) {

                head = null;
                return;
            }

            Node<T> iter = head;
            while (iter.next.next != null) {

                iter = iter.next;
            }

            iter.next = null;

        }

        ////////////////////////////////////////////                                                 // // // // // // // // // // // // // // // // // //
        //                                        //                                                 //
        // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH // // // // // // // // // // // // // // // // // //
        //                                        //
        ////////////////////////////////////////////

        @Override
        public Iterator<T> iterator() {


            return new Iterator<>() {


                @Override
                public boolean hasNext() {

                    return head!=null;
                }

                @Override
                public T next() {

                    if (head == null) {
                        throw new NoSuchElementException();
                    }

                    Node<T> temp = head;
                    head = head.next;
                    return temp.element;

                }
            };
        }

        @Override
        public void setAt(int index, T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public void addAt(int index, T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public void removeAt(int index) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        private static class Node<T> {

            private T element;  // element typu T
            private Node<T> next; // następny obiekt klasy Node

            private Node(T element, Node<T> next) {
                this.element = element;
                this.next = next;
            }
        }
    }
}
