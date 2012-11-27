package nl.viewsource.util;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.getFirstName())
                .append(',')
                .append(this.getLastName())
                .append(',')
                .append(this.getAge());
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Person)) {
            return false;
        }

        final Person that = (Person) obj;


        return
                (this.firstName == that.firstName || this.firstName != null && this.firstName.equals(that.firstName))
                        &&
                        (this.lastName == that.lastName || this.lastName != null && this.lastName.equals(that.lastName))
                        &&
                        this.age == that.age;
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + age;
        return result;
    }
}

final class PersonBuilder {

    private PersonBuilder() {

    }

    public static IPersonBuilder with() {
        return with(new Person());
    }

    public static IPersonBuilder with(Person person) {
        return FluentInterface.create(person, IPersonBuilder.class);
    }

    static interface IPersonBuilder {
        IPersonBuilder firstName(String firstName);

        IPersonBuilder lastName(String lastName);

        IPersonBuilder age(int age);

        Person create();
    }
}
