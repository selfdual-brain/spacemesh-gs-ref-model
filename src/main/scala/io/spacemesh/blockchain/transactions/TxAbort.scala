package io.spacemesh.blockchain.transactions

import io.spacemesh.blockchain.svm.AccountAddress

class TxAbort(account: AccountAddress, msg: String) extends RuntimeException {

}
