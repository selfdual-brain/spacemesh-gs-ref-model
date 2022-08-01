package io.spacemesh.blockchain.transactions

import io.spacemesh.cryptography.Hash
import io.spacemesh.blockchain.{ProtocolConstants, svm}
import io.spacemesh.blockchain.svm.AccountAddress
import io.spacemesh.blockchain.transactions.Transaction

class TransactionImpl(bytes: Array[Byte]) extends Transaction {
  assert (bytes.length >= ProtocolConstants.ADDRESS_LENGTH + 2) // a minimal transaction must contain txType, principal address and method selector

  val txType: Byte = bytes(0)
  val principal: AccountAddress = svm.AccountAddress(Hash(bytes.slice(1, ProtocolConstants.ADDRESS_LENGTH + 1)))
  val methodSelector: Byte = bytes(ProtocolConstants.ADDRESS_LENGTH + 1)

  override def binaryForm: Seq[Byte] = bytes

  override def payload: Seq[Byte] = bytes.drop(ProtocolConstants.ADDRESS_LENGTH + 2)
}