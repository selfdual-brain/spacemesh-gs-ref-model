package io.spacemesh.standard_templates

import io.spacemesh.cryptography.PublicKey
import io.spacemesh.blockchain.svm.{Account, AccountAddress, HostAPI}
import io.spacemesh.blockchain.transactions.{ParsedTransaction, Transaction}
import io.spacemesh.blockchain.svm.AccountFlavor
import io.spacemesh.blockchain.Ether

class SimpleWalletAccount(address: AccountAddress, publicKey: PublicKey, host: HostAPI) extends Account[PublicKey](address, AccountFlavor.Muggle, publicKey, host) {

  override def parsePayload(tx: Transaction): ParsedTransaction = ???

  override def verify(tx: Transaction, parsedPayload: ParsedTransaction): Boolean = ???

  override protected def copyTo(account: Account[PublicKey]): Unit = ???

  override protected def createEmpty(): Account[PublicKey] = ???

  override def maxSpend(tx: Transaction): Ether = ???
}

