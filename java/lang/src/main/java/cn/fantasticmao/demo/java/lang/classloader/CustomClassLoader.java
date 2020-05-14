package cn.fantasticmao.demo.java.lang.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * CustomClassLoader
 *
 * @author maomao
 * @since 2020-05-14
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String className = name.substring(name.lastIndexOf(".") + 1) + ".class";
        try (InputStream in = this.getClass().getResourceAsStream(className)) {
            if (in == null) {
                return super.loadClass(name);
            } else {
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                return defineClass(name, bytes, 0, bytes.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.loadClass(name);
    }
}
