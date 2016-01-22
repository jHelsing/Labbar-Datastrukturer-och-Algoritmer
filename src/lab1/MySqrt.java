package lab1;

/**
 * @author Jonathan and Amar
 * @version 0.5
 */
public class MySqrt {
    /**
     * Finds the approximation to the squareroot of x with binary search.
     *
     * @param x The number to find the squareroot of.
     * @param epsilon The margin of error. How close the approximation should be.
     * @return An approximation of the squareroot of x.
     */
    public static double mySqrtLoop(double x, double epsilon) {
        if(x < 0)
            return Double.NaN;

        double min;
        double max;
        if(x<1) {
            min = x;
            max = 1;
        } else {
            //x>=1
            min = 1;
            max = x;
        }
        double y = (max+min)/2;

        while(!(Math.abs((y*y)-x) < epsilon)) {
            if((y*y) > x) {
                max = y;
            } else if ((y*y) < x) {
                min = y;
            }
            y = (max+min)/2;
        }
        return y;

    }

    public static double mySqrtRecurse(double x, double epsilon) {
        return Double.NaN;
    }

    public static void main(String[] args) {
        System.out.println("Test of mySqrtLoop:");
        System.out.println("Svar: " + mySqrtLoop(100, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(0, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(-20, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(0.25, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(1, 0.000001));

        System.out.println("Test of mySqrtRecurse:");
        System.out.println("Svar: " + mySqrtRecurse(100, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(0, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(-20, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(0.25, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(1, 0.000001));
    }

}
