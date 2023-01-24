package io.spacemesh.blockchain.svm
import io.spacemesh.blockchain.transactions.Nonce
import io.spacemesh.blockchain.Ether
import io.spacemesh.blockchain.transactions.Transaction
import io.spacemesh.blockchain.LayerId
import io.spacemesh.blockchain.svm.Account

/**
 * API dedicated to one account calling the host.
 * Lifespan of this instance is just one tx execution.
 */
class HostAPIImpl(
                   caller: AccountAddress,
                   owningTx: Transaction,
                   layerOfCurrentBlock: LayerId,
                   baseGS: GlobalState,
                   updateBuffer: GlobalStateIncrementalUpdateBuffer
                 ) extends HostAPI {

/*                                                          PUBLIC                                                   */

  override def transfer(destination: AccountAddress, amount: Ether): Unit = ???

  override def call[R](targetAccount: AccountAddress, attachedTransfer: Ether, method: Byte, methodArgs: Array[Byte]): R = ???

  override def spawnNewAccount[S](template: TemplateAddress, immutableState: S): AccountAddress = ???

  override def deployNewTemplate[S](template: Array[Byte]): TemplateAddress = ???

  override def layerId(): LayerId = layerOfCurrentBlock

  override def bBalance(): Ether = ???

  override def gBalance(): Ether = ???

  override def gasPaymentsReserve(): Ether = ???

  override def increaseGasPaymentsReserve(amount: Ether): Ether = ???

  override def decreaseGasPaymentsReserve(amount: Ether): Ether = ???

  override def nonce(): Nonce = ???

  override def rootPrincipal(): AccountAddress = ???

  override def callStack(): Seq[(AccountAddress, Byte)] = ???

  override def log(msg: String, code: Long): Unit = ???

  /*                                                          PRIVATE                                                   */

  def getAccountInstance(address: AccountAddress): Account[?] = ???

}
