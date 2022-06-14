package io.spacemesh.platform

import io.spacemesh.cryptography.Hash
import scala.collection.immutable

class GlobalState(accounts: immutable.Map[AccountAddress, AccountWithMetadata[?]], templates: immutable.Map[TemplateAddress, Template[?]]) {
  def hash: Hash = ???
  def findAccount(addr: AccountAddress): Option[Account[?]] = ???
  def registerAccount[S](account: Account[S]): Unit = ???
  def isTemplateKnown(addr: TemplateAddress): Boolean = ???
  def registerTemplate[S](addr: TemplateAddress, template: Template[S]): Unit = ???
  def balanceOf(account: AccountAddress): TokensAmount = ???
  def nonceOf(account: AccountAddress): Nonce = ???
  def updateAccountBalance(account: AccountAddress, delta: TokensAmount): GlobalState = ???
}
