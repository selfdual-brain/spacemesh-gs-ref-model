package io.spacemesh.standard_templates

import io.spacemesh.cryptography.Signature
import io.spacemesh.data_structures.Slice
import io.spacemesh.execution.*

case class StdTxWithSingleSig(
                             rawTx: IndexedSeq[Byte],
                             methodArgumentsRaw: IndexedSeq[Byte],
                             signedPart: IndexedSeq[Byte],
                             signature: Signature,
                             nonce: Nonce,
                             minLayer: Long,
                             maxLayer: Long,
                             maxGas: Gas,
                             gasPrice: GasPrice,
                             ) extends ParsedTransaction {

}


