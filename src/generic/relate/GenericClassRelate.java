package generic.relate;

import java.math.BigDecimal;
import java.util.*;

public class GenericClassRelate {

    public static void main(String[] args) {
        Object obj = "";
        funcArg(1);
        List<Object> list = new ArrayList<>();
        list.add(0.5);

    }

    public static void funcArg(Object param) {

    }

    public static void array() {
        Number[] integerArray = new Integer[]{1, 2, 3};
        Number[] bigDecimalArray = new BigDecimal[]{new BigDecimal("")};
        Number[] numberArray = {1, 0.5, new BigDecimal("")};
    }

    public static void generic() {
        List<Number> numberList = new ArrayList<>();
        //compile fail
        //numberList = new ArrayList<Integer>();
    }

    public static void generic1() {
        Iterable<String> iterable = new ArrayList<>();
    }

    interface PayloadList<E,P> extends List<E> {
        void setPayload(int index, P val);
    }
}
