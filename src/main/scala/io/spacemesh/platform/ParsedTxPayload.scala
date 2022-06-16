package io.spacemesh.platform

case class ParsedTxPayload(
  methodArgumentsBlob: Array[Byte],
  nonce: Nonce,
  minLayer: Long,
  maxLayer: Long,
  maxGas: Gas,
  gasPrice: Ether,
)
                     
