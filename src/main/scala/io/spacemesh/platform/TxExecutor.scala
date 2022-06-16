package io.spacemesh.platform

import io.spacemesh.platform.TxReceipt

abstract class TxExecutor(vm: VirtualMachine) {

  //caution: at this point we assume that the existence of principal account was already validated

  /**
   * Implements the main "transition" function, i.e. applies given transaction to given global state.
   * Caution: global states are immutable, so we are creating a new global state here.
   *
   * @param gs global state to be updated by the execution of the transaction
   * @param tx transaction to be executed
   * @param parsedPayload parsed payload of the transaction
   * @param currentGasPrice gas price to be applied for this transaction
   * @param tx1kilobyteCost consensus cost to be charged (gas units per 1 kilobyte of tx data)
   * @return new global state
   */
  def execute(gs: GlobalState, tx: Transaction, parsedPayload: ParsedTxPayload, currentGasPrice: GasPrice, tx1kilobyteCost: Gas): (GlobalState, TxReceipt) = {
    val declaredMaxGas: Gas = parsedPayload.maxGas
    val costOfDeclaredMaxGas: Ether = declaredMaxGas * currentGasPrice
    val initialAccountBalance: Ether = gs.balanceOf(tx.principal)
    val gasAmountChargedForConsensus: Gas = math.max(1, tx.binarySize * tx1kilobyteCost / 1024)
    val consensusFee: Ether = gasAmountChargedForConsensus * currentGasPrice
    val newNonce: Nonce = parsedPayload.nonce

    //nonce mismatch check
    if (! newNonce.canBeAppliedOver(gs.nonceOf(tx.principal))) {
      val newGlobalState: GlobalState = gs.updateAccount(addr = tx.principal, balanceDelta = - consensusFee, maybeNonce = None)
      val receipt: TxReceipt = TxReceipt.NonceMismatch(gasBurned = gasAmountChargedForConsensus, feeCharged = consensusFee)
      return (newGlobalState, receipt)
    }

    //checking if declared gas limit is covered by the balance of principal account
    if (initialAccountBalance < consensusFee + costOfDeclaredMaxGas) {
      val totalFee: Ether = gasAmountChargedForConsensus * currentGasPrice
      val newGlobalState: GlobalState = gs.updateAccount(addr = tx.principal, balanceDelta = - totalFee, maybeNonce = Some(newNonce))
      val receipt: TxReceipt = TxReceipt.GasLimitNotCoveredBySponsorAccountBalance(gasBurned = gasAmountChargedForConsensus, feeCharged = totalFee)
      return (newGlobalState, receipt)
    }

    //we update nonce and we secure the cost of gas
    //this will be the global state used as the base for the changes buffer
    val gs1: GlobalState = gs.updateAccount(addr = tx.principal, balanceDelta = consensusFee + costOfDeclaredMaxGas, Some(newNonce))

    //preparing the "incremental update buffer" to accumulate all changes done to global state
    //by the cascade of smart-contract methods invoked during processing of this transaction
    //caution: the buffer also works as the cache of cloned accounts
    val buffer = new GlobalStateIncrementalUpdateBuffer(gs1, tx.principal)

    //todo finish this
    return (gs, TxReceipt.Success(1,1))

    //execute transaction
    
    //if it fails in the middle of execution, we rollback changes, and we charge only for the gas burned
    
    //up to the moment of failing
    
    //(plus the usual consensus fee)


  }

}
