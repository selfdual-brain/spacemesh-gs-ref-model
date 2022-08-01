package io.spacemesh.execution

import io.spacemesh.cryptography.Hash

case class AccountAddress(value: Hash) {
  override def toString: String = s"account:$value"
}
