package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises6;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.singletonList;

public class Solutions6 extends Exercises6 {

    @Override
    public <T> List<T> traversePreOrder(SdaTree<T> tree) {
        List<T> visited = new ArrayList<>();
        preOrder(tree, visited::add);
        return visited;
    }

    private <T> void preOrder(SdaTree<T> tree, Consumer<T> visitor) {
        visitor.accept(tree.getValue());
        tree.getLeftChild().ifPresent(left -> preOrder(left, visitor));
        tree.getRightChild().ifPresent(right -> preOrder(right, visitor));
    }


    @Override

    public <T> List<T> traverseInOrder(SdaTree<T> tree) {
        List<T> visited = new ArrayList<>();
        inOrder(tree, visited::add);
        return visited;
    }

    private <T> void inOrder(SdaTree<T> tree, Consumer<T> visitor) {
        tree.getLeftChild().ifPresent(left -> inOrder(left, visitor));
        visitor.accept(tree.getValue());
        tree.getRightChild().ifPresent(right -> inOrder(right, visitor));
    }

    @Override
    public <T> List<T> traversePostOrder(SdaTree<T> tree) {
        List<T> visited = new ArrayList<>();
        postOrder(tree, visited::add);
        return visited;
    }

    private <T> void postOrder(SdaTree<T> tree, Consumer<T> visitor) {
        tree.getLeftChild().ifPresent(left -> postOrder(left, visitor));
        tree.getRightChild().ifPresent(right -> postOrder(right, visitor));
        visitor.accept(tree.getValue());
    }

    @Override
    public <T> List<T> traversePreOrderIterative(SdaTree<T> tree) {
        List<T> visited = new ArrayList<>();
        preOrderIterative(tree, visited::add);
        return visited;
    }

    private <T> void preOrderIterative(SdaTree<T> tree, Consumer<T> visitor) {
        Deque<SdaTree<T>> toVisit = new ArrayDeque<>(singletonList(tree));
        while (!toVisit.isEmpty()) {
            SdaTree<T> node = toVisit.pop();
            visitor.accept(node.getValue());
            node.getRightChild().ifPresent(toVisit::push);
            node.getLeftChild().ifPresent(toVisit::push);
        }
    }

    @Override
    public <T> List<T> traverseLevelOrder(SdaTree<T> tree) {
        List<T> visited = new ArrayList<>();
        levelOrder(tree, visited::add);
        return visited;
    }

    private <T> void levelOrder(SdaTree<T> tree, Consumer<T> visitor) {
        Deque<SdaTree<T>> toVisit = new ArrayDeque<>(singletonList(tree));
        while (!toVisit.isEmpty()) {
            SdaTree<T> node = toVisit.poll();
            visitor.accept(node.getValue());
            node.getLeftChild().ifPresent(toVisit::offer);
            node.getRightChild().ifPresent(toVisit::offer);
        }
    }

    @Override
    public int countLeaves(SdaTree<?> tree) {
        if (isLeaf(tree)) {
            return 1;
        }
        return tree.getLeftChild().map(this::countLeaves).orElse(0) +
                tree.getRightChild().map(this::countLeaves).orElse(0);
    }

    private boolean isLeaf(SdaTree<?> tree) {
        return !tree.getRightChild().isPresent() && !tree.getLeftChild().isPresent();
    }

    @Override
    public SdaTree<String> buildTree1(String input) {
        String[][] values = Arrays.stream(input.split("\n"))
                .map(line -> line.split("\\s+"))
                .toArray(String[][]::new);
        validate(values);
        return buildTree1(values, 0, 0);
    }

    private void validate(String[][] values) {
        for (int i = 0; i < values.length; i++) {
            int expected = 1 << i;
            int actual = values[i][0].isEmpty() ? 0 : values[i].length;
            if (actual != expected) {
                throw new IllegalArgumentException("Expected " + expected + " values at depth " + i + ", got " + actual);
            }
        }
    }

    private <T> SdaTree<T> buildTree1(T[][] values, int depth, int offset) {
        if (depth == values.length || "-".equals(values[depth][offset])) {
            return null;
        }
        return SdaTree.of(values[depth][offset],
                buildTree1(values, depth + 1, offset * 2),
                buildTree1(values, depth + 1, offset * 2 + 1));
    }

    @Override
    public SdaTree<String> buildTree2(String input) {
        Pattern pattern = Pattern.compile("(left|right)\\((.+)\\)=(.+)");

        Children<String> children = new Children<>();
        for (String line : input.split("\n")) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid line: " + line);
            }
            children.add(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        return children.buildTree();
    }

    private static class Children<T> {

