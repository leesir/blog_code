package generic.boundandWildcard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class GenericBoundTest {

    public static void main(String [] args) {
        //compile success
        Integer number1 = 0;
        BigDecimal number2 = new BigDecimal("2");
        computeNumber(number1, number2);
        iter(new ArrayList<>(Arrays.asList("1", "2", "a")));
        iter(new HashSet<>(Arrays.asList("1", "2", "a")));

        //compile fail
//        computeNumber("string1", number2);

        computeCloneableNumber(new CloneableInteger(1));
        visitCAndSObject(new CAndS(){});

    }

    public static <T extends Number> void computeNumber(T number1, T number2) {
        //compute
    }

    public static <T extends Collection> void iter(T collection) {
        //iterate
        if (!collection.isEmpty()) {
            for (Object ele : collection) {
                System.out.println(ele);
            }
        }
    }

    public static <T extends Number & Cloneable> void computeCloneableNumber(T number) {
        System.out.println(number.intValue());
    }

    public static <T extends Serializable & Cloneable> void visitCAndSObject(T object) {
        System.out.println(object);
    }

    public static interface CAndS extends Cloneable, Serializable {

    }

    public static class CloneableInteger extends Number implements Cloneable {
        private Integer data;

        public CloneableInteger(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        @Override
        public int intValue() {
            return data;
        }

        @Override
        public long longValue() {
            return data;
        }

        @Override
        public float floatValue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public double doubleValue() {
            throw new UnsupportedOperationException();
        }
    }
}
