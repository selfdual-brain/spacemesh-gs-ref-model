package io.spacemesh.blockchain.svm

case class Template[S](
                        accountsFactory: (AccountAddress, S, HostAPI) => Account[S],
                        apiMetadata: Int => TemplateMethodSignature
                      ) {

}
