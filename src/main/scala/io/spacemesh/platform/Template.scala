package io.spacemesh.platform

case class Template[S](
                        accountsFactory: (AccountAddress, S, FFI) => Account[S],
                        apiMetadata: Int => TemplateMethodSignature
                      ) {

}
