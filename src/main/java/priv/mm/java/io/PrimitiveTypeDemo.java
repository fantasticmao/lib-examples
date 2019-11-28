package priv.mm.java.io;

import com.google.gson.internal.Primitives;
import org.junit.Test;

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
 * @author maodh
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

    /**
     * 使用 <code>xxd -b ${file}</code>查看二进制文件
     */
    @Test
    public void test() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<Class, Object> map = new HashMap<>();
        map.put(boolean.class, true);
        map.put(char.class, (int) 'a');
        map.put(byte.class, 1);
        map.put(short.class, 1);
        map.put(int.class, 1);
        map.put(long.class, 1L);
        map.put(float.class, 1.0F);
        map.put(double.class, 1.0D);

        for (Map.Entry<Class, Object> entry : map.entrySet()) {
            final Class key = entry.getKey();
            final Object val = entry.getValue();

            final String fileName = PrimitiveTypeDemo.class.getResource("").getPath() + key;
            File file = new File(fileName);
            try (FileOutputStream fileOut = new FileOutputStream(file, false);
                 DataOutputStream dataOut = new DataOutputStream(fileOut)) {
                final String methodName = "write" + key.getSimpleName().substring(0, 1).toUpperCase() + key.getSimpleName().substring(1);
                final Method method = dataOut.getClass().getMethod(methodName, Primitives.unwrap(val.getClass()));
                method.invoke(dataOut, val);
            }
        }
    }
}
