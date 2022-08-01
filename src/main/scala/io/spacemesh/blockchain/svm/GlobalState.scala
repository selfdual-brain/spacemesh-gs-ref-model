package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain
import io.spacemesh.blockchain.svm.{Account, AccountAddress, AccountWithMetadata, Template, TemplateAddress}
import io.spacemesh.blockchain.transactions.Nonce
import io.spacemesh.cryptography.Hash
import io.spacemesh.blockchain.Ether

import scala.collection.immutable

class GlobalState(accounts: immutable.Map[AccountAddress, AccountWithMetadata[?]], templates: immutable.Map[TemplateAddress, Template[?]]) {
//  def hash: Hash = ???

  def findAccount(addr: AccountAddress): Option[Account[?]] = accounts.get(addr).map(wrapper => wrapper.account)

  def isTemplateKnown(addr: TemplateAddress): Boolean = templates.contains(addr)

  def balanceOf(addr: AccountAddress): Ether = accounts(addr).balance

  def nonceOf(addr: AccountAddress): Nonce = accounts(addr).nonce

  def updateAccount(addr: AccountAddress, balanceDelta: Ether, maybeNonce: Option[Nonce]): GlobalState = {
    accounts.get(addr) match {
      case Some(AccountWithMetadata(account, oldBalance, oldNonce)) =>
        if (oldBalance + balanceDelta <= 0)
          throw new RuntimeException(s"cannot apply balance-delta=$balanceDelta on account $addr because final balance would be negative")

        val newWrapper: AccountWithMetadata[?] = maybeNonce match {
          case Some(newNonce) =>
            if (!newNonce.canBeAppliedOver(oldNonce))
              throw new RuntimeException(s"illegal nonce update attempt for account $addr: old=$oldNonce new=$newNonce}")
            blockchain.svm.AccountWithMetadata(account, oldBalance + balanceDelta, newNonce.applyOver(oldNonce))
          case None =>
            blockchain.svm.AccountWithMetadata(account, oldBalance + balanceDelta, oldNonce)
        }

        val newAccountsSnapshot = accounts + (addr -> newWrapper)
        val newGlobalState = GlobalState(newAccountsSnapshot, templates)
        return newGlobalState
      case None =>
        throw new RuntimeException(s"account not found in the global state: $addr")
    }
  }

}
