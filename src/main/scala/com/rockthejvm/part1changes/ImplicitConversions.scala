package com.rockthejvm.part1changes

object ImplicitConversions {

  case class Person(name: String) {
    def greet: String = s"Hey, my name is $name, Scala rocks!"
  }

  // Scala 2
  //  implicit def string2Person(string: String): Person = Person(string)
  //  // implicit conversions are discouraged
  //  val daniel: Person = "Daniel" // string2Person("Daniel")
  //  "Daniel".greet // string2Person("Daniel").greet

  // Scala 3: add implicit conversions explicitly
  // step 1: import the implicit conversions support
  import scala.language.implicitConversions
  // step 2: define a given value of type Conversion
  given string2Person: Conversion[String, Person] with
    override def apply(string: String): Person = Person(string)

  // 1 - use methods of the converted type
  "Daniel".greet
  // 2 - use the convertee instead of the required type
  val person: Person = "Daniel"

  def sayHiTo(person: Person): Unit =
    println(s"Hi, ${person.name}")

  sayHiTo("Alice") // ok

  def main(args: Array[String]): Unit = {

  }
}
