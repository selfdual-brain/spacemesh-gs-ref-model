package io.spacemesh.blockchain.transactions

import io.spacemesh.blockchain.svm.AccountAddress

import scala.collection.immutable

trait Transaction {
  def binaryForm: immutable.Seq[Byte]

  def principal: AccountAddress

  def methodSelector: Byte

  def payload: immutable.Seq[Byte]

  def binarySize: Int = binaryForm.size
}
