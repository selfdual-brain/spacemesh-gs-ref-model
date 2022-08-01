package io.spacemesh.blockchain.svm

import io.spacemesh.blockchain.svm.TemplateMethodSignature

abstract class TemplateMethodSignature {
}
object TemplateMethodSignature {
  case object CustomSerialization extends TemplateMethodSignature
  case class Schema(argument: Datatype, result: Option[Datatype]) extends TemplateMethodSignature
}
