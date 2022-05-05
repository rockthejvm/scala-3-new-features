package com.rockthejvm.part3removals

object Syntax {

  /**
   * Scala 3
   */

  // do-while instruction
  //  var i = 0
  //  do {
  //    println(i)
  //    i += 1
  //  } while (i < 10)
  // Scala 3 does NOT support do-while instructions

  // methods returning Unit without the = sign (procedural syntax)
  //  def sayHi() {
  //    // some block retuning Unit
  //    println("I'm a Unit weird function")
  //  }

  // limit 22
  val functionWithLotsOfArgs = (
                                 x1: Int,
                                 x2: Int,
                                 x3: Int,
                                 x4: Int,
                                 x5: Int,
                                 x6: Int,
                                 x7: Int,
                                 x8: Int,
                                 x9: Int,
                                 x10: Int,
                                 x11: Int,
                                 x12: Int,
                                 x13: Int,
                                 x14: Int,
                                 x15: Int,
                                 x16: Int,
                                 x17: Int,
                                 x18: Int,
                                 x19: Int,
                                 x20: Int,
                                 x21: Int,
                                 x22: Int,
                                 x23: Int,
                                 x24: Int,
                                 x25: Int
                               ) => println("lots of arguments")

  // methods with no arguments vs methods with an empty argument list
  def aParameterlessMethod = 42
  def aMethodWithEmptyArgList() = 42

  // in Scala 2, you can call both with the "parameterless" syntax
  val meaningOfLife = aParameterlessMethod // ok
  // val meaningOfLife_v2 = aMethodWithEmptyArgList // ok, with warning (Scala 2), NOT ok in Scala 3
  // not the other way around
  // val meaningOfLife_v3 = aParameterlessMethod() // illegal

  // uninitialized vars
  // Scala 2
  var toAssignLater: Int = _ // ok, but will be phased out in a future Scala 3 version
  // some time later
  toAssignLater = 87 // ok

  // Scala 3 style
  import scala.compiletime.uninitialized
  var toBeSetLater: Int = uninitialized
  // set it later
  toBeSetLater = 68

  def main(args: Array[String]): Unit = {

  }
}
