package io.spacemesh.execution

/**
 * API of the "host" (i.e. the smart contracts execution engine) exposed to every smart contract.
 *
 * Caution: The way this API is exposed to smart contracts must ensure that the host will know who is calling
 * (i.e. the code belonging to WHICH account is calling the host service).
 * Below in the docs we refer to the calling account as "the caller".
 */
trait HostAPI {

  /**
   * Executes native tokens transfer from the caller to given destination account.
   * If the requested amount of tokens exceeds current balance of the caller, current transaction will fail.
   *
   * @param destination account where to transfer tokens to
   * @param amount amount of tokens to be transferred.
   */
  def transfer(destination: AccountAddress, amount: Ether): Unit

  /**
   * Executes template method call.
   * Caution: target method must have @api visibility (otherwise TxAbort will be thrown).
   *
   * @param targetAccount address of the receiver (account)
   * @param attachedTransfer coins to be transferred with this call                      
   * @param method method index
   * @param methodArgs invocation arguments (to be passed as input to target method)
   * @tparam R the result will be type-casted do this type
   * @return return value from the method
   */
  def call[R](targetAccount: AccountAddress, attachedTransfer: Ether, method: Byte, methodArgs: Array[Byte]): R

  /**
   * Creates new account (as an instance of given template).
   *
   * @param template template to be used for the new account
   * @param immutableState immutable state (passed to the constructor of the account)
   * @tparam S type of immutable state
   * @return address of the newly-created account
   */
  def spawnNewAccount[S](template: TemplateAddress, immutableState: S): AccountAddress

  /**
   * Registers new template.
   *
   * @param template template definition (binary form)
   * @tparam S type of immutable state for accounts derived from this template
   * @return
   */
  def deployNewTemplate[S](template: Array[Byte]): TemplateAddress

  /**
   * Layer number of the block in the context of which the current transaction is being executed.
   * In other words it is height of the block since Genesis (Genesis block is layer 0).
   * This can be understood as a rough internal "blockchain time" concept.
   */
  def layerId(): LayerId

  /**
   * Current coins balance of the caller.
   */
  def balance(): Ether

  /**
   * Current balance of GasPurse.
   */
  def gasPaymentsReserve(): Ether

  /**
   * Transfers given amount from BusinessPurse to GasPurse.
   */
  def increaseGasPaymentsReserve(amount: Ether): Ether

  /**
   * Schedules withdrawal from GasPurse.
   * Caution: this will be executed as deferred operation (via withdrawals queue).
   */  
  def decreaseGasPaymentsReserve(amount: Ether): Ether
  
  /**
   * Current nonce value of the caller.
   */
  def nonce(): Nonce
  
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
   * Smart-contract level logging.
   * How this logging is handled (storage, presentation) is within engine responsibility.
   *
   * @param msg message
   * @param code business-level code (not to be interpreted by the engine)
   */
  def log(msg: String, code: Long): Unit

}
