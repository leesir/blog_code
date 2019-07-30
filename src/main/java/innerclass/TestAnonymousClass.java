package innerclass;

public class TestAnonymousClass {
    public int publicValue = 0;
    public static int publicStaticValue = 0;
    private int privateValue = 0;
    private static int privateStaticValue = 0;

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread");
            System.out.println(privateValue);
            System.out.println(privateStaticValue);
            System.out.println(publicValue);
            System.out.println(publicStaticValue);
        }
    });

    private static Thread staticThread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("staticThread");
            System.out.println(privateStaticValue);
            System.out.println(publicStaticValue);
        }
    });

    public static void main(String[] args) {
        final int localN = 0;
        Thread localThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("localThread");
                System.out.println(localN);
                System.out.println(privateStaticValue);
                System.out.println(publicStaticValue);
            }
        });


    }

    private void outF(final int arg1) {
        InnerClass innerClass = new InnerClass() {

            @Override
            void innerF() {
                System.out.println(arg1);
                System.out.println(privateValue);
                System.out.println(privateStaticValue);
                System.out.println(publicValue);
                System.out.println(publicStaticValue);
                outF();
            }
        };
    }

    private void outFJDK1_8version(final int arg1) {
        InnerClass innerClass = new InnerClass() {

            @Override
            void innerF() {
                System.out.println(arg1);
                System.out.println(privateValue);
                System.out.println(privateStaticValue);
                System.out.println(publicValue);
                System.out.println(publicStaticValue);
                outF();
            }
        };
    }

    private void outF() {

    }

    class InnerClass {
        void innerF() {

        }
    }
}
