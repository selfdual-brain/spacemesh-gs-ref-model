package io.spacemesh.platform

import io.spacemesh.cryptography.Hash
import scala.collection.immutable

class GlobalState(accounts: immutable.Map[AccountAddress, AccountWithMetadata[?]], templates: immutable.Map[TemplateAddress, Template[?]]) {
//  def hash: Hash = ???

  def findAccount(addr: AccountAddress): Option[Account[?]] = accounts.get(addr).map(wrapper => wrapper.account)

//  def registerAccount[S](account: Account[S]): Unit = ???

  def isTemplateKnown(addr: TemplateAddress): Boolean = templates.contains(addr)

//  def registerTemplate[S](addr: TemplateAddress, template: Template[S]): Unit = ???

  def balanceOf(addr: AccountAddress): TokensAmount = accounts(addr).balance

  def nonceOf(addr: AccountAddress): Nonce = accounts(addr).nonce

//  def updateAccountBalance(account: AccountAddress, delta: TokensAmount): GlobalState = ???
}
