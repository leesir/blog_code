package innerclass;

public class TestPriStaticInnerClass {
    private static int priStaticValue = 3;
    public static int pubStaticValue = 3;

    private static void priFOut() {

    }

    public static void pubFOut() {

    }

    private static class PriStaticInnerClass {
        private int fPubStaticInnerClass() {
            priFOut();
            pubFOut();
            return priStaticValue + pubStaticValue;
        }
    }

    public static void main(String[] args) {
        PriStaticInnerClass priStaticInnerClass = new PriStaticInnerClass();
    }
}
