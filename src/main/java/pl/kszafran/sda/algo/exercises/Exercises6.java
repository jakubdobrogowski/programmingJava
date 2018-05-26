package pl.kszafran.sda.algo.exercises;

import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * Zaimplementuj poniższe metody operujące na drzewie binarnym.
 */
public class Exercises6 {

    /**
     * Przechodzi podane drzewo w kolejności pre-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem rekurencji.
     */
    public <T> List<T> traversePreOrder(SdaTree<T> tree) {

        List<T> list = new ArrayList<>();
        preOrder(tree, list);
        return list;
    }

    private <T> void preOrder(SdaTree<T> tree, List<T> list) {

        list.add(tree.getValue());

        tree.getLeftChild().ifPresent(child -> preOrder(child, list));
        tree.getRightChild().ifPresent(child -> preOrder(child, list));

//        if (tree.getLeftChild().isPresent()) {
//
//            preOrder(tree.getLeftChild().get(), list);
//
//        }
//        if (tree.getRightChild().isPresent()) {
//
//            preOrder(tree.getRightChild().get(), list);
//        }

    }


    /**
     * Przechodzi podane drzewo w kolejności in-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem rekurencji.
     */
    public <T> List<T> traverseInOrder(SdaTree<T> tree) {
        List<T> list = new ArrayList<>();
        inOrder(tree, list);
        return list;

    }

    private <T> void inOrder(SdaTree<T> tree, List<T> list) {

        tree.getLeftChild().ifPresent(child -> inOrder(child, list));
        list.add(tree.getValue());
        tree.getRightChild().ifPresent(child -> inOrder(child, list));
    }

    /**
     * Przechodzi podane drzewo w kolejności post-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem rekurencji.
     */
    public <T> List<T> traversePostOrder(SdaTree<T> tree) {

        List<T> list = new ArrayList<>();
        postOrder(tree, list);
        return list;

    }

    private <T> void postOrder(SdaTree<T> tree, List<T> list) {

        tree.getLeftChild().ifPresent(child -> postOrder(child, list));
        tree.getRightChild().ifPresent(child -> postOrder(child, list));
        list.add(tree.getValue());
    }

    /**
     * Funkcja działa tak samo jak traversePreOrder.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem stosu (bez rekurencji).
     */
    public <T> List<T> traversePreOrderIterative(SdaTree<T> tree) {

        Deque<SdaTree<T>> stack = new ArrayDeque<>();
        stack.push(tree);
        List<T> list = new ArrayList<>();
        while (!stack.isEmpty()) {

            SdaTree<T> pop = stack.pop();
            list.add(pop.getValue());
            pop.getRightChild().ifPresent(stack::push);
            pop.getLeftChild().ifPresent(stack::push);

        }


//        list.add(tree.getValue());
//        tree.getRightChild().ifPresent(stack::push);
//        tree.getLeftChild().ifPresent(stack::push);
//
//        while (!stack.isEmpty()) {
//
//            if (tree.getRightChild().isPresent()) {
//
//                tree = tree.getRightChild().get();
//                tree.getRightChild().ifPresent(stack::push);
//            }
//
//             list.add(stack.pop().getValue());
//
//            if (tree.getLeftChild().isPresent()) {
//
//                tree = tree.getLeftChild().get();
//                tree.getLeftChild().ifPresent(stack::push);
//            }
//
//            list.add(stack.pop().getValue());
//        }

        return list;
    }

    /**
     * Przechodzi podane drzewo w kolejności level-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Podpowiedź: implementacja jest bardzo podobna do traversePreOrderIterative,
     * ale zamiast stosu wykorzystuje inną strukturę danych.
     */
    public <T> List<T> traverseLevelOrder(SdaTree<T> tree) {

        Deque<SdaTree<T>> queue = new ArrayDeque<>();
        queue.offer(tree);
        List<T> list = new ArrayList<>();
        while (!queue.isEmpty()) {

            SdaTree<T> poll = queue.poll();
            list.add(poll.getValue());
            poll.getLeftChild().ifPresent(queue::offer);
            poll.getRightChild().ifPresent(queue::offer);

        }
        return list;
    }

    /**
     * Funkcja zwraca liczbę liści w podanym drzewie.
     */
    public int countLeaves(SdaTree<?> tree) {


//        tree.getRightChild().ifPresent(e -> countLeaves(e));
//        tree.getLeftChild().ifPresent(e -> countLeaves(e));

        //int counter = 1;

        return count(tree);
    }

