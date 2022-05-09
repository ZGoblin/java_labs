package first;

import common.Common;

public class Main {

    private static final double gradesInDegree = 1.11;

    public static void main(String[] args) {

        Common.introduceMyself();

        double x = 122 + 20 + 12;
        double y = convertToRadians(x);
        double z = convertToGrades(x);

        Common.print("X = %f degrees", x);
        Common.print("Y = %f (%f degrees in radians)", y, x);
        Common.print("Z = %f (%f degrees in grades)", z, x);
    }

    public static double convertToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public static double convertToGrades(double degrees) {
        return degrees * gradesInDegree;
    }

}
