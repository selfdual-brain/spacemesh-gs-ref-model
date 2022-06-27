package io.spacemesh.cryptography

//Implementation of "fake" cryptographic signatures.
//
//IMPLEMENTATION REMARKS
//- For the research prototype we just need something that has API similar to real signatures.
//- We structurally mimic ed25519 algorithm, i.e. keys are 32-bytes long and signature are 64-bytes long

case class PublicKey(value: Hash)

case class PrivateKey(value: Hash)

case class KeyPair(publicKey: PublicKey, privateKey: PrivateKey)

case class Signature(value: Hash) {
  assert (value.bytes.length == 64)

  override def equals(obj: Any): Boolean = {
    obj match {
      case Signature(x) => x == value
      case other => false
    }
  }
}

object SignaturesLibrary {

  def sign(message: Array[Byte], keyPair: KeyPair):  Signature =
    calculateSignature(message, keyPair.publicKey)

  def verifySignature(message: Array[Byte], signature: Signature, publicKey: PublicKey): Boolean =
    calculateSignature(message, publicKey) == signature

  private def calculateSignature(message: Array[Byte], publicKey: PublicKey): Signature = {
    val digester = new RealSha256Digester
    digester.field(publicKey.value.bytes)
    digester.field("|")
    digester.field(message)
    val buffer = new Array[Byte](64)
    val publicKeyAsBytes: Array[Byte] = publicKey.value.bytes
    val messageHashAsBytes: Array[Byte] = digester.generateHash().bytes
    Array.copy(publicKeyAsBytes, 0, buffer, 0, 32)
    Array.copy(messageHashAsBytes, 0, buffer, 32, 32)
    return Signature(value = Hash(buffer))
  }

}