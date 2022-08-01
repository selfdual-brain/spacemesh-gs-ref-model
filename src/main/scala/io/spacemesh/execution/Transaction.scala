package io.spacemesh.execution

import scala.collection.immutable

trait Transaction {
  def binaryForm: immutable.Seq[Byte]
  def principal: AccountAddress
  def methodSelector: Byte
  def payload: immutable.Seq[Byte]
  def binarySize: Int = binaryForm.size
}
