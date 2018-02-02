package priv.mm.java8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MethodReference
 * 1. 引用静态方法
 * 2. 引用特定对象的实例方法
 * 3. 引用特定类型的任意对象的实例方法
 * 4. 引用构造方法
 *
 * @author maomao
 * @see <a href="http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">官方文档</a>
 * @since 2016.11.10
 */
public class MethodReference {

    /**
     * Reference to a static method
     * ContainingClass::staticMethodName
     */
    public static void m1() {
        List<Person> roster = Person.createRoster();
        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);
        Arrays.sort(rosterAsArray, Person::compareByAge);
    }

    /**
     * Reference to an instance method of a particular object
     * containingObject::instanceMethodName
     */
    public static void m2() {
        List<Person> roster = Person.createRoster();
        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

        class ComparisonProvider {
            public int compareByName(Person a, Person b) {
                return a.getName().compareTo(b.getName());
            }

            public int compareByAge(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);
    }

    /**
     * Reference to an instance method of an arbitrary object of a particular type
     * ContainingType::methodName
     */
    public static void m3() {
        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);
    }

    /**
     * Reference to a constructor
     * ClassName::new
     */
    public static void m4() {
        List<Person> roster = Person.createRoster();
        Set<Person> rosterSet = Person.transferElements(roster, HashSet<Person>::new);
    }

}
