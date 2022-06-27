package io.spacemesh.platform

case class Template[S](
                        accountsFactory: (AccountAddress, S, HostAPI) => Account[S],
                        apiMetadata: Int => TemplateMethodSignature
                      ) {

}
