package com.rockthejvm

package object part3removals:
  val aPackageLevelValue = "Scala 3"
  def aPackageLevelMethod() = println("this comes from a package object")
  
  // classes
  class PackageLevelClass(data: String)
  trait PackageLevelTrait
  object PackageLevelObject
  type POList[A] = List[A]
