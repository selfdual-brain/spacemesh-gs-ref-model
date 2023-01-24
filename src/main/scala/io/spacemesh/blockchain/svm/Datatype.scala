package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.svm.Datatype

/**
 * Encoding of "mini schema language" used for defining signatures of template methods. 
 */
sealed abstract class Datatype {}
object Datatype {

  //atomic types
  case object TBoolean extends Datatype
  case object TString extends Datatype
  case class TUnsignedInt(length: IntegerFieldSize) extends Datatype
  case class TSignedInt(length: IntegerFieldSize) extends Datatype
  case object TCoinAmount extends Datatype
  case object TGasPrice extends Datatype
  case object TLayerId extends Datatype
  case object TAccount extends Datatype
  case object TTemplate extends Datatype
  case class TAccountWithBehaviour(interface: TemplateAddress) extends Datatype

  //composite types
  case class TStruct(fields: Array[(String, Datatype)]) extends Datatype
  case class TUnion(elements: Array[Datatype]) extends Datatype
  case class TOption(value: Datatype) extends Datatype
  case class TArray(size: Int, element: Datatype) extends Datatype
  case class TSeq(element: Datatype) extends Datatype
}

sealed abstract class IntegerFieldSize {
  def sizeInBytes: Int
}
object IntegerFieldSize {
  case object Byte extends IntegerFieldSize {
    override def sizeInBytes: Int = 1
  }

  case object Word16 extends IntegerFieldSize {
    override def sizeInBytes: Int = 2
  }

  case object Word32 extends IntegerFieldSize {
    override def sizeInBytes: Int = 4
  }

  case object Word64 extends IntegerFieldSize {
    override def sizeInBytes: Int = 8
  }

  case object Word128 extends IntegerFieldSize {
    override def sizeInBytes: Int = 16
  }

  case object Word256 extends IntegerFieldSize {
    override def sizeInBytes: Int = 32
  }
}
