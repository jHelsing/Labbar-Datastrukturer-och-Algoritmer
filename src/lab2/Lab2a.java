

public class Lab2a {

    public static double[] simplifyShape(double[] poly, int k) {
        if(k >= poly.length/2)
            return poly;
        double[] newShape = poly.clone();
        int newShapeNbrOfPoints = newShape.length/2;
        while(newShapeNbrOfPoints > k){
            double minValue = 2000000;
            int minIndex=0;
            for(int i=2; i<newShape.length-2; i+=2) {
                double l1 = Math.sqrt((newShape[i]-newShape[i-2])*(newShape[i]-newShape[i-2]) + (newShape[i+1]-newShape[i-1])*(newShape[i+1]-newShape[i-1]));
                double l2 = Math.sqrt((newShape[i+2]-newShape[i])*(newShape[i+2]-newShape[i]) + (newShape[i+3]-newShape[i+1])*(newShape[i+3]-newShape[i+1]));
                double l3 = Math.sqrt((newShape[i+2]-newShape[i-2])*(newShape[i+2]-newShape[i-2]) + (newShape[i+3]-newShape[i-1])*(newShape[i+3]-newShape[i-1]));
                double result = l1+l2-l3;
                if((result) == 0) {
                    minIndex=i;
                    break;
                } else if(minValue > result) {
                    minIndex=i;
                    minValue=result;
                }
            }
            newShape = remove(newShape, minIndex);
            newShapeNbrOfPoints = newShape.length/2;
        }
        for(int i=0; i<newShape.length; i+=2)
            System.out.println("X:" + newShape[i] + " Y:" + newShape[i+1]);
        return newShape;
    }

    private static double[] remove(double[] newShape, int minIndex) {
        double[] temp = new double[newShape.length-2];
        for(int i=0; i<minIndex; i++) {
            temp[i] = newShape[i];
        }
        for(int i=minIndex+2; i<newShape.length; i++) {
            temp[i-2] = newShape[i];
        }

        return temp.clone();
    }
}
