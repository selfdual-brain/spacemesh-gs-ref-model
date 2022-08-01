package io.spacemesh.simulation

import io.spacemesh.blockchain.blocks.Block
import io.spacemesh.blockchain.transactions.Transaction

sealed abstract class SimEventPayload {
}

object SimEventPayload {

  //#################### TRANSPORT ####################
  case class IncomingTxFromClient(tx: Transaction) extends SimEventPayload
  case class FinalizedBlockDeliveredToExecution(block: Block) extends SimEventPayload

  //#################### LOOPBACK ####################
  case class TransactionCreationWakeup(n: Int) extends SimEventPayload
  case class BlockCreationWakeup(n: Int) extends SimEventPayload

  //#################### SEMANTIC ####################
  case class BlockFinalized(block: Block) extends SimEventPayload

}
