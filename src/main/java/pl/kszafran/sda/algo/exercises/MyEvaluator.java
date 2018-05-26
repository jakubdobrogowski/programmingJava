package pl.kszafran.sda.algo.exercises;

import java.util.*;

public class MyEvaluator {

    public int evaluate(String expression) {

        return evaluatePostfix(convertToPostfix(expression));
    }

    private int evaluatePostfix(List<Object> list) {

        ArrayDeque<Object> stack = new ArrayDeque<>();
        for (Object element : list) {

            if (isNumber(element.toString())) {

                stack.push(element);
            } else if (isOperator(element.toString())) {

                mathematiclaOperations(stack, element);

            } else if (isFuncion(element.toString())) {

                funkcionOpreactions(stack, element);

            } else if (isLeftParen(element.toString())) {

                throw new IllegalArgumentException();
            } else if (isRightParen(element.toString())) {

                throw new IllegalArgumentException();
            }
        }
        if (stack.size() != 1) {

            throw new IllegalArgumentException();
        }
        return Integer.valueOf(stack.pop().toString());
    }

    private void funkcionOpreactions(ArrayDeque<Object> stack, Object element) {
        stack.push(Funcion.byName(element.toString())
                .apply(Integer.valueOf(stack.pop().toString()),
                        Integer.valueOf(stack.pop().toString())));
    }

    private void mathematiclaOperations(ArrayDeque<Object> stack, Object element) {

        if (stack.size() < 2) {
            throw new IllegalArgumentException();
        }
        stack.push(Operator.bySymbol(element.toString())
                .apply(Integer.valueOf(stack.pop().toString()),
                        Integer.valueOf(stack.pop().toString())));
    }

    private List<Object> convertToPostfix(String expression) {
        Deque<Object> output = new ArrayDeque<>();
        Deque<Object> stack = new ArrayDeque<>();

        for (String token : expression.split("\\s+")) {

            if (isNumber(token)) {

                handleNumber(Integer.valueOf(token), output);
            } else if (isFuncion(token)) {

                handleFuncion(token, stack);
            } else if (isSeparator(token)) {

                handleSeparator(stack, output);
            } else if (isOperator(token)) {

                handleOperator(token, output, stack);
            } else if (isLeftParen(token)) {

                handleLeftParen(token, stack);
            } else if (isRightParen(token)) {

                handleRightParen(stack, output);
            } else if (isSthElse(token)) {

                throw new IllegalArgumentException();
            }
        }
        while (!stack.isEmpty()) {

            output.offer(stack.pop());
        }
        return new ArrayList<>(output);
    }

    private boolean isSthElse(String token) {

        return token.matches(".*");
    }

    private void handleSeparator(Deque<Object> stack, Deque<Object> output) {

        while (!isLeftParen(stack.peek().toString())) {

            output.offer(stack.pop());

            if (stack.isEmpty()) {

                throw new IllegalArgumentException();
            }
        }

    }

    private boolean isSeparator(String token) {

        return Punctuation.SEPARATOR.matches(token);
    }

    private void handleFuncion(String token, Deque<Object> stack) {

        stack.push(token);
    }

    private boolean isFuncion(String token) {

        return Funcion.byName(token) != null;
    }

    private void handleRightParen(Deque<Object> stack, Deque<Object> output) {

        while (!isLeftParen(stack.peek().toString())) {

            output.offer(stack.pop());

            if (stack.isEmpty()) {

                throw new IllegalArgumentException();
            }
        }
        stack.pop();
        if (!stack.isEmpty()) {
            if (isFuncion(stack.peek().toString())) {

                output.offer(stack.pop());

            }
        }
    }

    private boolean isRightParen(String token) {

        return Punctuation.RIGHT_PAREN.matches(token);
    }

    private void handleLeftParen(String token, Deque<Object> stack) {

        stack.push(token);
    }

    private boolean isLeftParen(String token) {

        return Punctuation.LEFT_PAREN.matches(token);
    }

    private void handleOperator(String token, Deque<Object> output, Deque<Object> stack) {

        if (!stack.isEmpty()) {

            if (maps.get(stack.peek()) != null) {

                while (maps.get(stack.peek()) >= maps.get(token)) {

                    output.offer(stack.pop());
                    if (stack.isEmpty()) {
                        break;
                    }
                }
            }
        }
        stack.push(token);
    }

    private boolean isOperator(String token) {

        return Operator.bySymbol(token) != null;
    }


    private void handleNumber(Integer token, Deque<Object> output) {

        output.offer(token);
    }

    private boolean isNumber(String token) {

        return token.matches("-?\\d+");
    }

    private static Map<String, Integer> maps = Map.of(

            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    private enum Funcion {

        MIN("min", Math::min),
        MAX("max", Math::max);


        private String name;

        private FuncionWithTwoArgs function;

        Funcion(String name, FuncionWithTwoArgs function) {
            this.name = name;
            this.function = function;
        }

        public String getName() {
            return name;
        }

        public FuncionWithTwoArgs getFunction() {
            return function;
        }

        public static FuncionWithTwoArgs byName(String token) {
            return BY_NAME.get(token);
        }

        private static final Map<String, FuncionWithTwoArgs> BY_NAME = Map.of(
                Funcion.MAX.getName(), Funcion.MAX.getFunction(),
                Funcion.MIN.getName(), Funcion.MIN.getFunction()

        );
    }

    public interface FuncionWithTwoArgs {

        int apply(int a, int b);
    }

    private enum Punctuation {

        LEFT_PAREN("("),
        RIGHT_PAREN(")"),
        SEPARATOR(",");

        private final String symbol;

        Punctuation(String symbol) {
            this.symbol = symbol;
        }

        private boolean matches(String token) {

            return symbol.equals(token);
        }
    }

    private enum Operator {

        PLUS("+", (a, b) -> b + a),

        MINUS("-", (a, b) -> b - a),

        TIMES("*", (a, b) -> a * b),

        DIVIDED("/", (a, b) -> b / a);

        private String name;
        private FuncionWithTwoArgs operation;

        Operator(String name, FuncionWithTwoArgs operation) {
            this.name = name;
            this.operation = operation;
        }

        public String getName() {
            return name;
        }

        public FuncionWithTwoArgs getOperation() {
            return operation;
        }

        public static FuncionWithTwoArgs bySymbol(String token) {

            return BY_SYMBOL.get(token);
        }

        private static final Map<String, FuncionWithTwoArgs> BY_SYMBOL = Map.of(

                Operator.PLUS.getName(), Operator.PLUS.getOperation(),
                Operator.MINUS.getName(), Operator.MINUS.getOperation(),
                Operator.TIMES.getName(), Operator.TIMES.getOperation(),
                Operator.DIVIDED.getName(), Operator.DIVIDED.getOperation()
        );
    }
}
