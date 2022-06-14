package io.spacemesh.standard_templates

import io.spacemesh.platform.{AccountAddress, FFI, Nonce, ParsedTxPayload, Account, TemplateAddress, TokensAmount, Transaction}

object SimpleWalletAccount {

  class ImmutableState {

  }

  class Logic(account: AccountAddress, s: ImmutableState, host: FFI) extends Account[ImmutableState](account, s, host) {

    override def parsePayload(tx: Transaction): ParsedTxPayload = ???

    override def verify(tx: Transaction, parsedPayload: ParsedTxPayload): Boolean = ???

    override def relayCall(targetAccount: AccountAddress, method: Byte, methodArgs: Array[Byte]): Unit = ???

    override def spawn[T](template: TemplateAddress, immutableState: T): AccountAddress = ???

    override def transfer(destination: AccountAddress, amount: TokensAmount): Unit = ???

    override def balance(): TokensAmount = ???

    override protected def copyTo(account: Account[ImmutableState]): Unit = ???

    override protected def createEmpty(): Account[ImmutableState] = ???
  }

}

