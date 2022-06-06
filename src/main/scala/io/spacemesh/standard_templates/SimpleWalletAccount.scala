package io.spacemesh.standard_templates

import io.spacemesh.platform.{AccountAddress, Nonce, ParsedTxPayload, Template, TemplateAddress, Transaction}

object SimpleWalletAccount {

  class ImmutableState {

  }

  class Logic(s: ImmutableState, host: FFI) extends Template[ImmutableState](s, host) {
    private var nonceValue: Nonce = Nonce.zero

    override def nonce: Nonce = nonceValue

    override def parsePayload(tx: Transaction): ParsedTxPayload = ???

    override def verify(tx: Transaction, parsedPayload: ParsedTxPayload): Boolean = ???

    override def relayCall(targetAccount: AccountAddress, method: Byte, methodArgs: Array[Byte]): Unit = ???

    override def spawn[T](template: TemplateAddress, immutableState: T): AccountAddress = ???
  }

}

