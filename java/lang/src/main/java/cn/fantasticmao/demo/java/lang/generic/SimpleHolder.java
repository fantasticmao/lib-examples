package cn.fantasticmao.demo.java.lang.generic;

/**
 * 使用 javap -c SimpleHolder
 *
 * @author fantasticmao
 * @since 2017.01.02
 */
public class SimpleHolder {
    private Object obj;

    public static void main(String[] args) {
        SimpleHolder holder = new SimpleHolder();
        holder.setObj("item");
        String s = (String) holder.getObj();
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
