import com.rockthejvm.part2additions.InfixNotation.Person;

public class JavaApp {
  public static void main(String[] args) {
    Person person = new Person("Alice");
    System.out.println(person.amazedBy("Scala 3 can be called in Java!"));
  }
}
