package io.spacemesh.platform

case class Transaction(principal: AccountAddress, methodSelector: Byte, payload: Array[Byte]) {
  def binarySize: Int
}
