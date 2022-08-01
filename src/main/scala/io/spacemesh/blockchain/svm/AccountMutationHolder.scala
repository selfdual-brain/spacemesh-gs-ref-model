package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.svm.Account
import io.spacemesh.blockchain.Ether

/**
 * Holds together an account instance and (mutable) balance.
 */
class AccountMutationHolder[S](account: Account[S]) {
  var balance: Ether = 0
}
