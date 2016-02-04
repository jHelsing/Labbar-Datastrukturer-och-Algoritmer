public class Lab2a {

    public static double[] simplifyShape(double[] poly, int k) {
        // If shape does not need to be simplified
        if(k >= poly.length/2)
            return poly;
        double[] newShape = poly.clone();
        int newShapeNbrOfPoints = newShape.length/2;
        // Loops until we have removed enough points.
        while(newShapeNbrOfPoints > k){
            double minValue = 2000000;
            int minIndex=0;
            // Calculate all the values for the points, except the start and finish point.
            for(int i=2; i<newShape.length-3; i+=2) {
                double l1 = Math.sqrt((newShape[i]-newShape[i-2])*(newShape[i]-newShape[i-2]) + (newShape[i+1]-newShape[i-1])*(newShape[i+1]-newShape[i-1]));
                double l2 = Math.sqrt((newShape[i+2]-newShape[i])*(newShape[i+2]-newShape[i]) + (newShape[i+3]-newShape[i+1])*(newShape[i+3]-newShape[i+1]));
                double l3 = Math.sqrt((newShape[i+2]-newShape[i-2])*(newShape[i+2]-newShape[i-2]) + (newShape[i+3]-newShape[i-1])*(newShape[i+3]-newShape[i-1]));
                double result = l1+l2-l3;
                // If we have a 0 value we remove that point instantly. Otherwise we save the value if it is the lowest value.
                if((result) == 0) {
                    minIndex=i;
                    break;
                } else if(minValue > result) {
                    minIndex=i;
                    minValue=result;
                }
            }
            // Removes the smallest point and updates the number of points we have
            newShape = remove(newShape, minIndex);
            newShapeNbrOfPoints = newShape.length/2;
        }

        return newShape;
    }

    /**
     * Removes an element from an array and decreases it's size.
     * @param newShape The array to remove the 2 elements from
     * @param minIndex The index to remove
     * @return The new shorter array
     */
    private static double[] remove(double[] newShape, int minIndex) {
        double[] temp = new double[newShape.length-2];
        for(int i=0; i<minIndex; i++) {
            temp[i] = newShape[i];
        }
        for(int i=minIndex+2; i<newShape.length; i+=2) {
            temp[i-2] = newShape[i];
            temp[i-1] = newShape[i+1];
        }
        return temp.clone();
    }
}
