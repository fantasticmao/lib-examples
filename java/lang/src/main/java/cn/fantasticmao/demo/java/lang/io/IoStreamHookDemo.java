package cn.fantasticmao.demo.java.lang.io;

import java.io.*;

/**
 * IoStreamHookDemo
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
 * @see IoStreamHookDemo.User#readObject(byte[])
 * @see IoStreamHookDemo.User#writeObject(User)
 * @since 04/02/2018
 */
public class IoStreamHookDemo {

    private static class User implements Serializable {
        private static final long serialVersionUID = 2196964377342851692L;
        private String username;
        private String password;

        public User() {
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            System.out.println("原始密码：" + password);
            final String putPassword = new StringBuffer(password).reverse().toString();
            System.out.println("加密密码：" + putPassword);

            ObjectOutputStream.PutField putFields = out.putFields();
            putFields.put("username", username);
            putFields.put("password", putPassword);
            out.writeFields();
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            ObjectInputStream.GetField readFields = in.readFields();
            final String readUsername = (String) readFields.get("username", "");
            final String readPassword = (String) readFields.get("password", "");

            this.username = readUsername;
            System.out.println("解密密码：" + readPassword);
            this.password = new StringBuffer(readPassword).reverse().toString();
            System.out.println("原始密码：" + password);
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

    private static byte[] writeObject(User user) {
        try (ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
             ObjectOutputStream objectOut = new ObjectOutputStream(byteArrayOut)) {
            objectOut.writeObject(user);
            return byteArrayOut.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static User readObject(byte[] bytes) {
        try (ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInput = new ObjectInputStream(byteArrayInput)) {
            return (User) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new User();
    }

    public static void main(String[] args) {
        final User user = new User("maomao", "123456");
        final byte[] bytes = writeObject(user);
        System.out.println("====== 序列化 && 反序列化 ======");
        final User user2 = readObject(bytes);
        System.out.println(user.equals(user2));
    }
}
