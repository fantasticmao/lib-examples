package cn.fantasticmao.demo.java.spring.tx;

/**
 * User
 *
 * @author maomao
 * @since 2019-08-15
 */
public class User {
    private Integer id;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
