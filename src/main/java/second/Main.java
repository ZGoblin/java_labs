package second;

import java.math.BigDecimal;
import common.Common;

public class Main {

    public static void main(String[] args) {

        Common.introduceMyself();

        double start = Common.inputDouble("Input start value");
        double end = Common.inputDouble(
                "Input end value",
                "End value must be bigger then start value",
                inValue -> inValue > start
        );
        double step = Common.inputDouble(
                "Input step ",
                "Step must be bigger then 0.0 and lower then end",
                inValue -> inValue > 0.0 && inValue < end
        );
        int n = Common.inputInt(
                "Input n",
                "n must be bigger then 0",
                inValue -> inValue > 0
        );
        double e = Common.inputDouble("Input e");

        for (double x = start; x <= end; x += step) {
            double y = CalcValue.calcExactValue(x);
            double sn = CalcValue.calcApproxValue(x, n);
            double se = sn - y;
            double en = CalcValue.calcApproxValue(x, e);
            double ee = en - y;

            Common.print("x = %f, y = %f, sn = %f, se = %f, en = %f, ee = %f", x, y, sn, se, en, ee);
        }

        Common.print("With BigDecimal below");

        for (BigDecimal x = BigDecimal.valueOf(start); x.compareTo(BigDecimal.valueOf(end)) < 0; x = x.add(BigDecimal.valueOf(step))) {
            BigDecimal y = CalcValue.calcExactValue(x);
            BigDecimal sn = CalcValue.calcApproxValue(x, n);
            BigDecimal se = sn.subtract(y);
            BigDecimal en = CalcValue.calcApproxValue(x, e);
            BigDecimal ee = en.subtract(y);

            Common.print("x = %f, y = %f, sn = %f, se = %f, en = %f, ee = %f", x, y, sn, se, en, ee);
        }
    }

}
