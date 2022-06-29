package io.spacemesh.platform

trait ParsedTransaction {
  def methodArgumentsRaw: IndexedSeq[Byte]
  def nonce: Nonce
  def minLayer: Long
  def maxLayer: Long
  def maxGas: Gas
  def gasPrice: GasPrice
}
                     
