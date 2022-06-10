package io.spacemesh.platform

import io.spacemesh.platform.TemplateMethodVisibility.*

abstract class Template[S](account: AccountAddress, immutableState: S, host: FFI) {

  @internal @query
  def nonce: Nonce 

  @internal @query
  def parsePayload(tx: Transaction): ParsedTxPayload

  @internal @query
  def verify(tx: Transaction, parsedPayload: ParsedTxPayload): Boolean

  @local(selector = 1)
  def relayCall(targetAccount: AccountAddress, method: Byte, methodArgs: Array[Byte]): Unit = {
    host.call(targetAccount, method, methodArgs)
  }

  @local(selector = 2)
  def spawn[T](template: TemplateAddress, immutableState: T): AccountAddress = {
    host.spawnNewAccount(template, immutableState)
  }

  @local(selector = 3)
  def transfer(destination: AccountAddress, amount: TokensAmount): Unit = {
    host.transfer(destination, amount)
  }

  @api(selector = 4) @query
  def balance(): TokensAmount = host.balance()
}
