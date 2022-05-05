package com.rockthejvm.part3removals

object Organizational {

  val aList: POList[Int] = List(1,2,3)
  val scala = aPackageLevelValue
  aPackageLevelMethod()
}

// Scala 3: top-level definitions
val favLang = "Scala 3" // visible inside the package
