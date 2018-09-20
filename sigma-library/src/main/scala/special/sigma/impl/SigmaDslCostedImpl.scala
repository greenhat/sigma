package special.sigma

import scalan._
import scala.reflect.runtime.universe._
import scala.reflect._

package impl {
// Abs -----------------------------------
trait SigmaDslCostedDefs extends scalan.Scalan with SigmaDslCosted {
  self: SigmaLibrary =>
import IsoUR._
import Converter._
import SigmaDslBuilder._
import TestSigmaDslBuilder._
import CostModel._
import CostedCol._
import Box._
import CostedOption._
import CostedNone._
import CostedSome._
import WSpecialPredef._
import ConcreteCosted._
import Costed._
import CostedPrim._
import CostedBox._
import CostedAvlTree._
import Context._
import CostedSigmaObject._
import AnyValue._
import AvlTree._
import CostedContext._

object CostedSigmaObject extends EntityObject("CostedSigmaObject") {
  // entityProxy: single proxy for each type family
  implicit def proxyCostedSigmaObject[Val](p: Rep[CostedSigmaObject[Val]]): CostedSigmaObject[Val] = {
    proxyOps[CostedSigmaObject[Val]](p)(scala.reflect.classTag[CostedSigmaObject[Val]])
  }

  // familyElem
  class CostedSigmaObjectElem[Val, To <: CostedSigmaObject[Val]](implicit _eVal: Elem[Val])
    extends ConcreteCostedElem[Val, To] {
    override def eVal = _eVal

    override lazy val parent: Option[Elem[_]] = Some(concreteCostedElement(element[Val]))
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs("Val" -> (eVal -> scalan.util.Invariant))
    override lazy val tag = {
      implicit val tagVal = eVal.tag
      weakTypeTag[CostedSigmaObject[Val]].asInstanceOf[WeakTypeTag[To]]
    }
    override def convert(x: Rep[Def[_]]) = {
      val conv = fun {x: Rep[CostedSigmaObject[Val]] => convertCostedSigmaObject(x) }
      tryConvert(element[CostedSigmaObject[Val]], this, x, conv)
    }

    def convertCostedSigmaObject(x: Rep[CostedSigmaObject[Val]]): Rep[To] = {
      x.elem match {
        case _: CostedSigmaObjectElem[_, _] => x.asRep[To]
        case e => !!!(s"Expected $x to have CostedSigmaObjectElem[_, _], but got $e", x)
      }
    }
    override def getDefaultRep: Rep[To] = ???
  }

  implicit def costedSigmaObjectElement[Val](implicit eVal: Elem[Val]): Elem[CostedSigmaObject[Val]] =
    cachedElem[CostedSigmaObjectElem[Val, CostedSigmaObject[Val]]](eVal)

  implicit case object CostedSigmaObjectCompanionElem extends CompanionElem[CostedSigmaObjectCompanionCtor] {
    lazy val tag = weakTypeTag[CostedSigmaObjectCompanionCtor]
    protected def getDefaultRep = RCostedSigmaObject
  }

  abstract class CostedSigmaObjectCompanionCtor extends CompanionDef[CostedSigmaObjectCompanionCtor] with CostedSigmaObjectCompanion {
    def selfType = CostedSigmaObjectCompanionElem
    override def toString = "CostedSigmaObject"
  }
  implicit def proxyCostedSigmaObjectCompanionCtor(p: Rep[CostedSigmaObjectCompanionCtor]): CostedSigmaObjectCompanionCtor =
    proxyOps[CostedSigmaObjectCompanionCtor](p)

  lazy val RCostedSigmaObject: Rep[CostedSigmaObjectCompanionCtor] = new CostedSigmaObjectCompanionCtor {
  }

  object CostedSigmaObjectMethods {
    object dsl {
      def unapply(d: Def[_]): Option[Rep[CostedSigmaObject[Val]] forSome {type Val}] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedSigmaObjectElem[_, _]] && method.getName == "dsl" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedSigmaObject[Val]] forSome {type Val}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedSigmaObject[Val]] forSome {type Val}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object Operations {
      def unapply(d: Def[_]): Option[Rep[CostedSigmaObject[Val]] forSome {type Val}] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedSigmaObjectElem[_, _]] && method.getName == "Operations" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedSigmaObject[Val]] forSome {type Val}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedSigmaObject[Val]] forSome {type Val}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object costBoxes {
      def unapply(d: Def[_]): Option[(Rep[CostedSigmaObject[Val]], Rep[Col[Box]]) forSome {type Val}] = d match {
        case MethodCall(receiver, method, Seq(bs, _*), _) if receiver.elem.isInstanceOf[CostedSigmaObjectElem[_, _]] && method.getName == "costBoxes" =>
          Some((receiver, bs)).asInstanceOf[Option[(Rep[CostedSigmaObject[Val]], Rep[Col[Box]]) forSome {type Val}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[(Rep[CostedSigmaObject[Val]], Rep[Col[Box]]) forSome {type Val}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object costColWithConstSizedItem {
      def unapply(d: Def[_]): Option[(Rep[CostedSigmaObject[Val]], Rep[Col[T]], Rep[Long]) forSome {type Val; type T}] = d match {
        case MethodCall(receiver, method, Seq(xs, itemSize, _*), _) if receiver.elem.isInstanceOf[CostedSigmaObjectElem[_, _]] && method.getName == "costColWithConstSizedItem" =>
          Some((receiver, xs, itemSize)).asInstanceOf[Option[(Rep[CostedSigmaObject[Val]], Rep[Col[T]], Rep[Long]) forSome {type Val; type T}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[(Rep[CostedSigmaObject[Val]], Rep[Col[T]], Rep[Long]) forSome {type Val; type T}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object costOption {
      def unapply(d: Def[_]): Option[(Rep[CostedSigmaObject[Val]], Rep[WOption[T]], Rep[Int]) forSome {type Val; type T}] = d match {
        case MethodCall(receiver, method, Seq(opt, opCost, _*), _) if receiver.elem.isInstanceOf[CostedSigmaObjectElem[_, _]] && method.getName == "costOption" =>
          Some((receiver, opt, opCost)).asInstanceOf[Option[(Rep[CostedSigmaObject[Val]], Rep[WOption[T]], Rep[Int]) forSome {type Val; type T}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[(Rep[CostedSigmaObject[Val]], Rep[WOption[T]], Rep[Int]) forSome {type Val; type T}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }
  }

  object CostedSigmaObjectCompanionMethods {
  }
} // of object CostedSigmaObject
  registerEntityObject("CostedSigmaObject", CostedSigmaObject)

object CostedContext extends EntityObject("CostedContext") {
  case class CostedContextCtor
      (override val ctx: Rep[Context])
    extends CostedContext(ctx) with Def[CostedContext] {
    override lazy val eVal: Elem[Context] = implicitly[Elem[Context]]
    lazy val selfType = element[CostedContext]
  }
  // elem for concrete class
  class CostedContextElem(val iso: Iso[CostedContextData, CostedContext])
    extends ConcreteCostedElem[Context, CostedContext]
    with ConcreteElem[CostedContextData, CostedContext] {
    override lazy val parent: Option[Elem[_]] = Some(concreteCostedElement(contextElement))
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
    override def convertConcreteCosted(x: Rep[ConcreteCosted[Context]]) = // Converter is not generated by meta
!!!("Cannot convert from ConcreteCosted to CostedContext: missing fields List(ctx)")
    override def getDefaultRep = RCostedContext(element[Context].defaultRepValue)
    override lazy val tag = {
      weakTypeTag[CostedContext]
    }
  }

  // state representation type
  type CostedContextData = Context

  // 3) Iso for concrete class
  class CostedContextIso
    extends EntityIso[CostedContextData, CostedContext] with Def[CostedContextIso] {
    private lazy val _safeFrom = fun { p: Rep[CostedContext] => p.ctx }
    override def from(p: Rep[CostedContext]) =
      tryConvert[CostedContext, Context](eTo, eFrom, p, _safeFrom)
    override def to(p: Rep[Context]) = {
      val ctx = p
      RCostedContext(ctx)
    }
    lazy val eFrom = element[Context]
    lazy val eTo = new CostedContextElem(self)
    lazy val selfType = new CostedContextIsoElem
    def productArity = 0
    def productElement(n: Int) = ???
  }
  case class CostedContextIsoElem() extends Elem[CostedContextIso] {
    def getDefaultRep = reifyObject(new CostedContextIso())
    lazy val tag = {
      weakTypeTag[CostedContextIso]
    }
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
  }
  // 4) constructor and deconstructor
  class CostedContextCompanionCtor extends CompanionDef[CostedContextCompanionCtor] with CostedContextCompanion {
    def selfType = CostedContextCompanionElem
    override def toString = "CostedContextCompanion"

    @scalan.OverloadId("fromFields")
    def apply(ctx: Rep[Context]): Rep[CostedContext] =
      mkCostedContext(ctx)

    def unapply(p: Rep[ConcreteCosted[Context]]) = unmkCostedContext(p)
  }
  lazy val CostedContextRep: Rep[CostedContextCompanionCtor] = new CostedContextCompanionCtor
  lazy val RCostedContext: CostedContextCompanionCtor = proxyCostedContextCompanion(CostedContextRep)
  implicit def proxyCostedContextCompanion(p: Rep[CostedContextCompanionCtor]): CostedContextCompanionCtor = {
    proxyOps[CostedContextCompanionCtor](p)
  }

  implicit case object CostedContextCompanionElem extends CompanionElem[CostedContextCompanionCtor] {
    lazy val tag = weakTypeTag[CostedContextCompanionCtor]
    protected def getDefaultRep = CostedContextRep
  }

  implicit def proxyCostedContext(p: Rep[CostedContext]): CostedContext =
    proxyOps[CostedContext](p)

  implicit class ExtendedCostedContext(p: Rep[CostedContext]) {
    def toData: Rep[CostedContextData] = {
      isoCostedContext.from(p)
    }
  }

  // 5) implicit resolution of Iso
  implicit def isoCostedContext: Iso[CostedContextData, CostedContext] =
    reifyObject(new CostedContextIso())

  def mkCostedContext
    (ctx: Rep[Context]): Rep[CostedContext] = {
    new CostedContextCtor(ctx)
  }
  def unmkCostedContext(p: Rep[ConcreteCosted[Context]]) = p.elem.asInstanceOf[Elem[_]] match {
    case _: CostedContextElem @unchecked =>
      Some((p.asRep[CostedContext].ctx))
    case _ =>
      None
  }

    object CostedContextMethods {
    object OUTPUTS {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "OUTPUTS" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object INPUTS {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "INPUTS" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object HEIGHT {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "HEIGHT" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object SELF {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "SELF" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object LastBlockUtxoRootHash {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "LastBlockUtxoRootHash" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object getVar {
      def unapply(d: Def[_]): Option[(Rep[CostedContext], Rep[Byte], Elem[T], Elem[T]) forSome {type T}] = d match {
        case MethodCall(receiver, method, Seq(id, cT, emT, _*), _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "getVar" =>
          Some((receiver, id, cT, emT)).asInstanceOf[Option[(Rep[CostedContext], Rep[Byte], Elem[T], Elem[T]) forSome {type T}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[(Rep[CostedContext], Rep[Byte], Elem[T], Elem[T]) forSome {type T}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object deserialize {
      def unapply(d: Def[_]): Option[(Rep[CostedContext], Rep[Byte], Elem[T], Elem[T]) forSome {type T}] = d match {
        case MethodCall(receiver, method, Seq(id, cT, emT, _*), _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "deserialize" =>
          Some((receiver, id, cT, emT)).asInstanceOf[Option[(Rep[CostedContext], Rep[Byte], Elem[T], Elem[T]) forSome {type T}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[(Rep[CostedContext], Rep[Byte], Elem[T], Elem[T]) forSome {type T}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object value {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "value" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object cost {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "cost" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object dataSize {
      def unapply(d: Def[_]): Option[Rep[CostedContext]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedContextElem] && method.getName == "dataSize" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedContext]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedContext]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }
  }

  object CostedContextCompanionMethods {
  }
} // of object CostedContext
  registerEntityObject("CostedContext", CostedContext)

object CostedBox extends EntityObject("CostedBox") {
  case class CostedBoxCtor
      (override val box: Rep[Box])
    extends CostedBox(box) with Def[CostedBox] {
    override lazy val eVal: Elem[Box] = implicitly[Elem[Box]]
    lazy val selfType = element[CostedBox]
  }
  // elem for concrete class
  class CostedBoxElem(val iso: Iso[CostedBoxData, CostedBox])
    extends ConcreteCostedElem[Box, CostedBox]
    with ConcreteElem[CostedBoxData, CostedBox] {
    override lazy val parent: Option[Elem[_]] = Some(concreteCostedElement(boxElement))
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
    override def convertConcreteCosted(x: Rep[ConcreteCosted[Box]]) = // Converter is not generated by meta
!!!("Cannot convert from ConcreteCosted to CostedBox: missing fields List(box)")
    override def getDefaultRep = RCostedBox(element[Box].defaultRepValue)
    override lazy val tag = {
      weakTypeTag[CostedBox]
    }
  }

  // state representation type
  type CostedBoxData = Box

  // 3) Iso for concrete class
  class CostedBoxIso
    extends EntityIso[CostedBoxData, CostedBox] with Def[CostedBoxIso] {
    private lazy val _safeFrom = fun { p: Rep[CostedBox] => p.box }
    override def from(p: Rep[CostedBox]) =
      tryConvert[CostedBox, Box](eTo, eFrom, p, _safeFrom)
    override def to(p: Rep[Box]) = {
      val box = p
      RCostedBox(box)
    }
    lazy val eFrom = element[Box]
    lazy val eTo = new CostedBoxElem(self)
    lazy val selfType = new CostedBoxIsoElem
    def productArity = 0
    def productElement(n: Int) = ???
  }
  case class CostedBoxIsoElem() extends Elem[CostedBoxIso] {
    def getDefaultRep = reifyObject(new CostedBoxIso())
    lazy val tag = {
      weakTypeTag[CostedBoxIso]
    }
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
  }
  // 4) constructor and deconstructor
  class CostedBoxCompanionCtor extends CompanionDef[CostedBoxCompanionCtor] with CostedBoxCompanion {
    def selfType = CostedBoxCompanionElem
    override def toString = "CostedBoxCompanion"

    @scalan.OverloadId("fromFields")
    def apply(box: Rep[Box]): Rep[CostedBox] =
      mkCostedBox(box)

    def unapply(p: Rep[ConcreteCosted[Box]]) = unmkCostedBox(p)
  }
  lazy val CostedBoxRep: Rep[CostedBoxCompanionCtor] = new CostedBoxCompanionCtor
  lazy val RCostedBox: CostedBoxCompanionCtor = proxyCostedBoxCompanion(CostedBoxRep)
  implicit def proxyCostedBoxCompanion(p: Rep[CostedBoxCompanionCtor]): CostedBoxCompanionCtor = {
    proxyOps[CostedBoxCompanionCtor](p)
  }

  implicit case object CostedBoxCompanionElem extends CompanionElem[CostedBoxCompanionCtor] {
    lazy val tag = weakTypeTag[CostedBoxCompanionCtor]
    protected def getDefaultRep = CostedBoxRep
  }

  implicit def proxyCostedBox(p: Rep[CostedBox]): CostedBox =
    proxyOps[CostedBox](p)

  implicit class ExtendedCostedBox(p: Rep[CostedBox]) {
    def toData: Rep[CostedBoxData] = {
      isoCostedBox.from(p)
    }
  }

  // 5) implicit resolution of Iso
  implicit def isoCostedBox: Iso[CostedBoxData, CostedBox] =
    reifyObject(new CostedBoxIso())

  def mkCostedBox
    (box: Rep[Box]): Rep[CostedBox] = {
    new CostedBoxCtor(box)
  }
  def unmkCostedBox(p: Rep[ConcreteCosted[Box]]) = p.elem.asInstanceOf[Elem[_]] match {
    case _: CostedBoxElem @unchecked =>
      Some((p.asRep[CostedBox].box))
    case _ =>
      None
  }

    object CostedBoxMethods {
    object id {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "id" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object valueCosted {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "valueCosted" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object bytes {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "bytes" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object bytesWithoutRef {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "bytesWithoutRef" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object propositionBytes {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "propositionBytes" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object registers {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "registers" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object deserialize {
      def unapply(d: Def[_]): Option[(Rep[CostedBox], Rep[Int], Elem[T], Elem[T]) forSome {type T}] = d match {
        case MethodCall(receiver, method, Seq(id, cT, emT, _*), _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "deserialize" =>
          Some((receiver, id, cT, emT)).asInstanceOf[Option[(Rep[CostedBox], Rep[Int], Elem[T], Elem[T]) forSome {type T}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[(Rep[CostedBox], Rep[Int], Elem[T], Elem[T]) forSome {type T}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object getReg {
      def unapply(d: Def[_]): Option[(Rep[CostedBox], Rep[Int], Elem[T], Elem[T]) forSome {type T}] = d match {
        case MethodCall(receiver, method, Seq(id, cT, emT, _*), _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "getReg" =>
          Some((receiver, id, cT, emT)).asInstanceOf[Option[(Rep[CostedBox], Rep[Int], Elem[T], Elem[T]) forSome {type T}]]
        case _ => None
      }
      def unapply(exp: Sym): Option[(Rep[CostedBox], Rep[Int], Elem[T], Elem[T]) forSome {type T}] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object value {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "value" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object cost {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "cost" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object dataSize {
      def unapply(d: Def[_]): Option[Rep[CostedBox]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedBoxElem] && method.getName == "dataSize" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedBox]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedBox]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }
  }

  object CostedBoxCompanionMethods {
  }
} // of object CostedBox
  registerEntityObject("CostedBox", CostedBox)

object CostedAvlTree extends EntityObject("CostedAvlTree") {
  case class CostedAvlTreeCtor
      (override val tree: Rep[AvlTree])
    extends CostedAvlTree(tree) with Def[CostedAvlTree] {
    override lazy val eVal: Elem[AvlTree] = implicitly[Elem[AvlTree]]
    lazy val selfType = element[CostedAvlTree]
  }
  // elem for concrete class
  class CostedAvlTreeElem(val iso: Iso[CostedAvlTreeData, CostedAvlTree])
    extends ConcreteCostedElem[AvlTree, CostedAvlTree]
    with ConcreteElem[CostedAvlTreeData, CostedAvlTree] {
    override lazy val parent: Option[Elem[_]] = Some(concreteCostedElement(avlTreeElement))
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
    override def convertConcreteCosted(x: Rep[ConcreteCosted[AvlTree]]) = // Converter is not generated by meta
!!!("Cannot convert from ConcreteCosted to CostedAvlTree: missing fields List(tree)")
    override def getDefaultRep = RCostedAvlTree(element[AvlTree].defaultRepValue)
    override lazy val tag = {
      weakTypeTag[CostedAvlTree]
    }
  }

  // state representation type
  type CostedAvlTreeData = AvlTree

  // 3) Iso for concrete class
  class CostedAvlTreeIso
    extends EntityIso[CostedAvlTreeData, CostedAvlTree] with Def[CostedAvlTreeIso] {
    private lazy val _safeFrom = fun { p: Rep[CostedAvlTree] => p.tree }
    override def from(p: Rep[CostedAvlTree]) =
      tryConvert[CostedAvlTree, AvlTree](eTo, eFrom, p, _safeFrom)
    override def to(p: Rep[AvlTree]) = {
      val tree = p
      RCostedAvlTree(tree)
    }
    lazy val eFrom = element[AvlTree]
    lazy val eTo = new CostedAvlTreeElem(self)
    lazy val selfType = new CostedAvlTreeIsoElem
    def productArity = 0
    def productElement(n: Int) = ???
  }
  case class CostedAvlTreeIsoElem() extends Elem[CostedAvlTreeIso] {
    def getDefaultRep = reifyObject(new CostedAvlTreeIso())
    lazy val tag = {
      weakTypeTag[CostedAvlTreeIso]
    }
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
  }
  // 4) constructor and deconstructor
  class CostedAvlTreeCompanionCtor extends CompanionDef[CostedAvlTreeCompanionCtor] with CostedAvlTreeCompanion {
    def selfType = CostedAvlTreeCompanionElem
    override def toString = "CostedAvlTreeCompanion"

    @scalan.OverloadId("fromFields")
    def apply(tree: Rep[AvlTree]): Rep[CostedAvlTree] =
      mkCostedAvlTree(tree)

    def unapply(p: Rep[ConcreteCosted[AvlTree]]) = unmkCostedAvlTree(p)
  }
  lazy val CostedAvlTreeRep: Rep[CostedAvlTreeCompanionCtor] = new CostedAvlTreeCompanionCtor
  lazy val RCostedAvlTree: CostedAvlTreeCompanionCtor = proxyCostedAvlTreeCompanion(CostedAvlTreeRep)
  implicit def proxyCostedAvlTreeCompanion(p: Rep[CostedAvlTreeCompanionCtor]): CostedAvlTreeCompanionCtor = {
    proxyOps[CostedAvlTreeCompanionCtor](p)
  }

  implicit case object CostedAvlTreeCompanionElem extends CompanionElem[CostedAvlTreeCompanionCtor] {
    lazy val tag = weakTypeTag[CostedAvlTreeCompanionCtor]
    protected def getDefaultRep = CostedAvlTreeRep
  }

  implicit def proxyCostedAvlTree(p: Rep[CostedAvlTree]): CostedAvlTree =
    proxyOps[CostedAvlTree](p)

  implicit class ExtendedCostedAvlTree(p: Rep[CostedAvlTree]) {
    def toData: Rep[CostedAvlTreeData] = {
      isoCostedAvlTree.from(p)
    }
  }

  // 5) implicit resolution of Iso
  implicit def isoCostedAvlTree: Iso[CostedAvlTreeData, CostedAvlTree] =
    reifyObject(new CostedAvlTreeIso())

  def mkCostedAvlTree
    (tree: Rep[AvlTree]): Rep[CostedAvlTree] = {
    new CostedAvlTreeCtor(tree)
  }
  def unmkCostedAvlTree(p: Rep[ConcreteCosted[AvlTree]]) = p.elem.asInstanceOf[Elem[_]] match {
    case _: CostedAvlTreeElem @unchecked =>
      Some((p.asRep[CostedAvlTree].tree))
    case _ =>
      None
  }

    object CostedAvlTreeMethods {
    object startingDigest {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "startingDigest" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object keyLength {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "keyLength" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object valueLengthOpt {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "valueLengthOpt" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object maxNumOperations {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "maxNumOperations" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object maxDeletes {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "maxDeletes" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object value {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "value" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object cost {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "cost" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }

    object dataSize {
      def unapply(d: Def[_]): Option[Rep[CostedAvlTree]] = d match {
        case MethodCall(receiver, method, _, _) if receiver.elem.isInstanceOf[CostedAvlTreeElem] && method.getName == "dataSize" =>
          Some(receiver).asInstanceOf[Option[Rep[CostedAvlTree]]]
        case _ => None
      }
      def unapply(exp: Sym): Option[Rep[CostedAvlTree]] = exp match {
        case Def(d) => unapply(d)
        case _ => None
      }
    }
  }

  object CostedAvlTreeCompanionMethods {
  }
} // of object CostedAvlTree
  registerEntityObject("CostedAvlTree", CostedAvlTree)

  registerModule(SigmaDslCostedModule)
}

object SigmaDslCostedModule extends scalan.ModuleInfo("special.sigma", "SigmaDslCosted")
}

trait SigmaDslCostedModule extends special.sigma.impl.SigmaDslCostedDefs {self: SigmaLibrary =>}