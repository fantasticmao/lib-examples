package priv.mm.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MatcherDemo
 * * \o:NUL字符 \u0000
 * \t:制表符 \u0009
 * \n:换行符 \u000A
 * \v:垂直制表符 \u000B
 * \f:换页符 \u000C
 * \r:回车符 \u000D
 * <p>
 * [...]:方括号内任意字符
 * [^...]:不在方括号内任意字符
 * .:除换行符和其他Unicode行终止符之外的任意字符
 * \w:任何ASCII字符单词、\W:任何非ASCII单词字符
 * \s:任何Unicode空白符\S:任何非Unicode空白符
 * \d:任何ASCII数字、\D:任何非ASCII数字
 * [\b]:退格
 * <p>
 * {n,m}:匹配至少n次，但不超过m次
 * {n,}:匹配至少n次
 * {n}:匹配n次
 * ?:匹配0次或1次
 * +:匹配1次或多次
 * *:匹配0次或多次
 * <p>
 * |:选择
 * (...):组合，将几个项组合为一个单元
 * (?:...):只组合，将组合项合为一个单元，但不记忆与该组相匹配的字符
 * \n:和第n个分组第一次匹配的字符相匹配
 * <p>
 * ^:匹配字符串的开头
 * $:匹配字符串的结尾
 * \b:匹配一个单词的边界、\B:匹配非单词的边界
 * (?=p):零宽正向先行断言，要求接下来的字符都与p匹配
 * (?!p):零宽负向先行断言，要求接下来的字符都不与p匹配
 * <p>
 * i:不区分大小写
 * g:执行全局匹配
 * m:多行匹配
 *
 * @author maomao
 * @since 2016.12.23
 */
public class MatcherDemo {
    private static void matches() {

    }

    private static void lookingAt() {

    }

    private static void findAndGroup() {
        String str = "abcdef,abcdef,abcdef,abcdef";
        Pattern pattern = Pattern.compile("b");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    private static void group() {

    }

    private static void split() {

    }

    private static void replace() {

    }

    private static void reset() {

    }

    public static void main(String[] args) {
        MatcherDemo.findAndGroup();
    }
}
