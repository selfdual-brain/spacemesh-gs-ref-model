package io.spacemesh

import java.time.ZoneId

object Test1 {

  def main(args: Array[String]): Unit = {
    val t = java.time.ZonedDateTime.of(2022, 8, 13, 14, 0, 0, 0, ZoneId.of("UTC"))
    val m = t.toInstant.toEpochMilli
    val s = t.toInstant.getEpochSecond
    println(m)
    println(s)
  }

}
