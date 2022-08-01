package io.spacemesh.execution

/**
 * Holds together an account instance and (mutable) balance.
 */
class AccountMutationHolder[S](account: Account[S]) {
  var balance: Ether = 0
}
