package io.spacemesh.data_structures

/**
 * A view on an sub-interval of some array.
 */
class Slice[E](targetArray: Array[E], start: Int, length: Int) extends Iterable[E] {
  val end: Int = start + length - 1
  assert(targetArray.length >= end + 1)

  override def iterator: Iterator[E] = new Iterator[E] {
    var nextRelativeIndex: Int = 0 
    
    override def hasNext: Boolean = start + nextRelativeIndex <= end

    override def next(): E = {
      if (this.hasNext) {
        val result: E = targetArray(start + nextRelativeIndex)
        nextRelativeIndex += 1
        return result
      } else {
        throw new NoSuchElementException()
      }
    }
  }
  
}
