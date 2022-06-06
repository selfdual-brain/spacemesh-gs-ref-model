package io.spacemesh.platform

trait TxSerializer {
  def serialize(tx: Transaction): Array[Byte]
  def deserialize(bytes: Array[Byte]): Transaction 
}
