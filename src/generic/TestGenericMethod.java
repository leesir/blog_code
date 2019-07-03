package generic;

/**
 * 泛型方法测试代码
 *
 * @author lijing
 */
public class TestGenericMethod {

    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public <T> boolean compare2(T object1, T object2) {
        return object1.equals(object2);
    }

    public <T, K, V> boolean compare3(T object1, Pair<K, V> p1) {
        return object1.equals(p1);
    }

    public static class Pair<K, V> {

        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        //1. 完整写法
        boolean compareResult = TestGenericMethod.<String, Integer>compare(new Pair<String, Integer>("key1", 1), new Pair<String, Integer>("key2", 2));
        boolean compare3Result = new TestGenericMethod().<Object, String, Integer>compare3(new Object(), new Pair<String, Integer>("key3", 3));

        //2. 类型推导写法
        boolean compareResult1 = TestGenericMethod.compare(new Pair<>("key1", 1), new Pair<>("key2", 2));
        boolean compare3Result1 = new TestGenericMethod().compare3(new Object(), new Pair<>("key3", 3));
    }
}
