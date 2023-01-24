package io.spacemesh.sandbox

import java.lang.reflect.Method

//import java.lang.reflect.*

object ReflectionTest1 {

  def main(args: Array[String]): Unit = {
    val appleClass: Class[?] = Class.forName("io.spacemesh.sandbox.Apple")
    val fooMethod: Method  = appleClass.getMethod("foo")
    val barMethod: Method  = appleClass.getMethod("bar", classOf[Int], classOf[String])
    val bazMethod: Method  = appleClass.getMethod("baz", classOf[Boolean], classOf[Marble])

    val probe: Apple = new Apple
    val marble: Marble = new Marble(101)

    fooMethod.invoke(probe)
    barMethod.invoke(probe, 42, "bingo")
    bazMethod.invoke(probe, true, marble)
  }

}
