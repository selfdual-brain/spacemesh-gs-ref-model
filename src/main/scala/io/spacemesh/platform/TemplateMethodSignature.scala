package io.spacemesh.platform

abstract class TemplateMethodSignature {
}
object TemplateMethodSignature {
  case object CustomSerialization extends TemplateMethodSignature
  case class Schema(argument: Datatype, result: Option[Datatype]) extends TemplateMethodSignature
}
