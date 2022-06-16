package io.spacemesh.platform

import io.spacemesh.platform.TxReceipt

abstract class TxExecutor(vm: VirtualMachine) {

  //caution: at this point we assume that the existence of principal account was already validated 
  def execute(gs: GlobalState, tx: Transaction, parsedPayload: ParsedTxPayload, currentGasPrice: GasPrice, tx1kilobyteCost: Gas): (GlobalState, TxReceipt) = {
    val declaredMaxGas: Gas = parsedPayload.maxGas
    val costOfDeclaredMaxGas: Ether = declaredMaxGas * currentGasPrice
    val initialAccountBalance: Ether = gs.balanceOf(tx.principal)
    val gasAmountChargedForConsensus: Gas = math.max(1, tx.binarySize * tx1kilobyteCost / 1024)
    val newNonce: Nonce = parsedPayload.nonce

    //nonce mismatch check
    if (! newNonce.canBeAppliedOver(gs.nonceOf(tx.principal))) {
      val totalFee: Ether = gasAmountChargedForConsensus * currentGasPrice
      val newGlobalState: GlobalState = gs.updateAccount(addr = tx.principal, balanceDelta = - totalFee, maybeNonce = None)
      val receipt: TxReceipt = TxReceipt.NonceMismatch(gasBurned = gasAmountChargedForConsensus, feeCharged = totalFee)
      return (newGlobalState, receipt)
    }

    //checking if declared gas limit is covered by the balance of principal account
    if (initialAccountBalance < costOfDeclaredMaxGas) {
      val totalFee: Ether = gasAmountChargedForConsensus * currentGasPrice
      val newGlobalState: GlobalState = gs.updateAccount(addr = tx.principal, balanceDelta = - totalFee, maybeNonce = Some(newNonce))
      val receipt: TxReceipt = TxReceipt.GasLimitNotCoveredBySponsorAccountBalance(gasBurned = gasAmountChargedForConsensus, feeCharged = totalFee)
      return (newGlobalState, receipt)
    }

    //todo finish this
    return (gs, TxReceipt.Success(1,1))

    //execute transaction
    
    //if it fails in the middle of execution, we rollback changes, and we charge only for the gas burned
    
    //up to the moment of failing
    
    //(plus the usual consensus fee)


  }

}
