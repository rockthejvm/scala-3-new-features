package com.rockthejvm.part1changes

object Givens {

  case class Person(name: String, age: Int)

  val people = List(
    Person("Daniel", 99),
    Person("Alice", 23),
    Person("Master Yoda", 900)
  )

  // Scala 2: implicits
  //  implicit val personOrdering: Ordering[Person] = new Ordering[Person] {
  //    override def compare(x: Person, y: Person) =
  //      x.name.compareTo(y.name)
  //  }

  // Scala 3: given value <=> implicit value
  //  given personOrdering: Ordering[Person] with
  //    override def compare(x: Person, y: Person) =
  //      x.name.compareTo(y.name)

  // alternate syntax ("alias")
  given personOrdering: Ordering[Person] = new Ordering[Person]:
    override def compare(x: Person, y: Person) =
      x.name.compareTo(y.name)

  // implicit arguments <=> using clauses
  // Scala 2
  def aMethodWithOrdering(persons: List[Person])(implicit ordering: Ordering[Person]): List[Person] = persons.sorted
  // Scala 3
  def aMethodWithOrdering_v2(persons: List[Person])(using ordering: Ordering[Person]): List[Person] = persons.sorted

  val sortedPeople = people.sorted // (personOrdering) passed automatically by the compiler
  // implicits are still supported in Scala 3, but they will be deprecated/removed

  /**
   * Synthesize new implicit/given values based on existing ones
   */
  // Scala 2
  //  implicit def optionOrdering[T](implicit ordering: Ordering[T]): Ordering[Option[T]] =
  //    new Ordering[Option[T]] {
  //      override def compare(x: Option[T], y: Option[T]) = (x, y) match {
  //        case (None, None) => 0
  //        case (None, _) => -1
  //        case (_, None) => 1
  //        case (Some(a), Some(b)) => ordering.compare(a, b)
  //      }
  //    }

  // Scala 3
  given optionOrdering_v2[T](using ordering: Ordering[T]): Ordering[Option[T]] with
    override def compare(x: Option[T], y: Option[T]) = (x, y) match
      case (None, None) => 0
      case (None, _) => -1
      case (_, None) => 1
      case (Some(a), Some(b)) => ordering.compare(a, b)

  /**
   * How implicits work with givens
   */

  def methodWithImplicitInt(implicit value: Int): Int = value * 10
  def methodWithUsingInt(using value: Int): Int = value * 10

  // with implicit value (Scala 2)
  //  implicit val meaningOfLife: Int = 42
  //  methodWithImplicitInt // ok
  //  methodWithUsingInt // ok; implicit values work with using clauses

  given meaningOfLife: Int = 42
  methodWithImplicitInt // ok; given values work with implicit arguments
  methodWithUsingInt // ok

  // implicit ambiguity is the same

  // passing non-implicit values explicitly instead of the implicit argument
  // Scala 2
  methodWithImplicitInt(100) // legal
  // Scala 3
  // methodWithUsingInt/*(42)*/(100) // not ok
  methodWithUsingInt(using 100) // ok - overriding current given

  /**
   * Importing differences
   */

  object PersonGivens:
    given ageOrdering: Ordering[Person] with
      override def compare(x: Person, y: Person) = y.age - x.age

  // 1 - import the explicit given
  // import PersonGivens.ageOrdering // also available in Scala 2

  // 2 - import a given for a certain type (if you don't know the name)
  // import PersonGivens.given Ordering[Person]

  // 3 - import all givens
  // import PersonGivens.given

  // important!
  // import PersonGivens._ // will NOT import the givens!

  /**
   * implicitly -> summon
   */
  // implicitly[T]
  def aMethodWithImplicitArg[T](implicit instance: T): T = instance

  // summon[T]
  def aMethodWithGivenArg[T](using instance: T): T = instance

  def main(args: Array[String]): Unit = {

  }
}
