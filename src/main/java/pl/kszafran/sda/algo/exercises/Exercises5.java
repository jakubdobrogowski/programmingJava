package pl.kszafran.sda.algo.exercises;

import java.util.*;

public class Exercises5 {

    /**
     * Funkcja przyjmuje ciąg znaków zawierający nawiasy okrągłe: "(", ")",
     * a następnie zwraca true jeśli nawiasy są prawidłowo zagnieżdżone.
     * <p>
     * Inne znaki są ignorowane.
     * <p>
     * Np. dla "(())" zwraca true, ale dla "())(" zwraca false.
     */
    public boolean balancedParens(String input) {

        Deque<Character> stosNawiasow = new ArrayDeque<>();

        for (char character : input.toCharArray()) {

            if (character == '(') {

                stosNawiasow.push(character);
            }
            if (character == ')') {

                if (stosNawiasow.isEmpty()) {

                    return false;
                }
                stosNawiasow.pop();
            }
        }
        return stosNawiasow.isEmpty();
    }

    /**
     * Funkcja działa analogicznie do balancedParens, ale sprawdza także
     * poprawne zagnieżdzenie nawiasów kwadratowych "[", "]" oraz klamrowych "{", "}".
     * <p>
     * Np. dla "[(){}]" zwraca true, ale dla "[(])" zwraca false.
     */

    public boolean balancedAnyParens(String input) {

        Deque<Character> stosNawiasow = new ArrayDeque<>();

        for (char character : input.toCharArray()) {

            if (character == '(' || character == '{' || character == '[') {

                stosNawiasow.push(character);
            }
            if (character == ')') {

                if (stosNawiasow.isEmpty()) {

                    return false;
                }
                if (stosNawiasow.pop() != '(') {

                    return false;
                }

            } else if (character == ']') {

                if (stosNawiasow.isEmpty()) {

                    return false;
                }
                if (stosNawiasow.pop() != '[') {

                    return false;
                }

            } else if (character == '}') {

                if (stosNawiasow.isEmpty()) {

                    return false;
                }
                if (stosNawiasow.pop() != '{') {

                    return false;
                }
            }
        }
        return stosNawiasow.isEmpty();

    }

    /**
     * Funkcja odwraca podaną kolejkę.
     * <p>
     * Uwaga: wolno używać jedynie metod poll(), offer(), peek() i isEmpty()
     * oraz nie wolno tworzyć innych kolekcji.
     */
    public <T> void reverseQueue(Deque<T> queue) {

        if (!queue.isEmpty()) {

            T poll = queue.poll();
            reverseQueue(queue);
            queue.offer(poll);
        }
    }


    /**
     * Tworzy nową kolejkę o stałej pojemności 'capacity', zawierającą podane elementy.
     * <p>
     * Uwaga: kolejkę należy zaimplementować w oparciu o bufor cykliczny.
     * Opis do znalezienia na Wikipedii: https://pl.wikipedia.org/wiki/Bufor_cykliczny
     * <p>
     * Uwaga: najłatwiej będzie implementować metody w takiej kolejności jak są zadeklarowane.
     * <p>
     * Podpowiedź: są dwa podstawowe sposoby na zaimplementowanie bufora cyklicznego:
     * - przechowywać dwa wskaźniki: na początek i na konieć kolejki
     * - przechowywać wskaźnik na początek kolejki oraz ilość elementów (wg mnie prostszy sposób)
     * Dla zainteresowanych tematem: https://www.snellman.net/blog/archive/2016-12-13-ring-buffers/
     *
     * @throws IllegalArgumentException jeśli ilość elementów przekracza pojemność
     */
    public <T> SdaQueue<T> createQueue(int capacity, T... elements) {
        return new SdaCircularBuffer<>(capacity, elements);
    }

    public interface SdaQueue<T> {

        /**
         * Zwraca true jeśli kolejka jest pusta.
         */
        boolean isEmpty();

        /**
         * Zwraca true jeśli kolejka jest pełna.
         */
        boolean isFull();

        /**
         * Zwraca rozmiar listy (ilość elementów).
         */
        int size();

        /**
         * Zwraca pierwszy element kolejki bez usuwania go.
         *
         * @throws NoSuchElementException jeśli kolejka jest pusta
         */
        T peek();

