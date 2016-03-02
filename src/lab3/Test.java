package lab3;

/**
 * @author Jonathan
 * @version 0.1
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("SortedLinkedListSet");
        int[] sortedN = {3,45,133,256,371,444,506,701,882,986};
        for(int i=0; i<sortedN.length; i++) {
            System.out.println("========TEST SORTEDLINKEDLISTSET n=" + sortedN[i] + "========");
            String[] arr = {"1", sortedN[i] + ""};
            TestSetSpeed.main(arr);
            System.out.println();
        }
        System.out.println();
        System.out.println();

        System.out.println("SplayTreeSet");
        int[] N = {145,500,1337,1532,2133,3178,4567,5432,7486,8431,9999};
        for(int i=0; i<N.length; i++) {
            System.out.println("========TEST SPLAYTREESET n=" + N[i] + "========");
            String[] arr = {"2", N[i] + ""};
            TestSetSpeed.main(arr);
            System.out.println();
        }
    }
}
