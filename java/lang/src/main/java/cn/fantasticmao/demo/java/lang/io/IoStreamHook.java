package cn.fantasticmao.demo.java.lang.io;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

/**
 * IoStreamHook
 * <p>
 * 在序列化和反序列化过程中需要特殊处理的类，可以实现这些指定签名的特殊方法：
 * <br>
 * <pre>
 * private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException;
 * private void writeObject(java.io.ObjectOutputStream stream) throws IOException;
 * private void readObjectNoData() throws ObjectStreamException;
 * </pre>
 * </p>
 *
 * @author fantasticmao
 * @see java.util.Date
 * @since 04/02/2018
 */
@Slf4j
public class IoStreamHook {

    public static class User implements Serializable {
        private String username;
        private String password;

        public User() {
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Serial
        private void writeObject(ObjectOutputStream out) throws IOException {
            log.info("原始密码：{}", password);
            final String putPassword = new StringBuffer(password).reverse().toString();
            log.info("加密密码：{}", putPassword);

            ObjectOutputStream.PutField putFields = out.putFields();
            putFields.put("username", username);
            putFields.put("password", putPassword);
            out.writeFields();
        }

        @Serial
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            ObjectInputStream.GetField readFields = in.readFields();
            final String readUsername = (String) readFields.get("username", "");
            final String readPassword = (String) readFields.get("password", "");

            this.username = readUsername;
            log.info("解密密码：{}", readPassword);
            this.password = new StringBuffer(readPassword).reverse().toString();
            log.info("原始密码：{}", password);
        }

        @Override
        public String toString() {
            return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (!username.equals(user.username)) return false;
            return password.equals(user.password);
        }

        @Override
        public int hashCode() {
            int result = username.hashCode();
            result = 31 * result + password.hashCode();
            return result;
        }
    }

    public static byte[] writeObject(User user) {
        try (ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
             ObjectOutputStream objectOut = new ObjectOutputStream(byteArrayOut)) {
            objectOut.writeObject(user);
            return byteArrayOut.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }

    public static User readObject(byte[] bytes) {
        try (ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInput = new ObjectInputStream(byteArrayInput)) {
            return (User) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return new User();
    }

}
