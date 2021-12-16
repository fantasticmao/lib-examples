package cn.fantasticmao.demo.java.lang.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * PrimitiveTypeDemo
 *
 * @author fantasticmao
 * @see DataOutputStream#writeBoolean(boolean)
 * @see DataOutputStream#writeChar(int)
 * @see DataOutputStream#writeByte(int)
 * @see DataOutputStream#writeShort(int)
 * @see DataOutputStream#writeInt(int)
 * @see DataOutputStream#writeLong(long)
 * @see DataOutputStream#writeFloat(float)
 * @see DataOutputStream#writeDouble(double)
 * @since 04/02/2018
 */
public class PrimitiveTypeDemo {

    static Map<Class<?>, Class<?>> map = new HashMap<>();

    static {
        map.put(Boolean.class, boolean.class);
        map.put(Character.class, int.class);
        map.put(Byte.class, int.class);
        map.put(Short.class, int.class);
        map.put(Integer.class, int.class);
        map.put(Long.class, long.class);
        map.put(Float.class, float.class);
        map.put(Double.class, double.class);
    }

    /**
     * 使用 <code>xxd -b ${file}</code>查看二进制文件
     */
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<Class<?>, Object> data = new HashMap<>();
        data.put(boolean.class, true);
        data.put(char.class, (int) 'a');
        data.put(byte.class, 1);
        data.put(short.class, 1);
        data.put(int.class, 1);
        data.put(long.class, 1L);
        data.put(float.class, 1.0F);
        data.put(double.class, 1.0D);

        for (Map.Entry<Class<?>, Object> entry : data.entrySet()) {
            final Class<?> key = entry.getKey();
            final Object val = entry.getValue();

            final String fileName = PrimitiveTypeDemo.class.getResource("/").getPath() + key;
            File file = new File(fileName);
            try (FileOutputStream fileOut = new FileOutputStream(file, false);
                 DataOutputStream dataOut = new DataOutputStream(fileOut)) {
                final String methodName = "write" + key.getSimpleName().substring(0, 1).toUpperCase() + key.getSimpleName().substring(1);
                final Method method = dataOut.getClass().getMethod(methodName, map.get(val.getClass()));
                method.invoke(dataOut, val);
            }
        }
    }
}
