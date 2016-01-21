package lab1;

/**
 * @author Jonathan and Amar
 * @version 0.5
 */
public class MySqrt {

    public static double mySqrtLoop(double x, double epsilon) {
        if(x<0)
            return Double.NaN;
        if(x<1) {
            double xOld = x;
            double y = (1-x)/2.0;
            double yOld;
            while(!(Math.abs((y*y)-x) < epsilon)) {
                yOld = y;
                if(y*y > x) {
                    y = (yOld - xOld) / 2.0;
                } else if(y*y < x) {
                    xOld = y;
                    y = (1-yOld) / 2.0;
                }
            }
            return y;
        }
        return Double.NaN;

    }

    public static double mySqrtRecurse(double x, double epsilon) {
        return Double.NaN;
    }

    public static void main(String[] args) {
        System.out.println("Svar: " + mySqrtLoop(0.25, 0.1));
    }

}