    private int count(SdaTree<?> tree) {
        int counter = isLeaf(tree) ? 1 : 0;

        if (tree.getRightChild().isPresent()) {

            counter += count(tree.getRightChild().get());
        }
        if (tree.getLeftChild().isPresent()) {

            counter += count(tree.getLeftChild().get());
        }
        return counter;
    }

    private boolean isLeaf(SdaTree<?> tree) {
        return !tree.getRightChild().isPresent() && !tree.getLeftChild().isPresent();
    }

    /**
     * Tworzy drzewo binarne na podstawie podanego tekstu.
     * <p>
     * Tekst zawiera tyle linijek, ile poziomów ma drzewo.
     * Każda linijka zawiera wartości węzłów na odpowiednim poziomie rozdzielone spacjami, po kolei,
     * czyli każda linijka zawiera dwa razy więcej wartości niż poprzednia.
     * Wartość "-" oznacza, że węzeł na danej pozycji nie istnieje.
     * <p>
     * Np. drzewo ze slajdów przedstawione byłoby jako "F\nB G\nA D - I\n- - C E - - H -",
     * czyli zapisując w wielu liniach:
     * F
     * B G
     * A D - I
     * - - C E - - H -
     * <p>
     * Uwaga: nie należy modyfikować klas SdaTree i SdaTreeImpl.
     *
     * @throws IllegalArgumentException jeśli któraś z linijek zawiera nieprawidłową ilość wartości
     */
    public SdaTree<String> buildTree1(String input) {

        String[][] array = Arrays.stream(input.split("\\n"))
                .map(e -> e.split(" "))
                .toArray(String[][]::new);

        buildTree1(array, 0, 0);

        throw new UnsupportedOperationException("Not implemented yet");

    }

    private void buildTree1(String[][] array, int deeth, int offset) {

        throw new UnsupportedOperationException("Not implemented yet");


    }

    ////////////////////////////////////////////
    //                                        //
    //           Z PRACY DOMOWEJ              //
    //                                        //
    ////////////////////////////////////////////

    public <T> List<T> filtrUsingPredicate(SdaTree<T> tree, Predicate<T> predicate) {

        List<T> list = new ArrayList<>();
        inOrder(tree, list);
        return list.stream().filter(predicate).collect(toList());
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Tworzy drzewo binarne na podstawie podanego tekstu.
     * <p>
     * Każda linijka zawiera informację o parze rodzic-dziecko.
     * Format każdej linijki wygląda następująco:
     * <p>
     * left(rodzic)=dziecko
     * <p>
     * lub
     * <p>
     * right(rodzic)=dziecko
     * <p>
     * dla lewego i prawego dziecka odpowiednio.
     * <p>
     * Uwaga: nie należy modyfikować klas SdaTree i SdaTreeImpl.
     *
     * @throws IllegalArgumentException jeśli któraś z linijek jest niezgodna z powyższym formatem
     */
    public SdaTree<String> buildTree2(String input) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja oblicza wysokość drzewa.
     * <p>
     * Przypomnienie: wysokość drzewa składającego się jedynie z korzenia to 0.
     */
    public int calcHeight(SdaTree<?> tree) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja znajduje największy element w drzewie.
     */
    public <T> T findMax(SdaTree<T> tree, Comparator<T> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public interface SdaTree<T> {

        /**
         * Tworzy nowe drzewo z korzeniem o podanej wartości oraz danych poddrzewach.
         */
        static <T> SdaTree<T> of(T value, SdaTree<T> left, SdaTree<T> right) {
            return new SdaTreeImpl<>(value, left, right);
        }

        /**
         * Tworzy nowy liść drzewa (węzeł bez dzieci).
         */
        static <T> SdaTree<T> leaf(T value) {
            return new SdaTreeImpl<>(value, null, null);
        }

        /**
         * Zwraca wartość tego węzła.
         */
        T getValue();

        /**
         * Zwraca lewe poddrzewo.
         */
        Optional<SdaTree<T>> getLeftChild();

        /**
         * Zwraca prawe poddrzewo.
         */
        Optional<SdaTree<T>> getRightChild();
    }

    private static class SdaTreeImpl<T> implements SdaTree<T> {

        private final T value;
        private final SdaTree<T> left;
        private final SdaTree<T> right;

        private SdaTreeImpl(T value, SdaTree<T> left, SdaTree<T> right) {
            this.value = Objects.requireNonNull(value, "value must not be null");
            this.left = left;
            this.right = right;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public Optional<SdaTree<T>> getLeftChild() {
            return Optional.ofNullable(left);
        }

        @Override
        public Optional<SdaTree<T>> getRightChild() {
            return Optional.ofNullable(right);
        }
    }
}
