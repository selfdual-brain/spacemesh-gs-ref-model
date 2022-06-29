package io.spacemesh.data_structures

/**
 * A view on an sub-interval of some indexed sequence.
 */
class Slice[E](targetSeq: IndexedSeq[E], start: Int, val length: Int) extends IndexedSeq[E] {
  val end: Int = start + length - 1
  assert(targetSeq.length >= end + 1)

  override def iterator: Iterator[E] = new Iterator[E] {
    var nextRelativeIndex: Int = 0

    override def hasNext: Boolean = start + nextRelativeIndex <= end

    override def next(): E = {
      if (this.hasNext) {
        val result: E = targetSeq(start + nextRelativeIndex)
        nextRelativeIndex += 1
        return result
      } else {
        throw new NoSuchElementException()
      }
    }
  }

  override def apply(i: Int): E =
    if (i < length)
      targetSeq(start + i)
    else
      throw new IndexOutOfBoundsException(i)

}