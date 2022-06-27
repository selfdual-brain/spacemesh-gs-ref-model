package io.spacemesh.platform

trait ParsedTxPayload {
  val methodArgumentsBlob: Array[Byte]
  val nonce: Nonce
  val minLayer: Long
  val maxLayer: Long
  val maxGas: Gas
  val gasPrice: Ether
  val verifyData: Array[Byte]
}
                     
