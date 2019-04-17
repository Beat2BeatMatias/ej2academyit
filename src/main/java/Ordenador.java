import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Ordenador<T> {

    public static <T extends Comparable<T>> T[] ordenarArray(T[] agencias) {
        Arrays.sort(agencias);
        return agencias;
    }

}
