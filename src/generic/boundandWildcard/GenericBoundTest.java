package generic.boundandWildcard;

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
        computeNumber("string1", number2);
    }

    public static <T extends Number> void computeNumber(T number1, T number2) {
        //compute
    }

    public static <T extends Collection> void iter(T collection) {
        //iterate
        for (Object ele : collection) {
            System.out.println(ele);
        }
    }
}
