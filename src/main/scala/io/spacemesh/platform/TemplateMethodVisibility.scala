package io.spacemesh.platform

/**
 * Annotations declaring public method visibility in templates.
 */
class TemplateMethodVisibility extends scala.annotation.StaticAnnotation
object TemplateMethodVisibility {

  /**
   * To be used by the engine only (not callable by clients).
   */
  class internal extends TemplateMethodVisibility

  /**
   * Callable by clients but only when principal of the transaction is the same as the account owner.
   * Not callable from other account.
   * @param selector numeric id of this method (between 0 and 255)
   */
  class local(selector: Int) extends TemplateMethodVisibility

  /**
   * Callable by clients and from other accounts.
   * @param selector numeric id of this method (between 0 and 255)
   */
  class api(selector: Int) extends TemplateMethodVisibility
}