package com.rockthejvm.part1changes

object NotGivens {

  // library code
  def processLists[A, B](la: List[A], lb: List[B]): List[(A, B)] =
    for
      a <- la
      b <- lb
    yield (a, b)

  // goal: be able to call processLists ONLY IF the types A and B are DIFFERENT
  val combinedLists = processLists(List(1,2,3), List("black", "white")) // ok
  val combinedNumbers = processLists(List(1,2,3), List(4,5)) // not ok - we want this code to NOT COMPILE

  class <:>[A, B]
  // make the compiler create a <:>[A, A]
  given equalType[A]: <:>[A, A] = new <:>[A, A]

  object DifferentTypes:
    import scala.util.NotGiven
    // compiler cannot find a given T => compiler will generate a NotGiven[T]
    def processListsDifferentType[A, B](la: List[A], lb: List[B])(using NotGiven[A <:> B]): List[(A, B)] =
      processLists(la, lb)

  def main(args: Array[String]): Unit = {
    import DifferentTypes._
    val combinedLists = processListsDifferentType(List(1,2,3), List("black", "white")) // ok
    // val combinedNumbers = processListsDifferentType(List(1,2,3), List(4,5)) // not ok - we want this code to NOT COMPILE
  }
}
