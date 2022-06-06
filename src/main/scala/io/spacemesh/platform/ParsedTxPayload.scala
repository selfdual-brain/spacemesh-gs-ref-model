package io.spacemesh.platform

abstract class ParsedTxPayload{
  def methodArguments: Array[Byte]
  def nonce: Nonce
  def minLayer: Long
  def maxLayer: Long
  def maxGas: Gas
  def gasPrice: TokensAmount
}
                     