        private final Map<T, T> parentToLeft = new HashMap<>();
        private final Map<T, T> parentToRight = new HashMap<>();

        public void add(String side, T parent, T child) {
            ("left".equals(side) ? parentToLeft : parentToRight).put(parent, child);
        }

        public SdaTree<T> buildTree() {
            return buildTree(findRoot());
        }

        private SdaTree<T> buildTree(T parent) {
            return parent == null ? null : SdaTree.of(
                    parent,
                    buildTree(parentToLeft.get(parent)),
                    buildTree(parentToRight.get(parent)));
        }

        private T findRoot() {
            Set<T> roots = new HashSet<>();
            roots.addAll(parentToLeft.keySet());
            roots.addAll(parentToRight.keySet());
            roots.removeAll(parentToLeft.values());
            roots.removeAll(parentToRight.values());
            if (roots.size() != 1) {
                throw new IllegalArgumentException("Expected exactly one root, got " + roots.size());
            }
            return roots.iterator().next();
        }
    }

    @Override
    public int calcHeight(SdaTree<?> tree) {
        return Math.max(
                tree.getLeftChild().map(left -> 1 + calcHeight(left)).orElse(0),
                tree.getRightChild().map(right -> 1 + calcHeight(right)).orElse(0));
    }

    // Alternatywne rozwiązanie:

    public int calcHeight2(SdaTree<?> tree) {
        // Rozpisujemy wszystkie możliwe przypadki:
        if (tree.getLeftChild().isPresent() && tree.getRightChild().isPresent()) {
            return Math.max(
                    1 + calcHeight(tree.getLeftChild().get()),
                    1 + calcHeight(tree.getRightChild().get()));
        } else if (tree.getLeftChild().isPresent()) {
            return 1 + calcHeight(tree.getLeftChild().get());
        } else if (tree.getRightChild().isPresent()) {
            return 1 + calcHeight(tree.getRightChild().get());
        } else {
            return 0;
        }

        /*
        Zauważ, że takie rozwiązanie jest dość niezgrabne.
        Byłoby ono natomiast bardziej naturalne, gdyby metody
        getLeftChild() i getRightChild() zwracały null zamiast Optional:

        if (tree.getLeftChild() != null && tree.getRightChild() != null) {
            return Math.max(
                    1 + calcHeight(tree.getLeftChild()),
                    1 + calcHeight(tree.getRightChild()));
        } else if (tree.getLeftChild() != null) {
            return 1 + calcHeight(tree.getLeftChild());
        } else if (tree.getRightChild() != null) {
            return 1 + calcHeight(tree.getRightChild());
        } else {
            return 0;
        }

        Jednak zamiast rozpisywać wszystkie możliwe przypadki i duplikować kod,
        możemy po prostu przyjąć wartość domyślną 0 dla nullowego dziecka:

        return Math.max(
                tree.getLeftChild() != null ? 1 + calcHeight(tree.getLeftChild()) : 0,
                tree.getRightChild() != null ? 1 + calcHeight(tree.getRightChild()) : 0)

        Wróćmy teraz do wersji getLeftChild() i getRightChild(), które zwracają Optional:

        return Math.max(
                tree.getLeftChild().isPresent() ? 1 + calcHeight(tree.getLeftChild().get()) : 0,
                tree.getRightChild().isPresent() ? 1 + calcHeight(tree.getRightChild().get()) : 0)

        Następnie, zamiast używać isPresent() oraz get(), możemy skorzystać z metody map():

        return Math.max(
                tree.getLeftChild().map(left -> 1 + calcHeight(left)).orElse(0),
                tree.getRightChild().map(right -> 1 + calcHeight(right)).orElse(0));

        W ten sposób otrzymujemy oryginalne rozwiązanie :).
        */
    }

    @Override
    public <T> T findMax(SdaTree<T> tree, Comparator<T> comparator) {
        return traversePreOrder(tree).stream().max(comparator).get();
    }

    // Alternatywne rozwiązanie:

    public <T> T findMax2(SdaTree<T> tree, Comparator<T> comparator) {
        T max = tree.getValue();
        if (tree.getLeftChild().isPresent()) {
            max = maxT(max, findMax2(tree.getLeftChild().get(), comparator), comparator);
        }
        if (tree.getRightChild().isPresent()) {
            max = maxT(max, findMax2(tree.getRightChild().get(), comparator), comparator);
        }
        return max;

        // Ponownie, takie rozwiazanie byłoby bardziej naturalne,
        // gdyby getLeftChild() i getRightChild() zwracały null.
    }

    private <T> T maxT(T t1, T t2, Comparator<T> comparator) {
        return comparator.compare(t1, t2) > 0 ? t1 : t2;
    }
}
