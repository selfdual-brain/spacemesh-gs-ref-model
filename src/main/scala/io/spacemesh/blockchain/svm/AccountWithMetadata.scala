package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.transactions.Nonce
import io.spacemesh.blockchain.Ether

case class AccountWithMetadata[S](account: Account[S], balance: Ether, nonce: Nonce) {

}
