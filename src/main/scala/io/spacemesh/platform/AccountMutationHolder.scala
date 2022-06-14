package io.spacemesh.platform

/**
 * Holds together an account instance and (mutable) balance.
 */
class AccountMutationHolder[S](account: Account[S]) {
  var balance: TokensAmount = 0
}
