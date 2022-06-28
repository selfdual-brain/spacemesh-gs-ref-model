package io.spacemesh.standard_templates

import io.spacemesh.cryptography.Signature
import io.spacemesh.data_structures.Slice
import io.spacemesh.platform.*

case class StdTxWithSingleSig(
                             rawTx: Array[Byte],
                             methodArgumentsBlob: Slice[Byte],
                             signedPart: Slice[Byte],
                             signature: Signature,
                             nonce: Nonce,
                             minLayer: Long,
                             maxLayer: Long,
                             maxGas: Gas,
                             gasPrice: GasPrice,
                             ) extends ParsedTxPayload


