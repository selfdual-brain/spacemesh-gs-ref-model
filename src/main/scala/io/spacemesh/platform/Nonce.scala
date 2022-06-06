package io.spacemesh.platform

import scala.collection.immutable.BitSet

case class Nonce(counter: Long, bitfield: BitSet) {
}

object Nonce {
  val zero: Nonce = Nonce(0, BitSet.empty)
}
