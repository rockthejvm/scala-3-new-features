package com.rockthejvm.part2additions

object MatchTypes {


  // Ints, Strings, Lists
  // BigInt -> last digit, of type Int
  // Strings -> last char
  // Lists -> last element

  def lastDigitOf(number: BigInt): Int = (number % 10).toInt
  def lastCharOf(string: String): Char =
    if string.isEmpty then throw new NoSuchElementException
    else string.charAt(string.length - 1)
  def lastElementOf[T](list: List[T]): T =
    if list.isEmpty then throw new NoSuchElementException
    else list.last

  // Scala 2? Can't.
  // Scala 3? yes.

  type ConstituentPartOf[T] = T match
    case BigInt => Int
    case String => Char
    case List[t] => t

  val aDigit: ConstituentPartOf[BigInt] = 2 // ok
  val aChar: ConstituentPartOf[String] = 'a'
  val anElement: ConstituentPartOf[List[Int]] = 42

  def lastComponentOf[T](biggerValue: T): ConstituentPartOf[T] = biggerValue match
    case b: BigInt => (b % 10).toInt
    case s: String =>
      if s.isEmpty then throw new NoSuchElementException
      else s.charAt(s.length - 1)
    case l: List[_] =>
      if l.isEmpty then throw new NoSuchElementException
      else l.last

  val lastDigit = lastComponentOf(BigInt(2357845)) // 5
  val lastChar = lastComponentOf("Scala") // 'a'
  val lastElement = lastComponentOf(List(1,2,3)) // 3

  // Why is this different from OOP
  // def returnLastConstituentOf(thing: Any): ConstituentPart = thing match ...

  // Why is this different from regular generics?
  // more subtle

  def lastElementOfList[A](list: List[A]): A = list.last

  lastElementOfList(List(1,2,3)) // 3, an Int
  lastElementOfList(List("a", "b")) // "b", a String

  // recursion
  type LowestLevelPartOf[T] = T match
    case List[t] => LowestLevelPartOf[t]
    case _ => T

  val lastElementOfNestedList: LowestLevelPartOf[List[List[List[Int]]]] = 2

  // not ok
  //  type AnnoyingMatchType[T] = T match
  //    case _ => AnnoyingMatchType[T]

  // not ok with infinite cycles - the compiler SOs
  //  type InfiniteRecursiveType[T] = T match
  //    case Int => InfiniteRecursiveType[T]
  //
  //  def aNaiveMethod[T]: InfiniteRecursiveType[T] = ???
  //
  //  val illegal: Int = aNaiveMethod[Int]

  // limited "flow typing"
  //  def accumulate[T](accumulator: T, smallerValue: ConstituentPartOf[T]): T = accumulator match
  //    case b: BigInt => b + smallerValue
}
