package special.sigma

import special.collection.{Col, ColOverArrayBuilder}
import org.bouncycastle.crypto.ec.CustomNamedCurves

import scala.reflect.ClassTag

trait ContractsTestkit {
  val R0 = 0.toByte;
  val R1 = 1.toByte;
  val R2 = 2.toByte;
  val R3 = 3.toByte;
  val R4 = 4.toByte;
  val R5 = 5.toByte;
  val R6 = 6.toByte;
  val R7 = 7.toByte;
  val R8 = 8.toByte;
  val R9 = 9.toByte;


  val Cols = new ColOverArrayBuilder
  val SigmaDsl = new TestSigmaDslBuilder
  val noRegisters = collection[AnyValue]()
  val noBytes = collection[Byte]()
  val noInputs = Array[Box]()
  val noOutputs = Array[Box]()
  val emptyAvlTree = new TestAvlTree(noBytes, 0, None, None, None)

  def collection[T:ClassTag](items: T*) = Cols.fromArray(items.toArray)

  def regs(m: Map[Byte, Any]): Col[AnyValue] = {
    val res = new Array[AnyValue](10)
    for ((id, v) <- m) {
      assert(res(id) == null, s"register $id is defined more then once")
      res(id) = new TestValue(v)
    }
    Cols.fromArray(res)
  }

  def contextVars(m: Map[Byte, Any]): Col[AnyValue] = {
    val maxKey = if (m.keys.isEmpty) 0 else m.keys.max
    val res = new Array[AnyValue](maxKey)
    for ((id, v) <- m) {
      val i = id - 1
      assert(res(i) == null, s"register $id is defined more then once")
      res(i) = new TestValue(v)
    }
    Cols.fromArray(res)
  }

  val AliceId = Array[Byte](1) // 0x0001
  def newAliceBox(id: Byte, value: Long, registers: Map[Int, Any] = Map()): Box = new TestBox(
    Cols.fromArray(Array[Byte](0, id)), value,
    Cols.fromArray(AliceId), noBytes, noBytes,
    regs(registers.map { case (k, v) => (k.toByte, v) })
  )

  def newContext(height: Long, self: Box, vars: AnyValue*): TestContext = {
    new TestContext(noInputs, noOutputs, height, self, emptyAvlTree, vars.toArray)
  }

  implicit class TestContextOps(ctx: TestContext) {
    def withInputs(inputs: Box*) =
      new TestContext(inputs.toArray, ctx.outputs, ctx.height, ctx.selfBox, emptyAvlTree, ctx.vars)
    def withOutputs(outputs: Box*) =
      new TestContext(ctx.inputs, outputs.toArray, ctx.height, ctx.selfBox, emptyAvlTree, ctx.vars)
    def withVariables(vars: Map[Int, Any]) =
      new TestContext(ctx.inputs, ctx.outputs, ctx.height, ctx.selfBox, emptyAvlTree,
        contextVars(vars.map { case (k, v) => (k.toByte, v) }).arr)
  }

  implicit def boolToSigma(b: Boolean): SigmaProp = TrivialSigma(b)
  
  case class NoEnvContract(condition: Context => Boolean) extends DefaultContract {
    def canOpen(ctx: Context): Boolean = condition(ctx)
  }
}
