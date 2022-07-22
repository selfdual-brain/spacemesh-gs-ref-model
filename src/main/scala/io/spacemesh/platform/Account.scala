package io.spacemesh.platform

import io.spacemesh.platform.TemplateMethodVisibility.{local, *}

abstract class Account[S](address: AccountAddress, flavor: AccountFlavor, immutableState: S, host: HostAPI) extends CloningSupport[Account[S]] {

  @internal @query
  def parsePayload(tx: Transaction): ParsedTransaction

  @internal @query
  def verify(tx: Transaction, parsedPayload: ParsedTransaction): Boolean

  @local(selector = 1)
  def relayCall(targetAccount: AccountAddress, attachedTransfer: Ether, method: Byte, methodArgs: Array[Byte]): Unit = {
    host.call(targetAccount, attachedTransfer, method, methodArgs)
  }

  @local(selector = 2)
  def spawn[T](template: TemplateAddress, immutableState: T): AccountAddress = {
    host.spawnNewAccount(template, immutableState)
  }

  @local(selector = 3)
  def deploy[S](template: Array[Byte]): Unit = {
    host.deployNewTemplate(template)
  }

  @local(selector = 4)
  def transfer(destination: AccountAddress, amount: Ether): Unit = {
    host.transfer(destination, amount)
  }

  @api(selector = 4) @query
  def balance(): Ether = host.balance()

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
