package conditionalcompile;

public class TestCC {
    private static final boolean flag = false;

    public static void main(String[] args) {
        if (flag) {
            System.out.println("print false");
        }
    }
}
