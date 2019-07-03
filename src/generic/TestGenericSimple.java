package generic;

public class TestGenericSimple<T> {
    private T data;

    public TestGenericSimple(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
