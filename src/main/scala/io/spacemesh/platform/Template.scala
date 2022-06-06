package io.spacemesh.platform

import io.spacemesh.platform.TemplateMethodVisibility.*

abstract class Template[S](account: AccountAddress, immutableState: S, host: FFI) {

  @internal
  def nonce: Nonce 

  @internal
  def parsePayload(tx: Transaction): ParsedTxPayload

  @internal
  def verify(tx: Transaction, parsedPayload: ParsedTxPayload): Boolean

  @local(selector = 1)
  def relayCall(targetAccount: AccountAddress, method: Byte, methodArgs: Array[Byte]): Unit

  @local(selector = 2)
  def spawn[T](template: TemplateAddress, immutableState: T): AccountAddress

  @local(selector = 3)
  def transfer(destination: AccountAddress, amount: TokensAmount): Unit

  @api(selector = 4)
  def balance(): TokensAmount
}
