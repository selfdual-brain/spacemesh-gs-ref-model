package io.spacemesh.platform

case class AccountWithMetadata[S](account: Account[S], balance: TokensAmount, nonce: Nonce) {

}
