package innerclass;

public class TestPubStaticInnerClass {
    private static int priStaticValue = 3;
    public static int pubStaticValue = 3;

    private static void priFOut() {

    }

    public static void pubFOut() {

    }

    public static class PubStaticInnerClass {
        private int fPubStaticInnerClass() {
            priFOut();
            pubFOut();
            return priStaticValue + pubStaticValue;
        }

        public static void f2() {

        }
    }

    public static void main(String[] args) {
        PubStaticInnerClass pubStaticInnerClass = new PubStaticInnerClass();
    }
}
