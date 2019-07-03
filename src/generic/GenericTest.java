package generic;

public class GenericTest {

    public static void main(String[] args) {
        //完整写法
        TestGenericSimple<String> test1A =  new TestGenericSimple<String>("test1A");
        String testAValue = test1A.getData();
        //JDK 1.7之后的类型推导写法
        TestGenericSimple<String> test1B = new TestGenericSimple<>("test1B");
        String testBValue = test1A.getData();
        //raw type写法
        @SuppressWarnings("unchecked")
        TestGenericSimple test1C = new TestGenericSimple("test1C");
        String testCValue = (String) test1C.getData();
    }
}
