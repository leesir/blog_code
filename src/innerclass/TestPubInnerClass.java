package innerclass;

public class TestPubInnerClass {
    private int privateValue = 0;
    private static int privateStaticValue = 0;
    public int publicValue = 1;
    public static int publicStaticValue = 3;

    public static void outPubStaticF() {

    }

    private static void outPriStaticF() {

    }

    public void outPubF() {

    }

    private void outPriF() {

    }

    public class PubInnerClass {
        public int f() {
            outPubStaticF();
            outPriStaticF();
            outPubF();
            outPriF();
            return privateValue + privateStaticValue + publicValue + publicStaticValue;
        }
    }

    public static void main(String[] args) {
        TestPubInnerClass testPubInnerClass = new TestPubInnerClass();
        PubInnerClass pubInnerClass = testPubInnerClass.new PubInnerClass();
    }
}
