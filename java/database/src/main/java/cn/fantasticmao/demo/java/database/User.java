package cn.fantasticmao.demo.java.database;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * User
 *
 * @author fantasticmao
 * @see cn.fantasticmao.demo.java.database.flink
 * @see cn.fantasticmao.demo.java.database.h2.H2Repository
 * @see cn.fantasticmao.demo.java.database.hbase.HbaseRepository
 * @see cn.fantasticmao.demo.java.database.mongodb.MongoDBRepository
 * @see cn.fantasticmao.demo.java.database.sqlite.SqliteRepository
 * @since 2021-12-18
 */
public class User {
    private final Integer id;
    private final String name;
    private final Integer age;
    private final String email;

    public static final User Tom = new User(1, "Tom", 20, "tom@google.com");
    public static final User Bob = new User(2, "Bob", 17, "bob@apple.com");
    public static final User Bob_2 = new User(2, "Bob", 17, "bob@google.com");
    public static final User Anni = new User(3, "Anni", 18, "anni@outlook.com");

    public User(Integer id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public static byte[] toBytes(User user) {
        byte[] nameBytes = user.getName().getBytes(StandardCharsets.UTF_8);
        byte[] emailBytes = user.getEmail().getBytes(StandardCharsets.UTF_8);

        int capacity = Integer.BYTES * 4 + Integer.BYTES * 2 + nameBytes.length + emailBytes.length;
        return ByteBuffer.allocate(capacity)
            .putInt(Integer.BYTES)
            .putInt(user.getId())
            .putInt(nameBytes.length)
            .put(nameBytes)
            .putInt(Integer.BYTES)
            .putInt(user.getAge())
            .putInt(emailBytes.length)
            .put(emailBytes)
            .array();
    }

    public static User fromBytes(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        assert byteBuffer.getInt() == Integer.BYTES;
        int id = byteBuffer.getInt();

        int nameLength = byteBuffer.getInt();
        byte[] nameBytes = new byte[nameLength];
        byteBuffer.get(nameBytes);
        String name = new String(nameBytes, StandardCharsets.UTF_8);

        assert byteBuffer.getInt() == Integer.BYTES;
        int age = byteBuffer.getInt();

        int emailLength = byteBuffer.getInt();
        byte[] emailBytes = new byte[emailLength];
        byteBuffer.get(emailBytes);
        String email = new String(emailBytes, StandardCharsets.UTF_8);

        return new User(id, name, age, email);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name)
            && Objects.equals(age, user.age) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, email);
    }

    // getter

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

}
