package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.svm.{AccountAddress, AccountMutationHolder, GlobalState}

import scala.collection.mutable

/**
 * While executing a single transaction we use GlobalStateIncrementalUpdateBuffer instance to keep track
 * of all accounts that were mutated.
 * Every time a mutating method is invoked via inter-accounts invocation, the engine checks if
 * the target account is already registered as part of incremental update.
 * If not yet - the target account is cloned and added to the buffer.
 *
 * This solution makes it possible to model blockchain accounts storage as instance variables in Scala.
 * Instead of using a Merkle-tree for accounts storage representation (as would be happening in
 * a "real" implementation of smart contracts platform) we just have accounts as instances
 * of plain Scala classes. However, we want the global state to be immutable, so our approach is to
 * clone account instance and track all the mutated account instances within the scope of one transaction.
 */
class GlobalStateIncrementalUpdateBuffer(originalGlobalState: GlobalState, txPrincipal: AccountAddress) {
  val mutatedAccounts = new mutable.HashMap[AccountAddress, AccountMutationHolder[?]]
  val deployedTemplates = new mutable.HashMap[TemplateAddress, Template[?]]
}
