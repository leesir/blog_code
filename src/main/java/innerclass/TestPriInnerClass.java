package innerclass;

public class TestPriInnerClass {
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

    private class PriInnerClass {
        public int f() {
            outPubStaticF();
            outPriStaticF();
            outPubF();
            outPriF();
            return privateValue + privateStaticValue + publicValue + publicStaticValue;
        }
    }

    public static void main(String[] args) {
        TestPriInnerClass testPubInnerClass = new TestPriInnerClass();
        PriInnerClass priInnerClass = testPubInnerClass.new PriInnerClass();
    }
}
