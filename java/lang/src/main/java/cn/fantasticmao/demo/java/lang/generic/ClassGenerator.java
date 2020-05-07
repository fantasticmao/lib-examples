package cn.fantasticmao.demo.java.lang.generic;

/**
 * 擦除丢失了`泛型参数`的确切信息
 * {@code new T()}、{@code arg instanceof T} 都无法通过编译。
 *
 * @author maomao
 * @since 2017.1.2
 */
public class ClassGenerator {
    /**
     * 传入类型信息 {@link Class<T>} 对象来弥补上述缺陷
     * {@code t.newInstance()} 无法验证 T 是否具有默认（无参）构造器。
     * 可以自定义显示的工厂对象，作为 {@link Class<T>} 的变种，使对象的创建接受编译期检查。(代码太多...详情见《Java编程思想》P382)
     */
    private static <T> T generate(Class<T> t) {
        try {
            return t.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(ClassGenerator.generate(Object.class));
        System.out.println(ClassGenerator.generate(Integer.class));
    }
}
