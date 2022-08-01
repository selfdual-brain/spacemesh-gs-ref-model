package io.spacemesh.blockchain.transactions

trait TxSerializer {
  def serialize(tx: Transaction): Array[Byte]

  def deserialize(bytes: Array[Byte]): Transaction
}
