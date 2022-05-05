package com.rockthejvm.part2additions

object UnionIntersectionTypes {

  // union types
  val truthOr42: Boolean | Int = 42

  def ambivalentMethod(arg: String | Int): String = arg match {
    case _: String => "a string"
    case _: Int => "a number"
  }

  val aNumberDescription = ambivalentMethod(55)
  val aStringDescription = ambivalentMethod("Scala")

  // type inference wil do LCA of the branches
  val stringOrInt = if (42 > 0) "a String" else 42
  val stringOrInt_v2: String | Int = if (42 > 0) "a String" else 42 // ok if we pass the union type explicitly

  // "flow" typing - explicit null checking
  type Maybe[T] = T | Null
  def handleMaybe(someValue: Maybe[String]): Int =
    if (someValue != null) someValue.length // someValue is a String
    else 0

  // flow typing is restricted
  //  type ErrorOr[T] = T | "error"
  //  def handleResource(arg: ErrorOr[Int]): Unit =
  //    if (arg != "error") println(arg + 1) // does not work
  //    else println("Error!")

  // intersection types
  class Animal
  trait Carnivore
  class Crocodile extends Animal with Carnivore
  val carnivoreAnimal: Animal & Carnivore = new Crocodile

  // intersection types & the diamond problem
  trait Gadget:
    def use(): Unit

  trait Camera extends Gadget:
    def takePicture(): Unit = println("smile!")
    override def use(): Unit = println("snap!")

  trait Phone extends Gadget:
    def makePhoneCall(): Unit = println("dialling...")
    override def use(): Unit = println("ring ring")

  def useSmartDevice(cp: Camera & Phone): Unit =
    cp.takePicture()
    cp.makePhoneCall()
    cp.use() // ??? depends on how Camera and Phone are inherited (order) - trait linearization

  class Smartphone extends Phone with Camera // use == snap
  class CameraWithPhone extends Camera with Phone // use == ringing

  // intersection types + covariance
  trait HostConfig
  trait HostController:
    def get: Option[HostConfig]

  trait PortConfig
  trait PortController:
    def get: Option[PortConfig]

  def getConfigs(controller: HostController & PortController): Option[HostConfig & PortConfig] =
    controller.get
  // works/compiles because Option is covariant

  def main(args: Array[String]): Unit = {
    useSmartDevice(new Smartphone)
    useSmartDevice(new CameraWithPhone)
  }
}
