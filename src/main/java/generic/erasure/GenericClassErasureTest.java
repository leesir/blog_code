package generic.erasure;

import java.util.List;

/**
 * 泛型类擦除测试代码
 *
 * @author lijing
 */
public class GenericClassErasureTest {

    public static void main(String[] args) {
        Node<String> node = new Node<>("tail", null);
        System.out.println(node.getData());
        UpperBoundTypeClass<String> boundTypeClass = new UpperBoundTypeClass<>("NodeSubClass");
        System.out.println(boundTypeClass.getData());
    }

    public static <T> void f(List<? super Node> n) {

    }

    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }

    public static class UpperBoundTypeClass<T extends String> {
        private T data;

        public UpperBoundTypeClass(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}
