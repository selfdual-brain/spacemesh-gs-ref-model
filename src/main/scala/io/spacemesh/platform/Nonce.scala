package io.spacemesh.platform

import scala.collection.immutable.BitSet

case class Nonce(counter: Long, bitfield: BitSet) {

  def canBeAppliedOver(oldNonce: Nonce): Boolean = 
    math.signum(counter - oldNonce.counter) match {
      case 1 => true
      case 0 => oldNonce.bitfield.intersect(bitfield).isEmpty
      case -1 => false
    }
    
  def applyOver(oldNonce: Nonce): Nonce =
    if (this.canBeAppliedOver(oldNonce)) {
      if (counter > oldNonce.counter)
        this
      else
        Nonce(counter, bitfield ++ oldNonce.bitfield)
      
    } else
      throw RuntimeException(s"Cannot apply $this over $oldNonce")

}

object Nonce {
  val zero: Nonce = Nonce(0, BitSet.empty)
}
