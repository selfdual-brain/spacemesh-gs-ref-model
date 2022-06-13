package io.spacemesh.platform

sealed abstract class TxReceipt {
  val gasBurned: Gas
  val feeCharged: TokensAmount
}

object TxReceipt {
  case class Success(gasBurned: Gas, feeCharged: TokensAmount) extends TxReceipt
  case class NonceMismatch(gasBurned: Gas, feeCharged: TokensAmount) extends TxReceipt
  case class GasLimitNotCoveredBySponsorAccountBalance(gasBurned: Gas, feeCharged: TokensAmount) extends TxReceipt
  case class GasLimitExceeded(gasBurned: Gas, feeCharged: TokensAmount) extends TxReceipt
  case class UnhandledExceptionThrownByContractCode(gasBurned: Gas, feeCharged: TokensAmount, exception: String)  extends TxReceipt
}
