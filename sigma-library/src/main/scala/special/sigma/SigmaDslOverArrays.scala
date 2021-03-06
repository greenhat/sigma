package special.sigma {
  import scalan.OverloadHack.Overloaded1  // manual fix
  import scalan._

  trait SigmaDslOverArrays extends Base { self: SigmaLibrary =>
    import TestSigmaDslBuilder._;
    import SigmaProp._;
    import SigmaDslBuilder._;
    import SigmaContract._;
    import WOption._;
    import Col._;
    import Box._;
    import AvlTree._;
    import AnyValue._;
    import Context._;
    import ColOverArrayBuilder._;
    import MonoidBuilderInst._;
    import ConcreteCostedBuilder._;
    import CostModel._;
    import WBigInteger._;
    import WECPoint._;
    import DefaultSigma._;
    trait DefaultSigma extends SigmaProp {
      def builder: Rep[TestSigmaDslBuilder] = RTestSigmaDslBuilder();
      @NeverInline @OverloadId(value = "and_sigma") def &&(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      // manual fix
      @NeverInline @OverloadId(value = "and_bool") def &&(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "or_sigma") def ||(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      // manual fix
      @NeverInline @OverloadId(value = "or_bool") def ||(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline def lazyAnd(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def lazyOr(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke
    };
    trait DefaultContract extends SigmaContract {
      def builder: Rep[SigmaDslBuilder] = RTestSigmaDslBuilder()
    };
    abstract class TestBox(val id: Rep[Col[Byte]], val value: Rep[Long], val bytes: Rep[Col[Byte]], val bytesWithoutRef: Rep[Col[Byte]], val propositionBytes: Rep[Col[Byte]], val registers: Rep[Col[AnyValue]]) extends Box {
      def builder: Rep[TestSigmaDslBuilder] = RTestSigmaDslBuilder();
      @NeverInline def getReg[T](id: Rep[Int])(implicit cT: Elem[T]): Rep[WOption[T]] = delayInvoke;
      @NeverInline def cost: Rep[Int] = delayInvoke;
      @NeverInline def dataSize: Rep[Long] = delayInvoke;
      @NeverInline def deserialize[T](i: Rep[Int])(implicit cT: Elem[T]): Rep[WOption[T]] = delayInvoke;
      def tokens: Rep[Col[scala.Tuple2[Col[Byte], Long]]] = this.R2[Col[scala.Tuple2[Col[Byte], Long]]].get
    };
    abstract class TestAvlTree(val startingDigest: Rep[Col[Byte]], val keyLength: Rep[Int], val valueLengthOpt: Rep[WOption[Int]], val maxNumOperations: Rep[WOption[Int]], val maxDeletes: Rep[WOption[Int]]) extends AvlTree with Product with Serializable {
      def builder: Rep[TestSigmaDslBuilder] = RTestSigmaDslBuilder();
      @NeverInline def dataSize: Rep[Long] = delayInvoke;
      @NeverInline def cost: Rep[Int] = delayInvoke
    };
    abstract class TestValue[T](val value: Rep[T]) extends AnyValue {
      @NeverInline def dataSize: Rep[Long] = delayInvoke
    };
    abstract class TestContext(val inputs: Rep[WArray[Box]], val outputs: Rep[WArray[Box]], val height: Rep[Long], val selfBox: Rep[Box], val lastBlockUtxoRootHash: Rep[AvlTree], val vars: Rep[WArray[AnyValue]]) extends Context {
      def builder: Rep[TestSigmaDslBuilder] = RTestSigmaDslBuilder();
      @NeverInline def HEIGHT: Rep[Long] = delayInvoke;
      @NeverInline def SELF: Rep[Box] = delayInvoke;
      @NeverInline def INPUTS: Rep[Col[Box]] = delayInvoke;
      @NeverInline def OUTPUTS: Rep[Col[Box]] = delayInvoke;
      @NeverInline def LastBlockUtxoRootHash: Rep[AvlTree] = delayInvoke;
      @NeverInline def getVar[T](id: Rep[Byte])(implicit cT: Elem[T]): Rep[WOption[T]] = delayInvoke;
      @NeverInline def deserialize[T](id: Rep[Byte])(implicit cT: Elem[T]): Rep[WOption[T]] = delayInvoke;
      @NeverInline def cost: Rep[Int] = delayInvoke;
      @NeverInline def dataSize: Rep[Long] = delayInvoke
    };
    abstract class TestSigmaDslBuilder extends SigmaDslBuilder {
      def Cols: Rep[ColOverArrayBuilder] = RColOverArrayBuilder();
      def Monoids: Rep[MonoidBuilderInst] = RMonoidBuilderInst();
      def Costing: Rep[ConcreteCostedBuilder] = RConcreteCostedBuilder();
      @NeverInline def CostModel: Rep[CostModel] = delayInvoke;
      @NeverInline def verifyZK(proof: Rep[Thunk[SigmaProp]]): Rep[Boolean] = delayInvoke;
      @NeverInline def atLeast(bound: Rep[Int], props: Rep[Col[SigmaProp]]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def allOf(conditions: Rep[Col[Boolean]]): Rep[Boolean] = delayInvoke;
      @NeverInline def anyOf(conditions: Rep[Col[Boolean]]): Rep[Boolean] = delayInvoke;
      @NeverInline def allZK(proofs: Rep[Col[SigmaProp]]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def anyZK(proofs: Rep[Col[SigmaProp]]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def sigmaProp(b: Rep[Boolean]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def blake2b256(bytes: Rep[Col[Byte]]): Rep[Col[Byte]] = delayInvoke;
      @NeverInline def sha256(bytes: Rep[Col[Byte]]): Rep[Col[Byte]] = delayInvoke;
      @NeverInline def PubKey(base64String: Rep[String]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def byteArrayToBigInt(bytes: Rep[Col[Byte]]): Rep[WBigInteger] = delayInvoke;
      @NeverInline def longToByteArray(l: Rep[Long]): Rep[Col[Byte]] = delayInvoke;
      @NeverInline def proveDlog(g: Rep[WECPoint]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def proveDHTuple(g: Rep[WECPoint], h: Rep[WECPoint], u: Rep[WECPoint], v: Rep[WECPoint]): Rep[SigmaProp] = delayInvoke;
      @NeverInline def isMember(tree: Rep[AvlTree], key: Rep[Col[Byte]], proof: Rep[Col[Byte]]): Rep[Boolean] = delayInvoke;
      @NeverInline def treeLookup(tree: Rep[AvlTree], key: Rep[Col[Byte]], proof: Rep[Col[Byte]]): Rep[WOption[Col[Byte]]] = delayInvoke;
      @NeverInline def treeModifications(tree: Rep[AvlTree], operations: Rep[Col[Byte]], proof: Rep[Col[Byte]]): Rep[WOption[Col[Byte]]] = delayInvoke;
      @NeverInline def groupGenerator: Rep[WECPoint] = delayInvoke;
      @NeverInline def exponentiate(base: Rep[WECPoint], exponent: Rep[WBigInteger]): Rep[WECPoint] = delayInvoke
    };
    abstract class TrivialSigma(val _isValid: Rep[Boolean]) extends SigmaProp with DefaultSigma with Product with Serializable {
      @NeverInline def propBytes: Rep[Col[Byte]] = delayInvoke;
      @NeverInline def isValid: Rep[Boolean] = delayInvoke;
      @NeverInline @OverloadId(value = "and_sigma") override def &&(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "and_bool") override def &&(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "or_sigma") override def ||(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "or_bool") override def ||(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline override def lazyAnd(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke;
      @NeverInline override def lazyOr(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke
    };
    abstract class ProveDlogEvidence(val value: Rep[WECPoint]) extends SigmaProp with DefaultSigma with Product with Serializable {
      @NeverInline def propBytes: Rep[Col[Byte]] = delayInvoke;
      @NeverInline def isValid: Rep[Boolean] = delayInvoke;
      @NeverInline @OverloadId(value = "and_sigma") override def &&(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "and_bool") override def &&(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "or_sigma") override def ||(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "or_bool") override def ||(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline override def lazyAnd(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke;
      @NeverInline override def lazyOr(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke
    };
    abstract class ProveDHTEvidence(val gv: Rep[WECPoint], val hv: Rep[WECPoint], val uv: Rep[WECPoint], val vv: Rep[WECPoint]) extends SigmaProp with DefaultSigma with Product with Serializable {
      @NeverInline def propBytes: Rep[Col[Byte]] = delayInvoke;
      @NeverInline def isValid: Rep[Boolean] = delayInvoke;
      @NeverInline @OverloadId(value = "and_sigma") override def &&(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "and_bool") override def &&(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "or_sigma") override def ||(other: Rep[SigmaProp]): Rep[SigmaProp] = delayInvoke;
      @NeverInline @OverloadId(value = "or_bool") override def ||(other: Rep[Boolean])(implicit o: Overloaded1): Rep[SigmaProp] = delayInvoke;
      @NeverInline override def lazyAnd(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke;
      @NeverInline override def lazyOr(other: Rep[Thunk[SigmaProp]]): Rep[SigmaProp] = delayInvoke
    };
    trait DefaultSigmaCompanion;
    trait DefaultContractCompanion;
    trait TestBoxCompanion;
    trait TestAvlTreeCompanion;
    trait TestValueCompanion;
    trait TestContextCompanion;
    trait TestSigmaDslBuilderCompanion;
    trait TrivialSigmaCompanion;
    trait ProveDlogEvidenceCompanion;
    trait ProveDHTEvidenceCompanion
  }
}