package io.spacemesh.cryptography

import java.security.MessageDigest
import scala.util.Random
import scala.collection.mutable.StringBuilder

/**
 * Wrapper for a cryptographic hash value.
 */
case class Hash(bytes: Array[Byte]) extends Ordered[Hash] {
  override lazy val hashCode: Int = calculateHashCode //we memoize hashCode of this hash for performance optimization; it impacts all of the crucial data structures in the simulator

  override def toString: String = convertBytesToHex(bytes)

  private def convertBytesToHex(bytes: Seq[Byte]): String = {
    val sb = new StringBuilder
    for (b <- bytes) {
      sb.append(String.format("%02x", Byte.box(b)))
    }
    sb.toString
  }

  private def convertBytesToHexDashed(bytes: Seq[Byte]): String = {
    val sb = new StringBuilder
    for (b <- bytes) {
      sb.append(String.format("%02x", Byte.box(b)))
      sb.append('-')
    }
    sb.toString
  }

  override def compare(that: Hash): Int = {
    assert(bytes.length == that.bytes.length) //comparing hashes of different lengths considered illegal
    for (i <- 0 until math.min(bytes.length, that.bytes.length)) {
      val diff = bytes(i) - that.bytes(i)
      if (diff != 0)
        return diff
    }
    return 0
  }

  private def calculateHashCode: Int = {
    var acc: Int = 0
    for (i <- 0 until bytes.length / 2) {
      val offset = i * 2
      val intValue = (bytes(offset)<<8 & 0xFF00) | (bytes(offset+1) & 0xFF)
      acc = acc ^ intValue
    }
    return acc
  }

  override def canEqual(that: Any): Boolean = that.isInstanceOf[Hash]

  override def equals(obj: Any): Boolean = {
    if (obj == null)
      false
    else
      this.canEqual(obj) && {
        val that = obj.asInstanceOf[Hash]
        bytes.length == that.bytes.length && hashCode == that.hashCode && bytes.sameElements(obj.asInstanceOf[Hash].bytes)
      }
  }

}

object Hash {

  implicit val ordering: Ordering[Hash] = new Ordering[Hash] {
    override def compare(x: Hash, y: Hash): Int = x.compare(y)
  }

  /**
   * Generates fake hash using random number generator.
   */
  def random(hashLengthInBytes: Int, rng: Random): Hash = {
    val a = new Array[Byte](hashLengthInBytes)
    rng.nextBytes(a)
    return Hash(a)
  }

  /**
   * Generates fake hash using given random number generator.
   * This hash will be 8 bytes long (so, the size of Long values).
   */
  def random(rng: Random): Hash = random(8, rng)

  /**
   * Generates 64-bit digest from given string value.
   */
  def digest64of(string: String): Hash = {
    val mincer = MessageDigest.getInstance("SHA-256")
    mincer.update("#qwertyuiopasdfghjklzxcvbnm".getBytes("UTF-8"))
    mincer.update(string.getBytes("UTF-8"))
    mincer.update("$12345678901234567890".getBytes("UTF-8"))
    val sha256digest: Array[Byte] = mincer.digest()
    return Hash(sha256digest.slice(0, 8))
  }
}