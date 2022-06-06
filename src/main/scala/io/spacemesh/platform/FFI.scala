package io.spacemesh.platform

/**
 * API of the "host" (i.e. the smart contracts execution engine) exposed to every smart contract.
 *
 * Caution: The way this API is exposed to smart contracts must ensure that the host will know who is calling
 * (i.e. the code belonging to WHICH account is calling the host service).
 * Below in the docs we refer to the calling account as "the caller".
 */
trait FFI {

  /**
   * Executes native tokens transfer from the caller to given destination account.
   * If the requested amount of tokens exceeds current balance of the caller, current transaction will fail.
   *
   * @param destination account where to transfer tokens to
   * @param amount amount of tokens to be transferred.
   */
  def transfer(destination: AccountAddress, amount: TokensAmount): Unit

  /**
   * Layer number of the block in the context of which the current transaction is being executed.
   * In other words it is height of the block since Genesis (Genesis block is layer 0).
   * This can be understood as a rough internal "blockchain time" concept.
   */
  def layerId(): LayerId

  /**
   * The current native tokens balance of the caller.
   */
  def balance(): TokensAmount

  /**
   * Principal of the current transaction.
   */
  def rootPrincipal(): AccountAddress

  /**
   * Because inter-contract calls are possible, at at point along the execution of a transaction
   * we have a call stack snapshot. Every entry in this call stack is a method at some account.
   * The call stack mapped to (account, method-selector) is the collection returned by this method.
   * The first element of the collection corresponds to the bottom of the call stack.
   *
   * Remark: the pair (p,m) at the bottom of the call stack contains the root principal of the current
   * transaction (and the method selector as was specified in the current transaction).
   */
  def callStack(): Seq[(AccountAddress, Byte)]

  /**
   *
   * @return
   */
  def tokensReceivedMap(): Map[AccountAddress, TokensAmount]

  def log(msg: String, code: Long): Unit

}