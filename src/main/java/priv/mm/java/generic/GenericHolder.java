package priv.mm.java.generic;

/**
 * 使用 javap -c GenericHolder
 *
 * @author maomao
 * @since 2017.01.02
 */
public class GenericHolder<T> {
    private T obj;

    public static void main(String[] args) {
        GenericHolder<String> holder = new GenericHolder<>();
        holder.setObj("item");
        String s = holder.getObj();
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
