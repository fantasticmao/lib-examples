package priv.mm.base.generic;

public class Generic<T extends Number> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
