import java.util.Scanner;

public class SimpleCalculator {
    private Double storedValue = null;
    private Double currentInput = null;
    private String pendingOp = null;

    public static void main(String[] args) {
        new SimpleCalculator().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Simple calculator. Enter numbers or operators (+ - * / =) each on its own line. Type 'exit' to quit.");
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            if (isOperator(line)) {
                handleOperator(line);
            } else {
                handleNumber(line);
            }
        }
        sc.close();
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("=");
    }

    private void handleNumber(String s) {
        try {
            double num = Double.parseDouble(s);
            currentInput = num;
            if (storedValue == null && pendingOp == null) {
                storedValue = currentInput;
                currentInput = null;
            }
            if (pendingOp != null) {
                System.out.println("Display: " + format(currentInput));
            } else {
                System.out.println("Display: " + format(storedValue));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input.");
        }
    }

    private void handleOperator(String op) {
        if (storedValue == null && currentInput == null) {
            System.out.println("Display: 0.0");
            return;
        }
        if (op.equals("=")) {
            if (pendingOp != null && currentInput != null) {
                storedValue = apply(storedValue, pendingOp, currentInput);
                pendingOp = null;
                currentInput = null;
            }
            System.out.println("Display: " + format(storedValue));
        } else {
            if (pendingOp != null && currentInput != null) {
                storedValue = apply(storedValue, pendingOp, currentInput);
                currentInput = null;
            } else if (pendingOp == null && currentInput != null) {
                storedValue = currentInput;
                currentInput = null;
            }
            pendingOp = op;
            System.out.println("Display: " + format(storedValue));
        }
    }

    private double apply(double a, String op, double b) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    System.out.println("Error: Division by zero. Result set to Infinity.");
                    return Double.POSITIVE_INFINITY;
                }
                return a / b;
        }
        return b;
    }

    private String format(Double d) {
        if (d == null) return "0.0";
        if (d == Math.floor(d)) return String.format("%.0f", d);
        else return d.toString();
    }
}
