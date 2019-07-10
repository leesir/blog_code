package generic.boundandWildcard;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GenericWildcardTest {
    Comparable<?> comparable;

    public static void main(String[] args) {
        Collection<?> collection;
        System.out.println(sumOfList1(Arrays.asList(1, 2, 2.3, new BigDecimal("2"))));
        System.out.println(sumOfList1(Arrays.asList(1, 2, 3)));
        System.out.println(sumOfList1(Arrays.asList(1.1, 2.2, 2.3)));
        System.out.println(sumOfList2(Arrays.asList(1, 2, 2.3)));
        System.out.println(sumOfList2(Arrays.asList(1, 2, 2)));
        System.out.println(sumOfList2(Arrays.asList(1.1, 2.2, 2.3)));
    }

    public static void visitComparable(List<? extends Number> list) {
        for (Number ele : list) {
            System.out.println(ele);
        }
    }

    public static <T extends Number> void visitComparable2(List<T> list) {
        for (Number ele : list) {
            System.out.println(ele);
        }
    }

    public static double sumOfList1(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list) {
            s += n.doubleValue();
        }
        return s;
    }

    public static void sumOfListLower(List<? super Integer> list) {
        list.add(1);
        //Integer value = list.get(0);
    }

    public static <T extends Number> double sumOfList2(List<T> list) {
        double s = 0.0;
        for (Number n : list) {
            s += n.doubleValue();
        }
        return s;
    }


}
