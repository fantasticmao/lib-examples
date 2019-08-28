package priv.mm.java;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexDemo
 * <p>
 * Characters
 * x: 字符x
 * \\: 反斜杠
 * \0n:
 * \0nn:
 * \0mnn:	The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7)
 * \xhh	The character with hexadecimal value 0xhh
 * \ uhhhh	The character with hexadecimal value 0xhhhh
 * \x{h...h}	The character with hexadecimal value 0xh...h (Character.MIN_CODE_POINT  <= 0xh...h <=  Character.MAX_CODE_POINT)
 * \t: 制表符\u0009
 * \n: 换行符\u000A
 * \r: 回车符\u000D
 * \f: 换页符\u000C
 * \a:The alert (bell) character ('\u0007')
 * \e:The escape character ('\u001B')
 * \cx:The control character corresponding to x
 * </p>
 * <p>
 * Character classes
 * [abc]: a|b|c
 * [^abc]: !(a|b|c)
 * [a-zA-Z]: (a-z)||(A-Z)
 * [a-d[m-p]]: (a-z)||(m-p)
 * [a-z&&[def]]: (a-z)&&(d|e|f)
 * [a-z&&[^bc]]: (a-z)&&!(b|c)
 * [a-z&&[^m-p]]: (a-z)&&!(m-p)
 * </p>
 * <p>
 * Predefined character classes
 * .: 任意字符
 * \d: 数字[0-9]
 * \D: 非数字[^d]
 * \h: 水平空格字符[\t\xA0\u1680\u180e\u2000-\u200a\u202f\u205f\u3000]
 * \H: 非水平空格字符[^\h]
 * \s: 空格字符[\t\n\x0B\f\r]
 * \S: 非空格字符[^\s]
 * \v: 垂直空白字符[\n\x0B\f\r\x85\u2028\u2029]
 * \V: 非垂直空白字符[^\v]
 * \w: 单词字符[a-zA-Z_0-9]
 * \W: 非单词字符^\w]
 * </p>
 * <p>
 * POSIX character classes (US-ASCII only)
 * </p>
 * <p>
 * java.lang.Character classes (simple java character type)
 * </p>
 * <p>
 * Classes for Unicode scripts, blocks, categories and binary properties
 * </p>
 * <p>
 * Boundary matchers
 * ^: 行的起始位置
 * $: 行的结束位置
 * \b: 单词的边界
 * \B: 非单词的边界
 * \A: The beginning of the input
 * \G: The end of the previous match
 * \Z: The end of the input but for the final terminator, if any
 * \z: The end of the input
 * </p>
 * <p>
 * Linebreak matcher
 * \R	Any Unicode linebreak sequence, is equivalent to \u000D\u000A|[\u000A\u000B\u000C\u000D\u0085\u2028\u2029]
 * </p>
 * <p>
 * Greedy quantifiers 贪婪型
 * X?: 匹配零次或一次
 * X*: 匹配零次或多次
 * X+: 匹配一次或多次
 * X{n}: 匹配n次
 * X{n,}: 至少匹配n次
 * X{n,m}: 至少匹配n次，最多匹配m次
 * </p>
 * <p>
 * Reluctant quantifiers 勉强型
 * X??: X, once or not at all
 * X*?: X, zero or more times
 * X+?: X, one or more times
 * X{n}?: X, exactly n times
 * X{n,}?: X, at least n times
 * X{n,m}?: X, at least n but not more than m times
 * </p>
 * <p>
 * Possessive quantifiers 占有型
 * X?+: X, once or not at all
 * X*+: X, zero or more times
 * X++: X, one or more times
 * X{n}+: X, exactly n times
 * X{n,}+: X, at least n times
 * X{n,m}+: X, at least n but not more than m times
 * </p>
 * <p>
 * Logical operators
 * XY: X followed by Y
 * X|Y: Either X or Y
 * (X): X, as a capturing group
 * </p>
 * <p>
 * Back references
 * \n: Whatever the nth capturing group matched
 * \k<name>: Whatever the named-capturing group "name" matched
 * </p>
 * <p>
 * Quotation
 * \: Nothing, but quotes the following character
 * \Q: 开始引用到\E截止的字符串
 * \E: 结束引用从\Q起始的字符串
 * </p>
 * <p>
 * Special constructs (named-capturing and non-capturing)
 * (?<name>X): X, as a named-capturing group
 * (?:X): X, as a non-capturing group
 * (?idmsuxU-idmsuxU): 	Nothing, but turns match flags i d m s u x U on - off
 * (?idmsux-idmsux:X):  	X, as a non-capturing group with the given flags i d m s u x on - off
 * (?=X): X, via zero-width positive lookahead
 * (?!X): X, via zero-width negative lookahead
 * (?<=X): X, via zero-width positive lookbehind
 * (?<!X): X, via zero-width negative lookbehind
 * (?>X): X, as an independent, non-capturing group
 * </p>
 *
 * @author maomao
 * @since 2016.12.23
 */
public class RegexDemo {

    /**
     * 匹配字符串的整个部分
     */
    private static void matches() {
        String str = "11aa22";
        Pattern pattern = Pattern.compile("(\\d){2}(\\w){2}(\\d){2}");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }

    /**
     * 匹配字符串的起始部分
     */
    private static void lookingAt() {
        String str = "11aa22";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.lookingAt());

        matcher.reset("aa11bb");
        System.out.println(matcher.lookingAt());
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
        String str = "我的邮箱:maomao8017@gmail.com";
        Pattern pattern = Pattern.compile("\\d+");
        System.out.println(Arrays.toString(pattern.split(str)));
    }

    private static void replace() {

    }

    private static void reset() {

    }

    public static void main(String[] args) {
        String content = "啦啦啦啦<a class=\"ilink unline\" href =\"//www.google.com\" target=\"_blank\">//www.google.com</a>啦啦啦</p>";
        String regex = "href( )*=( )*\"//";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        content = matcher.replaceAll("href=\"http://");
        System.out.println(content);
    }
}
