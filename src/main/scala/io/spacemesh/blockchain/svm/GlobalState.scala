package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain
import io.spacemesh.blockchain.svm.{Account, AccountAddress, AccountWithMetadata, Template, TemplateAddress}
import io.spacemesh.blockchain.transactions.Nonce
import io.spacemesh.cryptography.Hash
import io.spacemesh.blockchain.Ether

import scala.collection.immutable

class GlobalState(accounts: immutable.Map[AccountAddress, AccountWithMetadata[?]], templates: immutable.Map[TemplateAddress, Template[?]]) {

  def findAccount(addr: AccountAddress): Option[Account[?]] = accounts.get(addr).map(wrapper => wrapper.accountInstance)

  def isTemplateKnown(addr: TemplateAddress): Boolean = templates.contains(addr)

  def businessPurseBalanceOf(addr: AccountAddress): Ether = accounts(addr).businessPurseBalance

  def gasPurseBalanceOf(addr: AccountAddress): Ether = accounts(addr).gasPurseBalance

  def nonceOf(addr: AccountAddress): Nonce = accounts(addr).nonce

  def updateAccount(addr: AccountAddress, bBalanceDelta: Ether, gBalanceDelta: Ether, newNonceOption: Option[Nonce]): GlobalState = {
    accounts.get(addr) match {
      case Some(AccountWithMetadata(account, nonceOld, bBalanceOld, gBalanceOld)) =>
        if (bBalanceOld + bBalanceDelta < 0)
          throw new RuntimeException(s"cannot apply b-balance-delta=$bBalanceDelta on account $addr because final balance would be negative")
        val nonce: Nonce = newNonceOption match {
          case Some(newNonce) => newNonce.applyOver(nonceOld)
          case None => nonceOld
        }
        val newWrapper: AccountWithMetadata[?] = AccountWithMetadata(account, nonce, bBalanceOld + bBalanceDelta, gBalanceOld + gBalanceDelta)
        val newAccountsSnapshot = accounts + (addr -> newWrapper)
        val newGlobalState = GlobalState(newAccountsSnapshot, templates)
        return newGlobalState
      case None =>
        throw new RuntimeException(s"account not found in the global state: $addr")
    }
  }

}
