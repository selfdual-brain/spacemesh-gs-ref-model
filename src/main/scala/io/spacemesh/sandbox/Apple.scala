package io.spacemesh.sandbox

class Apple {

  def foo(): Unit = {
    println("foo executing ...")
  }

  def bar(a: Int, b: String): Unit = {
    println("bar executing ...")
    println(a)
    println(b)
  }

  def baz(a: Boolean, m: Marble): Int = {
    println("baz executing ...")
    println(a)
    println(m)
    return 1
  }

}
