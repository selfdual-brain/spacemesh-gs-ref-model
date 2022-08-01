package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.svm.Datatype

/**
 * Encoding of "mini schema language" used for defining signatures of template methods. 
 */
sealed abstract class Datatype {}
object Datatype {
  case object TBoolean extends Datatype
  case object TString extends Datatype
  case object TByte extends Datatype
  case object TShort extends Datatype
  case object TInt extends Datatype
  case object TLong extends Datatype
  case object TAccount extends Datatype
  case object TEtherAmount extends Datatype
  case class TTuple(elements: Array[Datatype]) extends Datatype
  case class TOption(value: Datatype) extends Datatype
  case class TArray(size: Int, element: Datatype) extends Datatype
  case class TSeq(element: Datatype) extends Datatype
  case class TEnum(elements: Datatype) extends Datatype
}
