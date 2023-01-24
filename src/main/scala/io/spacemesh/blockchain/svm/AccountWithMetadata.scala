package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.transactions.Nonce
import io.spacemesh.blockchain.Ether

case class AccountWithMetadata[S](
                                   accountInstance: Account[S],
                                   nonce: Nonce,
                                   businessPurseBalance: Ether,
                                   gasPurseBalance: Ether
                                 ) {

}
