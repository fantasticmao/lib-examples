package cn.fantasticmao.demo.java.lang.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * ListForEachTest
 *
 * @author fantasticmao
 * @since 2020-11-14
 */
@Slf4j
public class ListForEachTest {

    /**
     * 在使用 {@code for (int i = 0; i < list.size(); i++)} 的方式遍历 List 过程中，删除元素的操作会导致 for() 遍历的元素是不完整的。
     * <p>
     * 例如，待遍历的 List 中的元素为 [1, 2, 3, 4, 5]，在遍历过程中删除元素 [2] 时 i = 1，在元素 [2] 被删除之后，
     * List 中的元素为 [1, 3, 4, 5]。此时 i = 2 对应的元素为 [4]，即该 for() 已经跳过了元素 [3]。
     */
    @Test
    public void forEach1Error() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer target = 2;
        for (int i = 0; i < list.size(); i++) {
            Integer e = list.get(i);
            log.info("element: {}", e);

            if (e.equals(target)) {
                list.remove(target);
            }
        }
    }

    /**
     * 在使用 foreach 语法或者 List.forEach() 的方式遍历 List 过程中，删除元素的操作会导致程序抛出一个异常。
     *
     * @see #forEach3Error()
     */
    @Test(expected = ConcurrentModificationException.class)
    public void forEach2Error() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer target = 2;
        for (Integer e : list) {
            if (e.equals(target)) {
                list.remove(target);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void forEach3Error() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer target = 2;
        list.forEach(e -> {
            if (e.equals(target)) {
                list.remove(target);
            }
        });
    }

    /**
     * 在遍历 List 时删除元素的正确写法：倒叙遍历 List
     */
    @Test
    public void forEach1Success() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer target = 2;
        for (int i = list.size() - 1; i >= 0; i--) {
            Integer e = list.get(i);
            log.info("element: {}", e);

            if (e.equals(target)) {
                list.remove(target);
            }
        }
    }

    /**
     * 在遍历 List 时删除元素的正确写法：在删除元素之后立即 break 当前循环
     */
    @Test
    public void forEach2Success() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer target = 2;
        for (Integer e : list) {
            if (e.equals(target)) {
                list.remove(target);
                break;
            }
        }
    }

    /**
     * 在遍历 List 时删除元素的正确写法：使用 Iterator
     */
    @Test
    public void forEach3Success() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer target = 2;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equals(target)) {
                it.remove();
            }
        }
    }
}
