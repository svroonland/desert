package io.github.vigoo.desert.zio

import io.github.vigoo.desert.codecs._
import io.github.vigoo.desert.zio.codecs._
import io.github.vigoo.desert.zio.syntax._
import zio.Chunk
import zio.test.Assertion._
import zio.test._
import zio.test.environment.TestEnvironment

object ZioCodecsSpec extends DefaultRunnableSpec {
  override def spec: ZSpec[TestEnvironment, Any] =
    suite("ZIO serialization codecs")(
      testM("correctly byte chunks") {
        val chunkIn = Chunk[Byte](1, 2, 4, 8, 16)
        for {
          serialized <- serializeToArray(chunkIn)
          chunkOut <- deserializeFromArray[Chunk[Byte]](serialized)
        } yield assert(chunkIn)(equalTo(chunkOut))
      },
      testM("correctly int chunks") {
        val chunkIn = Chunk[Int](1, 2, 4, 8, 16)
        for {
          serialized <- serializeToArray(chunkIn)
          chunkOut <- deserializeFromArray[Chunk[Int]](serialized)
        } yield assert(chunkIn)(equalTo(chunkOut))
      },
    )
}
