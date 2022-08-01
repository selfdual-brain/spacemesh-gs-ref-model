package io.spacemesh.execution

sealed abstract class TxReceipt {
  val gasBurned: Gas
  val feeCharged: Ether
}

object TxReceipt {
  case class Success(gasBurned: Gas, feeCharged: Ether) extends TxReceipt
  case class NonceMismatch(gasBurned: Gas, feeCharged: Ether) extends TxReceipt
  case class GasLimitNotCoveredBySponsorAccountBalance(gasBurned: Gas, feeCharged: Ether) extends TxReceipt
  case class GasLimitExceeded(gasBurned: Gas, feeCharged: Ether) extends TxReceipt
  case class UnhandledExceptionThrownByContractCode(gasBurned: Gas, feeCharged: Ether, exception: String)  extends TxReceipt
}
