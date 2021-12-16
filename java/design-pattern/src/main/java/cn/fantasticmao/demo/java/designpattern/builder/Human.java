package cn.fantasticmao.demo.java.designpattern.builder;

/**
 * Human
 *
 * @author fantasticmao
 * @since 2017/8/13
 */
public class Human {
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Human: " + firstName + "·" + lastName;
    }

    public static class Builder {
        private Human human = new Human();

        public Builder firstName(String firstName) {
            this.human.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.human.lastName = lastName;
            return this;
        }

        public Human build() {
            return this.human;
        }
    }
}
