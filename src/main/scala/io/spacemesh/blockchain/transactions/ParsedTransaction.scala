package io.spacemesh.blockchain.transactions

import io.spacemesh.blockchain.GasPrice
import io.spacemesh.blockchain.Gas

trait ParsedTransaction {
  def methodArgumentsRaw: IndexedSeq[Byte]

  def nonce: Nonce

  def minLayer: Long

  def maxLayer: Long

  def maxGas: Gas

  def gasPrice: GasPrice
}
