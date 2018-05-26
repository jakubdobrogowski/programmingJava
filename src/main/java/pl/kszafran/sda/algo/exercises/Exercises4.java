package pl.kszafran.sda.algo.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

        /**
         * Odwraca kolejność wszystkich elementów w liście
         */
        void reverse();

        /**
         * Odwraca kolejność wszystkich elementów w liście, ale tutaj uzyje tego ze
         * lista jest dwukierunkowa
         */
        void reverseWithUsingPrev();

        /**
         * Kopjuje listę
         * <p>
         * Uwaga: żadne instancje klasy Node nie mogą być współdzielone pomiędzy oryginalną listą i kopią.
         * Wartości T, czyli Node.element, mogą (a wręcz MUSZĄ) być współdzielone.
         */
        SdaList<T> copy();

        /**
         * Metoda która  która pozwala wstawić do środka listy dowolną ilość nowych elementów.
         * Rozwiązanie musi działać w czasie liniowym O(n), czyli innymi słowy nie wystarczy przejść pętlą
         * po “elements” i zawołać wersję addAt(..), która przyjmuje jeden element
         */

        void addAt(int index, T... elements);

    }

    private static class SdaLinkedList<T> implements SdaList<T> {

        private Node<T> head;
        private Node<T> before;
        // private Node<T> tail;  chyba tego nie powinno być

        public SdaLinkedList(T[] elements) {

//            for (int i = elements.length - 1; i >= 0; i--) {
//
//                head = new Node<>(elements[i], head);
//            }

            for (int i = elements.length - 1; i >= 0; i--) {
                addFirst(elements[i]);
            }

        }

        public SdaLinkedList() { // konsturktor domyślny tworzy pustą listę

            head = null;
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

/*            int COUNTER = 0;
            Node<T> iter = head;
            while (iter != null) {

                iter = iter.next;
                COUNTER++;
            }
            return COUNTER;*/

            Node<T> iter = head;
            int counter = 0;
            if (iter != null) {

                counter = counter + size(iter);
            }
            return counter;
        }

        private int size(Node<T> iter) {

            if (iter == null) {
                return 0;
            }
            return 1 + size(iter.next);
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
            Node<T> node = head.next;
            return getLast(node);
          /*  while (node.next != null) {

                node = node.next;
            }
            return node.element;*/

        }

        private T getLast(Node<T> node) {

            if (node.next == null) {

                return node.element;
            }
            return getLast(node.next);
        }

        @Override
        public T get(int index) {

            // int COUNTER = 0;
            int offset = 0;
            if (head == null) {
                throw new IndexOutOfBoundsException();
            }

            Node<T> node = head;
            return get(node, index, offset);

        /*    while (node.next != null) {

                if (index == COUNTER) {
                    break;
                }
                node = node.next;
                COUNTER++;
            }
            if (index < 0 || index > COUNTER) {

                throw new IndexOutOfBoundsException();
            }
            return node.element;*/

        }

        private T get(Node<T> node, int index, int offset) {

            if (node == null) {

                throw new IndexOutOfBoundsException();
            }
            if (offset == index) {

                return node.element;
            }
            return get(node.next, index, offset + 1);
        }


        @Override
        public void clear() {

            head = null;
        }

        @Override
        public void addFirst(T element) {

            Node<T> node = new Node<>(element, head, null);
            if (head != null) {
                head.prev = node;
            }
            head = node;

        }

        @Override
        public void addLast(T element) {

            if (head == null) {

                addFirst(element);
                return;
            }
            Node<T> node = head;
           /* while (node.next != null) {

                node = node.next;
            }

            node.next = new Node<>(element, null);*/


            //      Node<T> nodeLast = tail; //yiii
            //  addLast(node, nodeLast, element);  //tu teź
        }

        private void addLast(Node<T> node, Node<T> nodeLast, T element) {

            if (node.next == null) {

                node.prev = new Node<>(element, node, node.prev);
                node.next = new Node<>(element, null, node.prev);
                return;
            }
            addLast(node.next, nodeLast.prev, element);
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

            Node<T> node = head;
        /*    while (node.next.next != null) {

                node = node.next;
            }

            node.next = null;*/

            removeLast(node);

        }

        private void removeLast(Node<T> node) {

            if (node.next.next == null) {

                node.next = null;
                return;
            }
            removeLast(node.next);
        }

        ////////////////////////////////////////////                                                 // // // // // // // // // // // // // // // // // //
        //                                        //                                                 //
        // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH // // // // // // // // // // // // // // // // // //
        //                                        //
        ////////////////////////////////////////////

        @Override
        public Iterator<T> iterator() {
            return new Iterator<>() {

                private Node<T> node = head;

                private int counter = -1;
                private boolean canRemove = false;

                @Override
                public boolean hasNext() {

                    return node != null; // kumam
                }

                @Override
                public T next() {

                    if (hasNext()) {

                        counter++;
                        T element = node.element;
                        node = node.next;
                        canRemove = true;
                        return element;
                    }
                    throw new NoSuchElementException();
                }

                @Override
                public void remove() {

                    if (!canRemove) {
                        throw new IllegalStateException();
                    }
                    removeAt(counter);
                    canRemove = false;
                }
            };
        }

        @Override
        public void setAt(int index, T element) {

            int offset = 0;

            if (index < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (head == null) {
                throw new IndexOutOfBoundsException();
            }
            Node<T> node = head;
            /*for (int i = 0; i < index; i++) {
i
                if (node.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                node = node.next;
            }
            node.element = element;*/

            setAt(node, index, element, offset);
        }

        private void setAt(Node<T> node, int index, T element, int offset) {

            if (node == null) {

                throw new IndexOutOfBoundsException();
            }
            if (offset == index) {

                node.element = element;
                return;
            }
            setAt(node.next, index, element, offset + 1);
        }

        @Override
        public void addAt(int index, T element) {
            int offset = 0;
       /*     if (index < 0) {
                throw new IndexOutOfBoundsException();
            }

            if (index > size()) {

                throw new IndexOutOfBoundsException();
            }*/
            if (index == 0) {
                addFirst(element);
                return;
            }
            if (isEmpty()) {

                throw new IndexOutOfBoundsException();
            }
            Node<T> node = head;
            addAt(node, index, element, offset);
            /*for (int i = 0; i < index - 1; i++) {

                node = node.next;
            }
            node.next = new Node<T>(element, node.next);*/
        }

        private void addAt(Node<T> node, int index, T element, int offset) {

            if (node == null) {
                if (index == size()) {

                    addLast(element);
                    return;
                }
                throw new IndexOutOfBoundsException();
            }
            if (index - 1 == offset) {

                //   node.next = new Node<>(element, node.next);
                return;
            }
            addAt(node.next, index, element, offset + 1);
        }

        @Override
        public void removeAt(int index) {
            int offset = 0;
      /*      if (index < 0) {
                throw new IndexOutOfBoundsException();
            }

            if (index > size()) {

                throw new IndexOutOfBoundsException();
            }*/

            if (isEmpty()) {

                throw new IndexOutOfBoundsException();
            }

            if (index == 0) {
                head = head.next;
                return;
            }
            Node<T> node = head;


//            for (int i = 0; i < index - 1; i++) {
//
//                node = node.next;
//            }
//
//            node.next = node.next.next;

            removeAt(node, index, offset);
        }

        private void removeAt(Node<T> node, int index, int offset) {

            if (node.next == null) {

                throw new IndexOutOfBoundsException();
            }

            if (index - 1 == offset) {

                node.next = node.next.next;
                return;
            }

            removeAt(node.next, index, offset + 1);
        }

        @Override
        public void reverse() {

//            int size = size();
//            Node rep = null;
//
//            for (int i = 0; i < size; i++) {
//
//                Node<T> next = head.next; //2 //3 //4
//                head.next = rep; //null //1 //2 //tu się dzieje cała magia
//                rep = head; //1 //2 //3
//                head = next; //2 //3
//            }
//            head = rep;

            reverse(head.next, null);
        }

        private void reverse(Node<T> current, Node<T> previous) {

            if (current == null) {
                head = previous;
                return;
            }
            current = head.next;
            head.next = previous;
            previous = head;
            head = current;
            reverse(current, previous);
        }


//        @Override
//        public void reverseWithUsingPrev() {
//            int offset = 0;
//            Node<T> iter = head;
//
//            reverseWithUsingPrev(head, offset);
//        }
//
//        private void reverseWithUsingPrev(Node<T> head, int offset) {
//
//            if (size() % 2 == 0) {
//
//                if ((size() / 2) == offset) {
//
//                    return;
//                }
//            } else {
//
//                if ((size() / 2) == offset) {
//
//                    return;
//                }
//            }
//
//
//            swap(head, head.prev);
//            reverseWithUsingPrev(head.next, head.prev, offset + 1);
//        }
//
//        private void swap(Node<T> head, Node<T> tail) {
//
//            Node<T> temp = head;
//            head = tail;
//            tail = head;
//        }

        @Override
        public void reverseWithUsingPrev() {

            reverseWithUsingPrev(head);
        }

        // jaka listę najłatwiej odwrócić?
        // zawierającą jeden element.

        private void reverseWithUsingPrev(Node<T> iter) {

            if (iter == null) {

                return;
            }
            if (iter.prev == null) {

                Node<T> node = head;
                head = head.next;
                head.next = node;
            } else {

                Node<T> node = head.next;
                head.next = head.prev;
                head.prev = node;
            }
            reverseWithUsingPrev(iter.next);
        }

        @Override
        public SdaList<T> copy() {

            SdaList<T> list = new SdaLinkedList<>();
            Node<T> node = head;
            copy(node, list);
            return list;
        }

        private void copy(Node<T> node, SdaList<T> list) {
            if (node == null) {

                return;
            }
            list.addLast(node.element);
            copy(node.next, list);
        }

        @Override
        public void addAt(int index, T... elements) {

            int offset = 0;
            Node<T> node = head;
            addAt(node, offset, index, elements);


        }

        private void addAt(Node<T> node, int offset, int index, T[] elements) {

            if (node == null) {
                return;
            }
            if (offset == index) {

                for (T element : elements) {

                    //       node.next = new Node<>(element, node.next);
                }
                return;
            }
            addAt(node.next, offset + 1, index, elements);

        }

        private static class Node<T> {

            private T element;
            private Node<T> next;
            private Node<T> prev;

//            private Node(T element, Node<T> next) {
//                this.element = element;
//                this.next = next;
//
//            }

            private Node(T element, Node<T> next, Node<T> prev) {
                this.element = element;
                this.next = next;
                this.prev = prev;
            }
        }
    }
}
