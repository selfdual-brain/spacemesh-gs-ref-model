package io.spacemesh.platform

trait ParsedTxPayload {
  def methodArgumentsBlob: Array[Byte]
  def nonce: Nonce
  def minLayer: Long
  def maxLayer: Long
  def maxGas: Gas
  def gasPrice: GasPrice
}
                     
