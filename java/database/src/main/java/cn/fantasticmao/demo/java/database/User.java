package cn.fantasticmao.demo.java.database;

import cn.fantasticmao.demo.java.database.hbase.HbaseRepository;

/**
 * User
 *
 * @author fantasticmao
 * @see cn.fantasticmao.demo.java.database.h2.H2Repository
 * @see HbaseRepository
 * @see cn.fantasticmao.demo.java.database.sqlite.SqliteRepository
 * @since 2021-12-18
 */
public class User {
    private Integer id;
    private String name;
    private String email;

    public User() {
    }

    public User(Integer id, String name) {
        this(id, name, null);
    }

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
