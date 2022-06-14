package io.spacemesh.platform

type TokensAmount = Long
type Gas = Long
type GasPrice = Long
type LayerId = Long

type Template[S] = (AccountAddress, S, FFI) => Account[S]

/**
 * Annotation for marking "read-only" methods of a template (aka "getter" in Ethereum).
 * Such a method is not allowed to modify global state.
 * A client can call such a method to query global state - wrapping such call into a transaction is not needed.
 */
class query extends scala.annotation.StaticAnnotation
