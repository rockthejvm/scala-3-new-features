package com.rockthejvm.part3removals

object TypeProjections {

  class Outer:
    class Inner

  val o1 = new Outer
  val o2 = new Outer
  val i1: Outer#Inner = new o1.Inner
  val i2: Outer#Inner = new o2.Inner
  //      ^^^^^^^^^^^ type projection

  // Scala 2 advanced exercise to motivate abstract type projections
  //  trait ItemLike {
  //    type Key
  //  }
  //
  //  trait Item[K] extends ItemLike {
  //    type Key = K
  //  }
  //
  //  class StringItem extends Item[String]
  //  class IntItem extends Item[Int]
  //
  //  def get[ItemType <: ItemLike](key: ItemType#Key): ItemType /* appropriate Item type for the appropriate Key */  = ???
  //                                      ^^ DOES NOT WORK
  //
  //  get[IntItem](42) // an IntItem with 42
  //  get[StringItem]("Scala") // a StringItem with 42
  //  // get[StringItem](42) // should not compile

  // otherwise, generic type projections would allow bad bounds to be mixed:

  //  trait ItemAll extends ItemLike {
  //    type Key >: Any
  //  }
  //
  //  trait ItemNothing extends ItemLike {
  //    type Key <: Nothing
  //  }
  //
  //  def funcAll[ItemType <: ItemAll]: Any => ItemType#Key = a => a
  //  def funcNothing[ItemType <: ItemNothing]: ItemType#Key => Nothing = a => a
  //  def funcWeird[ItemType <: ItemAll with ItemNothing]: Any => Nothing =
  //    funcAll[ItemType].andThen(funcNothing[ItemType])
  //
  //  def main(args: Array[String]): Unit = {
  //    val anInt: Int = funcWeird("Scala")
  //    println(anInt + 1)
  //  }

  // this code compiles and throws a ClassCastException (we've lost type safety)!


}
