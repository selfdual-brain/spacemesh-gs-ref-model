package io.spacemesh.platform

import scala.collection.mutable

trait CloningSupport[+SelfType] {
  self: SelfType =>

  def createDetachedCopy(): SelfType
}

object CloningSupport {

  def deepCopyOfMapViaDetachedCopy[K,V <: CloningSupport[V]](coll: mutable.Map[K,V]): mutable.Map[K,V] = coll map { case (k,v) => (k, v.createDetachedCopy().asInstanceOf[V])}

  //code below does not compile, unfortunately (how to fix this ???)
  //  def deepCopyOfMapViaClone[K,V <: {def clone(): Object}](coll: mutable.Map[K,V]): mutable.Map[K,V] = coll map { case (k,v) => (k, v.clone().asInstanceOf[V])}

}
