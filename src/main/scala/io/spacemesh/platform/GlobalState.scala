package io.spacemesh.platform

import io.spacemesh.cryptography.Hash

trait GlobalState {
  def hash: Hash
  def findAccount(addr: AccountAddress): Option[Account[?]]
  def registerAccount[S](account: Account[S]): Unit
  def hasTemplate(addr: TemplateAddress): Boolean
  def registerTemplate(addr: TemplateAddress): Unit 
}