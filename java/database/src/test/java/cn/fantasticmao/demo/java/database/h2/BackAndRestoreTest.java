package cn.fantasticmao.demo.java.database.h2;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;

/**
 * BackAndRestoreTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class BackAndRestoreTest {

    @Test
    public void backup() throws SQLException {
        BackAndRestore.backup();
    }

    @Test
    @Ignore
    public void restore() throws SQLException {
        BackAndRestore.restore();
    }

}