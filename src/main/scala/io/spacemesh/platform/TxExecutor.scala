package io.spacemesh.platform

abstract class TxExecutor(vm: VirtualMachine) {

  def execute(gs: GlobalState, tx: Transaction, parsedPayload: ParsedTxPayload, currentGasPrice: GasPrice, tx1kilobyteCost: Gas): (GlobalState, TxReceipt) = {
    val declaredMaxGas: Gas = parsedPayload.maxGas
    val costOfDeclaredMaxGas: TokensAmount = declaredMaxGas * currentGasPrice
    val initialAccountBalance: TokensAmount = gs.balanceOf(tx.principal)
    val gasAmountChargedForConsensus: Gas = math.max(1, tx.binarySize * tx1kilobyteCost / 1024)

    //checking if nonce declared in tx is compatible with current nonce in principal account
    if (! parsedPayload.nonce.canBeAppliedOver(gs.nonceOf(tx.principal))) {
      val totalFee: TokensAmount = gasAmountChargedForConsensus * currentGasPrice
      val newGlobalState: GlobalState = gs.updateAccountBalance(account = tx.principal, delta = - totalFee)
      val receipt: TxReceipt = TxReceipt.NonceMismatch(gasBurned = gasAmountChargedForConsensus, feeCharged = totalFee)
      return (newGlobalState, receipt)
    }

    //checking if declared gas limit is covered by the balance of principal account
    if (initialAccountBalance < costOfDeclaredMaxGas) {
      val totalFee: TokensAmount = gasAmountChargedForConsensus * currentGasPrice
      val newGlobalState: GlobalState = gs.updateAccountBalance(account = tx.principal, delta = - totalFee)
      val receipt: TxReceipt = TxReceipt.GasLimitNotCoveredBySponsorAccountBalance(gasBurned = gasAmountChargedForConsensus, feeCharged = totalFee)
      return (newGlobalState, receipt)
    }

    //execute transaction
    
    //if it fails in the middle of execution, we rollback changes, and we charge only for the gas burned
    
    //up to the moment of failing
    
    //(plus the usual consensus fee)


  }

}
