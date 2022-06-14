package io.spacemesh.platform

import io.spacemesh.platform.TemplateMethodVisibility.*

abstract class Account[S](address: AccountAddress, immutableState: S, host: FFI) extends CloningSupport[Account[S]] {

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

  protected def abortTransaction(msg: String): Unit = {
    throw new TxAbort(address, msg)
  }

  protected def copyTo(account: Account[S]): Unit

  protected def createEmpty(): Account[S]

  override def createDetachedCopy(): Account[S] = {
    val result = createEmpty()
    this.copyTo(result)
    return result
  }

}
