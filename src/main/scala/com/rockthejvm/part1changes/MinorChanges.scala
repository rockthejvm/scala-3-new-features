package com.rockthejvm.part1changes

object MinorChanges {

  /**
   * Imports
   */
  // importing everything
  import scala.concurrent.duration.* // * instead of _
  // alias
  import java.util.{List => JList} // Scala 2 notation
  import java.util.List as JList // Scala 3 style
  // import everything BUT something
  import java.util.{List as _ /* <-- import to be ignored */, *}

  /**
   * varargs
   */
  val aList = List(1,2,3,4)
  // many collections have vararg-apply methods; we might sometimes want to expand an existing collection to varargs
  val anArray = Array(aList(0), aList(1), aList(2), aList(3))
  // Scala 2
  val anArray_v2 = Array(aList: _*)
  // Scala 3
  val anArray_v3 = Array(aList*) // consistent with the vararg pattern match

  /**
   * Trait constructor arguments
   */
  trait Person(name: String) // legal now
  // solve the diamond problem
  //  trait JPerson extends Person("John")
  //  trait APerson extends Person("Alice")
  //  trait Kid extends JPerson with APerson // would be weird

  /**
   * "Universal constructors", == apply methods everywhere
   */
  class Pet(name: String)
  val lassie = Pet("Lassie") // ok
}
