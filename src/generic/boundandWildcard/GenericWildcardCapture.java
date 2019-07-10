package generic.boundandWildcard;

import java.util.List;

/**
 * @author lijing
 */
public class GenericWildcardCapture {

//    public static void setFalse(List<?> target) {
//        target.set(0, target.get(0));
//    }

    public static void set(List<?> target) {
        setHelper(target);
    }

    private static <T> void setHelper(List<T> target) {
        target.set(0, target.get(0));
    }
}
