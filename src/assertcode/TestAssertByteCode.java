package assertcode;

import java.util.Random;

public class TestAssertByteCode {

    public static void main(String[] args) {
//        assert1();
//        assert2();
        assert3();
    }

    /**
     * 测试断言assert Expression1;
     */
    private static void assert1() {
        int assert1 = 0;
        assert1++;
        assert assert1 == 0;
    }

    /**
     * 测试assert Expression1 : Expression2;
     * Expression2为普通值
     */
    private static void assert2() {
        int assert2 = new Random().nextInt();
        assert assert2 <= 0 : "assert2";
    }

    /**
     * 测试assert Expression1 : Expression2;
     * Expression2为方法调用
     */
    private static void assert3() {
        int assert3 = new Random().nextInt();
        assert assert3 <= 0 : getAssert3ErrorMsg();
    }

    private static int getAssert3ErrorMsg() {
        return 3;
    }
}

