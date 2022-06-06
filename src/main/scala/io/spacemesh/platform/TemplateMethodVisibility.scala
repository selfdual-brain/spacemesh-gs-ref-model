package io.spacemesh.platform

class TemplateMethodVisibility extends scala.annotation.StaticAnnotation
object TemplateMethodVisibility {
  class internal extends TemplateMethodVisibility
  class local(selector: Int) extends TemplateMethodVisibility
  class api(selector: Int) extends TemplateMethodVisibility
}