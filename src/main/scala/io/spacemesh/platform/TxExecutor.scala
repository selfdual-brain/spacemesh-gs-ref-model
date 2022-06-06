package io.spacemesh.platform

abstract class TxExecutor(gs: GlobalState) {
  def execute(tx: Transaction): Unit

}
