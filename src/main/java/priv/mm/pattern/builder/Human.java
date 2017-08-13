package priv.mm.pattern.builder;

/**
 * Human
 *
 * @author maodh
 * @since 2017/8/13
 */
public class Human {
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Human: " + firstName + "·" + lastName;
    }

    private static class Builder {
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

    public static void main(String[] args) {
        Human human = new Human.Builder().firstName("猛").lastName("毛").build();
        System.out.println(human);
    }
}
