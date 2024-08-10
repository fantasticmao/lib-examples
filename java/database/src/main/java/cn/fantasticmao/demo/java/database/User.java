package cn.fantasticmao.demo.java.database;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * User
 *
 * @author fantasticmao
 * @see cn.fantasticmao.demo.java.database.flink
 * @see cn.fantasticmao.demo.java.database.h2.H2Repository
 * @see cn.fantasticmao.demo.java.database.hbase.HbaseRepository
 * @see cn.fantasticmao.demo.java.database.mongodb.MongoCrudRepository
 * @see cn.fantasticmao.demo.java.database.postgresql.PostgresCrudRepository
 * @see cn.fantasticmao.demo.java.database.postgresql.PostgresJsonbRepository
 * @see cn.fantasticmao.demo.java.database.sqlite.SqliteRepository
 * @since 2021-12-18
 */
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime birthday;

    public static final User Tom = new User(1, "Tom", 20, "tom@google.com",
        LocalDateTime.of(2004, 6, 1, 12, 0, 0));
    public static final User Bob = new User(2, "Bob", 17, "bob@apple.com",
        LocalDateTime.of(2007, 8, 15, 12, 0, 0));
    public static final User Bob_2 = new User(2, "Bob", 17, "bob@google.com",
        LocalDateTime.of(2007, 8, 15, 12, 0, 0));
    public static final User Anni = new User(3, "Anni", 18, "anni@outlook.com",
        LocalDateTime.of(2006, 12, 24, 12, 0, 0));

    public User() {
    }

    public User(Integer id, String name, Integer age, String email, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.birthday = birthday;
    }

    public static byte[] toBytes(User user) {
        byte[] nameBytes = user.getName().getBytes(StandardCharsets.UTF_8);
        byte[] emailBytes = user.getEmail().getBytes(StandardCharsets.UTF_8);
        long birthdayTimestamp = user.getBirthday()
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli();

        int capacity = Integer.BYTES * 5 + Integer.BYTES * 2 + nameBytes.length + emailBytes.length + Long.BYTES;
        return ByteBuffer.allocate(capacity)
            .putInt(Integer.BYTES)
            .putInt(user.getId())
            .putInt(nameBytes.length)
            .put(nameBytes)
            .putInt(Integer.BYTES)
            .putInt(user.getAge())
            .putInt(emailBytes.length)
            .put(emailBytes)
            .putInt(Long.BYTES)
            .putLong(birthdayTimestamp)
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

        assert byteBuffer.getInt() == Long.BYTES;
        long birthdayTimestamp = byteBuffer.getLong();
        LocalDateTime birthday = Instant.ofEpochMilli(birthdayTimestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();

        return new User(id, name, age, email, birthday);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", birthday=" + birthday +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name)
            && Objects.equals(age, user.age) && Objects.equals(email, user.email)
            && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, email, birthday);
    }

    // getter and setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}
