package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.svm.Account
import io.spacemesh.blockchain.Ether

/**
 * Holds together an account instance and its coin balance.
 */
class AccountMutationHolder[S](account: Account[S]) {
  var businessPurseBalance: Ether = 0
  var gasPurseBalance: Ether = 0
}