        /**
         * Usuwa i zwraca pierwszy element kolejki.
         *
         * @throws NoSuchElementException jeśli kolejka jest pusta
         */
        T dequeue();

        /**
         * Dodaje nowy element na końcu kolejki.
         *
         * @throws IllegalStateException jeśli kolejka jest pełna
         */
        void enqueue(T element);
    }

    private static class SdaCircularBuffer<T> implements SdaQueue<T> {

        private T[] array;
        private int size;
        private int counter;

        public SdaCircularBuffer(int capacity, T[] elements) {

            if (elements.length > capacity) {

                throw new IllegalArgumentException();
            }
            this.array = Arrays.copyOf(elements, capacity);
            this.size = elements.length;

        }


        @Override
        public boolean isEmpty() {

            return size == 0;
        }

        @Override
        public boolean isFull() {

            return array.length == size;
        }

        @Override
        public int size() {

            return size;
        }

        @Override
        public T peek() {

            if (isEmpty()) {

                throw new NoSuchElementException();
            }
            return array[counter];
        }

        @Override
        public T dequeue() {

            if (isEmpty()) {

                throw new NoSuchElementException();
            }
            T t = array[counter];
            array[counter] = null;
            counter = (counter + 1) % array.length;
            size--;
            return t;


        }

        @Override
        public void enqueue(T element) {

            if (isFull()) {

                throw new IllegalStateException();
            }
            array[(counter + size) % array.length] = element;
            size++;
        }
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Funkcja odwraca podany stos.
     * <p>
     * Uwaga: wolno używać jedynie metod pop(), push(), peek() i isEmpty()
     * oraz nie wolno tworzyć innych kolekcji.
     * <p>
     * Podpowiedź: pytać o podpowiedzi :)
     */

    public <T> void reverseStack(Deque<T> stack) {

        if (stack.isEmpty()) {
//            T top = stack.pop();
//            reverseStack(stack);
//            pushToBottom(stack, top);
            return;
        }
        T top = stack.pop();
        reverseStack(stack);
        pushToBottom(stack, top);
    }

    private <T> void pushToBottom(Deque<T> stack, T bottom) {
        if (stack.isEmpty()) {
            stack.push(bottom);
        } else {

            T top = stack.pop();
            pushToBottom(stack, bottom);
            stack.push(top);
        }
        //     return;  //mogłoby być
    }

    /**
     * Funkcja oblicza podane wyrażenie, np: dla "2 * 3 + 7 * 8" zwraca 62.
     * <p>
     * Dla ułatwienia przyjmij, że wszystkie symbole (tzn. liczby, operatory, nawiasy) są rozdzielone spacjami:
     * "( 2 + 6 ) * 10 / 8" jest poprawnym wyrażeniem
     * "(2 + 6) * 10 / 8" jest niepoprawnym wyrażeniem
     * <p>
     * Uwaga: całą logikę najlepiej jest zaimplementować w osobnej klasie, np. Evaluator.
     * <p>
     * Podpowiedź: ewaluacja odbywa się w dwóch krokach:
     * 1. należy użyć algorytmu shunting-yard aby przekształcić wyrażenie do notacji postfiksowej,
     * czyli tzn. Odwrotnej Notacji Polskiej (Reverse Polish Notation)
     * 2. należy obliczyć wyrażenie zapisane w notacji postfiksowej
     * <p>
     * Oba algorytmy są opisane na Wikipedii: https://pl.wikipedia.org/wiki/Odwrotna_notacja_polska
     * <p>
     * W wersji podstawowej należy zaimplementować jedynie operatory +, -, * oraz /.
     * <p>
     * W wersji bardziej zaawansowanej należy zaimplementować dodatkowo dwuargumentowe funkcje min, max, np.:
     * "4 * min ( 30 , max ( 10 , 50 ) )" zwraca 120
     * <p>
     * Podpowiedź: wszystkie wymagane operatory (+, -, *, /) są lewostronnie łaczne.
     *
     * @throws IllegalArgumentException jeśli wyrażenie jest niepoprawne
     */
    public int evaluate(String expression) {

        MyEvaluator myEvaluator = new MyEvaluator();
        return myEvaluator.evaluate(expression);
    }

    public void showMeEvaluate(String expression){

        MyEvaluator myEvaluator = new MyEvaluator();
        System.out.println(myEvaluator.evaluate(expression));
    }
}
