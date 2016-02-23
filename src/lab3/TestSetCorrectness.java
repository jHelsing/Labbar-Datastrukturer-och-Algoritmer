package lab3;

import java.util.HashSet;
import java.util.Random;

/**
 * @author Jonathan and Amar
 * @version 0.1
 */
public class TestSetCorrectness {

    public static void main(String[] args) {
        // Get all the arguments
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int n3 = Integer.parseInt(args[2]);
        int n4 = Integer.parseInt(args[3]);

        int resetCount = 0;
        while(resetCount < n2) {
            System.out.println("\n=============== NEW SET ===============\n");
            SimpleSet<Integer> testSet = null;
            HashSet<Integer> referenceSet = new HashSet<>();

            if(n1==1) {
                // Create SortedLinkedListSet
                testSet = new SortedLinkedListSet<Integer>();
            } else if(n1==2) {
                // Crete SplayTree
                testSet = new SplayTreeSet<Integer>();
            } else {
                // Default
                testSet = new SortedLinkedListSet<Integer>();
            }

            int operationCount = 0;
            String operationsDone = "";
            while (operationCount < n3) {
                Random r = new Random();
                int randomOperation = r.nextInt(100);

                if(0 <= randomOperation && randomOperation < 25) {
                    // Test size method
                    operationsDone = operationsDone + "size() ";
                    if(testSet.size() != referenceSet.size()) {
                        // If a inconsistency is found
                        System.out.println("BUG in size");
                        System.exit(1);
                    }
                } else if(25 <= randomOperation && randomOperation < 50) {
                    // Test add method
                    Integer integer = r.nextInt(n4); // A random integer in the interval [0, n4)
                    operationsDone = operationsDone + "add(" + integer.toString() + ") ";
                    if(testSet.add(integer) != referenceSet.add(integer)) {
                        // If a inconsistency is found
                        System.out.println("BUG in add");
                        System.out.println("Operation Flow: " + operationsDone);
                        System.exit(1);
                    }
                } else if(50 <= randomOperation && randomOperation < 75) {
                    // Test remove method
                    Integer integer = r.nextInt(n4); // A random integer in the interval [0, n4)
                    operationsDone = operationsDone + "remove(" + integer.toString() + ") ";
                    if(testSet.remove(integer) != referenceSet.remove(integer)) {
                        // If a inconsistency is found
                        System.out.println("BUG in remove");
                        System.out.println("Operation Flow: " + operationsDone);
                        System.exit(1);
                    }
                } else {
                    // Test contains method
                    Integer integer = r.nextInt(n4); // A random integer in the interval [0, n4)
                    operationsDone = operationsDone + "contains(" + integer.toString() + ") ";
                    if(testSet.contains(integer) != referenceSet.contains(integer)) {
                        // If a inconsistency is found
                        System.out.println("BUG in contains");
                        System.out.println("Operation Flow: " + operationsDone);
                        System.exit(1);
                    }
                }
                operationCount++;
            }
            System.out.println(operationsDone);
            resetCount++;
        }
        System.out.println("\nTest completed and no inconsistencies where found!");
        System.exit(0);
    }
}
