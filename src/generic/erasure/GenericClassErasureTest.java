package generic.erasure;

/**
 * 泛型类擦除测试代码
 *
 * @author lijing
 */
public class GenericClassErasureTest {

    public static void main(String[] args) {
        Node<String> node = new Node<>("tail", null);
        System.out.println(node.getData());
    }

    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() { return data; }
    }
}
