package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Common {

    public static void introduceMyself() {
        System.out.println("------------");
        System.out.println("122-20ck-1");
        System.out.println("Zaiets O.S.");
        System.out.println("Number 12");
        System.out.println("------------");
    }

    public static void print(String text, Object ...variables ) {
        System.out.printf((text) + "%n", variables);
    }

    public static String input(String introduceText, String errorText, Function<String, Boolean> predicate) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                print(introduceText);
                String rawValue = reader.readLine();
                if (predicate.apply(rawValue)) {
                    return rawValue;
                } else {
                    print(errorText);
                }
            } catch (Exception e) {
                print(e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    public static double inputDouble(String introduceText, String errorText, Function<Double, Boolean> predicate) {
        String inputValue = input(
                introduceText,
                errorText,
                rawValue -> {
                    Double value = Double.parseDouble(rawValue);
                    return predicate.apply(value);
                }
        );
        return Double.parseDouble(inputValue);
    }

    public static double inputDouble(String introduceText) {
        String inputValue = input(
                introduceText,
                "",
                rawValue -> {
                    // Need to check cast
                    Double.parseDouble(rawValue);
                    return true;
                }
        );
        return Double.parseDouble(inputValue);
    }

    public static int inputInt(String introduceText, String errorText, Function<Integer, Boolean> predicate) {
        String inputValue = input(
                introduceText,
                errorText,
                rawValue -> {
                    Integer value = Integer.parseInt(rawValue);
                    return predicate.apply(value);
                }
        );
        return Integer.parseInt(inputValue);
    }

    public static String input(String introduceText) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            print(introduceText);
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

}
