package io.spacemesh.execution

trait TxSerializer {
  def serialize(tx: Transaction): Array[Byte]
  def deserialize(bytes: Array[Byte]): Transaction 
}
