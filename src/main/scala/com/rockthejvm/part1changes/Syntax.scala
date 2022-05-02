package com.rockthejvm.part1changes

object Syntax {

  // if
  val ifExpression = if (2 > 3) "bigger" else "smaller"
  val ifExpression_v2 = if 2 > 3 then "bigger" else "smaller" // Scala 3

  // multiline ifs
  val ifExpression_v3 =
    if (2 > 3) {
      val result = "bigger"
      // code
      result
    } else {
      val result = "smaller"
      // code
      result
    }

  // Scala 3: braceless
  val ifExpression_v4 =
    if 2 > 3 then
      val result = "bigger"
      // code
      result
    else
      val result = "smaller"
      // code
      result
    // indentation matters

  // while
  val whileExpression: Unit = while (2 > 3) {
    println("bigger")
    println("MUCH bigger")
  }

  // Scala 3: braceless
  val whileExpression_v2: Unit = while 2 > 3 do
    println("bigger")
    println("Much bigger")
    // indentation matters

  // for
  val forComprehension =
    for {
      num <- List(1,2,3)
      char <- List('a', 'b')
    } yield s"$num-$char"

  // Scala 3
  val forComprehension_v2 =
    for
      num <- List(1,2,3)
      char <- List('a', 'b')
    yield s"$num-$char"

  // match
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"
  }

  // Scala 3: braceless
  val aPatternMatch_v2 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  // try-catch
  val tryCatch =
    try {
      "".charAt(2) // throws an IOOB exception
    } catch {
      case oobE: IndexOutOfBoundsException => '_'
      case e: Exception => 'z'
    }

  // Scala 3: braceless
  val tryCatch_v2 =
    try
      "".charAt(2)
    catch
      case oobE: IndexOutOfBoundsException => '_'
      case e: Exception => 'z'

  /**
   * Significant indentation
   */
  def computeMeaningOfLife(arg: Int): Int = // significant indentation activated HERE
    val partialResult = 49



    // code (blank)




    partialResult + arg + 2 // <- is part of the code block/method implementation!
    // "phantom code block" ends with the significant indentation

  def isPrime(n: Int): Boolean =
    def aux(potentialDivisor: Int): Boolean =
      if (potentialDivisor > n / 2) true
      else if (n % potentialDivisor == 0) false
      else aux(potentialDivisor + 1)

    aux(2)
  end isPrime // <- Scala 3: for large code blocks
  // ^ if, while, match, for, methods, classes, objects, ...

  // significant indentation region token `:`, for classes, traits, objects, enums
  class Animal {
    def eat(): Unit = {
      println("I'm eating")
    }
  }

  // Scala 3
  class AnimalV2: // <- indentation region
    def eat(): Unit =
      println("I'm eating")

    def grow(): Unit =
      println("I'm growing")
  end AnimalV2

  /*
    indentation = # of whitespace characters (spaces + tabs)
    3 spaces + 2 tabs > 2 spaces + 2 tabs
    3 spaces + 3 tabs > 3 spaces + 2 tabs
    3 spaces + 2 tabs ??? 2 spaces + 3 tabs

    use one kind of indentation!
  */

  def main(args: Array[String]): Unit = {

  }
}
