package lab4;

import java.util.Comparator;

/**
 * @author Jonathan and Amar
 * @version 0.1
 */
public class CompDijkstraPath<E extends DirectedGraph.DijkstraObject> implements Comparator<E>  {

    @Override
    public int compare(E o1, E o2) {
        if (o1.getWeight() > o2.getWeight())
            return 1;
        else if(o1.getWeight() < o2.getWeight())
            return -1;
        else
            return 0;
    }

}
