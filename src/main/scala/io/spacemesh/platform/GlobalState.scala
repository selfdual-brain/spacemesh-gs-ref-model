package io.spacemesh.platform

import io.spacemesh.cryptography.Hash

class GlobalState(accounts: Map[AccountAddress, Account[?], templates: Map[TemplateAddress, Template[S]]]) {

  def hash: Hash
  def findAccount(addr: AccountAddress): Option[XAccount[?]]
  def registerAccount[S](account: XAccount[S]): Unit
  def hasTemplate(addr: TemplateAddress): Boolean
  def registerTemplate(addr: TemplateAddress): Unit
  def balanceOf(account: AccountAddress): TokensAmount
  def nonceOf(account: AccountAddress): Nonce
  def updateAccountBalance(account: AccountAddress, delta: TokensAmount): GlobalState
}
