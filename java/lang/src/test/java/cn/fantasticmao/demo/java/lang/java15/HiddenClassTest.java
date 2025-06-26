package cn.fantasticmao.demo.java.lang.java15;

import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * HiddenClassTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class HiddenClassTest {

    @Test
    public void hiddenClass() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String classData = "yv66vgAAAEEAEQoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCAAIAQASaGVsbG8gZmFudGFzdGljbWFvBwAKAQA5Y24vZmFudGFzdGljbWFvL2RlbW8vamF2YS9sYW5nL2phdmExNS9KRVAzNzFIaWRkZW5DbGFzc2VzAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEABWhlbGxvAQAUKClMamF2YS9sYW5nL1N0cmluZzsBAApTb3VyY2VGaWxlAQAYSkVQMzcxSGlkZGVuQ2xhc3Nlcy5qYXZhACEACQACAAAAAAACAAEABQAGAAEACwAAAB0AAQABAAAABSq3AAGxAAAAAQAMAAAABgABAAAAAwAJAA0ADgABAAsAAAAbAAEAAAAAAAMSB7AAAAABAAwAAAAGAAEAAAAGAAEADwAAAAIAEA==";
        byte[] classBytes = Base64.getDecoder().decode(classData);

        Class<?> proxy = MethodHandles.lookup()
            .defineHiddenClass(classBytes, true)
            .lookupClass();

        System.out.println("Name: " + proxy.getName());
        for (Method method : proxy.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());
        }

        Method method = proxy.getDeclaredMethod("hello");
        Object result = method.invoke(null);
        System.out.println("Result: " + result);
    }
}
