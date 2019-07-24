package atomic_reference;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String name;
    Integer age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
public class Test {
    public static void main(String[] args) {
        User a = new User("a",20);
        User b = new User("b",30);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(a);
        System.out.println(userAtomicReference.compareAndSet(a, b)+" "+userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(a, b)+" "+userAtomicReference.get().toString());
    }
}
