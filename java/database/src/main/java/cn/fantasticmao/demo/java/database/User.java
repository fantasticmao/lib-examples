package cn.fantasticmao.demo.java.database;

/**
 * User
 *
 * @author fantasticmao
 * @see cn.fantasticmao.demo.java.database.h2.H2Repository
 * @see cn.fantasticmao.demo.java.database.hbase.HbaseRepository
 * @see cn.fantasticmao.demo.java.database.mongodb.MongoRepository
 * @see cn.fantasticmao.demo.java.database.sqlite.SqliteRepository
 * @since 2021-12-18
 */
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;

    public User() {
    }

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(Integer id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
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

}
