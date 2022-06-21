package io.spacemesh.standard_templates

import io.spacemesh.platform.{AccountAddress, FFI, Nonce, ParsedTxPayload, Account, TemplateAddress, Ether, Transaction}

object SimpleWalletAccount {

  class ImmutableState {

  }

  class Logic(account: AccountAddress, s: ImmutableState, host: FFI) extends Account[ImmutableState](account, s, host) {

    override def parsePayload(tx: Transaction): ParsedTxPayload = ???

    override def verify(tx: Transaction, parsedPayload: ParsedTxPayload): Boolean = ???

    override protected def copyTo(account: Account[ImmutableState]): Unit = ???

    override protected def createEmpty(): Account[ImmutableState] = ???
  }

}

