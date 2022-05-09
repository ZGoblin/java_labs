package second;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalcValue {

    public static double calcExactValue(double x) {
        return -0.5 * Math.log(1 - 2 * x * Math.cos(Math.PI / 3) + Math.pow(x, 2));
    }

    public static double calcApproxValue(double x, int n) {
        double S = 0d;

        for (int i = 1; i <= n; i++) {
            S += calcS(x, i);
        }

        return S;
    }

    public static double calcApproxValue(double x, double e) {
        double S = 0d;
        int step = 1;

        do {
            double previousValue = S;

            S += calcS(x, step++);

            if (previousValue - S <= e && previousValue != 0) {
                break;
            }
        } while (true);

        return S;
    }

    public static double calcS(double x, int n) {
        return (Math.pow(x, n) * Math.cos(n * (Math.PI / 3))) / n;
    }

    public static BigDecimal calcExactValue(BigDecimal x) {
        return BigDecimal.valueOf(-0.5).multiply(BigDecimal.valueOf(Math.log(1 - 2 * x.doubleValue() * Math.cos(Math.PI / 3) + Math.pow(x.doubleValue(), 2))));
    }

    public static BigDecimal calcApproxValue(BigDecimal x, int n) {
        BigDecimal S = BigDecimal.valueOf(0d);

        for (int i = 1; i <= n; i++) {
            S = S.add(calcS(x, i));
        }

        return S;
    }

    public static BigDecimal calcApproxValue(BigDecimal x, double e) {
        BigDecimal S = BigDecimal.valueOf(0d);
        int step = 1;

        do {
            BigDecimal previousValue = S;

            S = S.add(calcS(x, step++));

            if (previousValue.subtract(S).compareTo(BigDecimal.valueOf(e)) < 0 && previousValue.compareTo(BigDecimal.valueOf(0)) != 0) {
                break;
            }
        } while (true);

        return S;
    }

    public static BigDecimal calcS(BigDecimal x, int n) {
        return x.pow(n).multiply(BigDecimal.valueOf(Math.cos(n * (Math.PI / 3)))).divide(BigDecimal.valueOf(n), MathContext.DECIMAL128);
    }

}
