package io.spacemesh.execution

case class Template[S](
                        accountsFactory: (AccountAddress, S, HostAPI) => Account[S],
                        apiMetadata: Int => TemplateMethodSignature
                      ) {

}
