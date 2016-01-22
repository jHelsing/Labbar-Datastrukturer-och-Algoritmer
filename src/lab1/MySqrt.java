package lab1;

/**
 * @author Jonathan and Amar
 * @version 1.0
 */
public class MySqrt {

    /**
     * Finds the approximation to the squareroot of x with binary search.
     *
     * @param x The number to find the squareroot of.
     * @param epsilon The margin of error. How close to the actual value the approximation should be.
     * @return An approximation of the squareroot of x.
     */
    public static double mySqrtLoop(double x, double epsilon) {
        if(x < 0) //No real solutions to sqrt(-something).
            return Double.NaN;

        //Decide the starting interval
        double min;
        double max;
        if(x<1) {
            // If x is less than 1 we search for the approximation between x and 1.
            min = x;
            max = 1;
        } else {
            //x>=1
            // If x is more or equal to 1 we search for the approximation between 1 and x.
            min = 1;
            max = x;
        }

        double y = (max+min)/2; //Decide the new mid-point of the interval [min,max]

        //Loops until we find an enough exact result
        while(!(Math.abs((y*y)-x) < epsilon)) {
            if((y*y) > x) {
                // If the result from squaring y is bigger than x, we probably need to look for a smaller y-value.
                max = y;
            } else if ((y*y) < x) {
                // If the result from squaring y is smaller than x, we probably need to look for a bigger y-value.
                min = y;
            }
            y = (max+min)/2;
        }
        return y;
    }

    /**
     * Approximates the squareroot of x with the margin of error epsilon. This is done recursive with the help of a
     * helper method.
     *
     * @param x The number to find the squareroot of.
     * @param epsilon The margin of error. How close to the actual value the approximation should be.
     * @return An approximation of the squareroot of x.
     */
    public static double mySqrtRecurse(double x, double epsilon) {
        if(x < 0) //No real solutions to sqrt(-something).
            return Double.NaN;

        //Decide the starting interval
        double min;
        double max;
        if(x<1) {
            // If x is less than 1 we search for the approximation between x and 1.
            min = x;
            max = 1;
        } else {
            //x>=1
            // If x is more or equal to 1 we search for the approximation between 1 and x.
            min = 1;
            max = x;
        }
        return mySqrtRecurseHelper(x, epsilon, min, max);
    }

    /**
     * Helper method for mySqrtRecurse. This is the actual recursive method.
     *
     * @param x The number to find the squareroot of.
     * @param epsilon The margin of error. How close to the actual value the approximation should be.
     * @param min The current minimum value of the interval.
     * @param max The current max value of the interval.
     * @return The approximation of the squareroot.
     */
    private static double mySqrtRecurseHelper(double x, double epsilon, double min, double max) {
        double y = (max+min)/2;
        if((Math.abs((y*y)-x)) < epsilon) {
            // if we have achieved a enough exact approximation, we return this approximation.
            return y;
        }

        if((y*y) > x) {
            // If the result from squaring y is bigger than x, we probably need to look for a smaller y-value.
            return mySqrtRecurseHelper(x,epsilon,min,y);
        } else if ((y*y) < x) {
            // If the result from squaring y is less than x, we probably need to look for a bigger y-value.
            return mySqrtRecurseHelper(x,epsilon,y,max);
        }
        // Some kind of error during calculation
        return Double.NaN;
    }

    public static void main(String[] args) {
        System.out.println("Test of mySqrtLoop:");
        System.out.println("Svar: " + mySqrtLoop(100, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(0, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(-20, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(0.25, 0.000001));
        System.out.println("Svar: " + mySqrtLoop(1, 0.000001));

        System.out.println();

        System.out.println("Test of mySqrtRecurse:");
        System.out.println("Svar: " + mySqrtRecurse(100, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(0, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(-20, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(0.25, 0.000001));
        System.out.println("Svar: " + mySqrtRecurse(1, 0.000001));
    }

}
