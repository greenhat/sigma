package wrappers.org.bouncycastle.math.ec

import scalan._
import impl._
import special.sigma.wrappers.WrappersModule
import scala.reflect.runtime.universe._
import scala.reflect._

package impl {
  import java.lang.reflect.Method  // manual fix
  import java.math.BigInteger  // manual fix

  import org.bouncycastle.math.ec.ECPoint  // manual fix
  import special.sigma.wrappers.ECPointWrapSpec // manual fix

  // Abs -----------------------------------
trait WECPointsDefs extends scalan.Scalan with WECPoints {
  self: WrappersModule =>
import IsoUR._
import Converter._
import WECPoint._
import WArray._

object WECPoint extends EntityObject("WECPoint") {
  // entityConst: single const for each entity
  import Liftables._
  import scala.reflect.{ClassTag, classTag}

  case class WECPointConst(
        constValue: ECPoint
      ) extends WECPoint with LiftedConst[ECPoint, WECPoint] {
    val liftable: Liftable[ECPoint, WECPoint] = LiftableECPoint
    val selfType: Elem[WECPoint] = liftable.eW
    private val thisClass = classOf[WECPoint]

    def add(x$1: Rep[WECPoint]): Rep[WECPoint] = {
      asRep[WECPoint](mkMethodCall(self,
        thisClass.getMethod("add", classOf[Sym]),
        List(x$1),
        true, element[WECPoint]))
    }

    def multiply(x$1: Rep[WBigInteger]): Rep[WECPoint] = {
      asRep[WECPoint](mkMethodCall(self,
        thisClass.getMethod("multiply", classOf[Sym]),
        List(x$1),
        true, element[WECPoint]))
    }

    def getEncoded(x$1: Rep[Boolean]): Rep[WArray[Byte]] = {
      asRep[WArray[Byte]](mkMethodCall(self,
        thisClass.getMethod("getEncoded", classOf[Sym]),
        List(x$1),
        true, element[WArray[Byte]]))
    }
  }

  implicit object LiftableECPoint
    extends Liftable[ECPoint, WECPoint] {
    lazy val eW: Elem[WECPoint] = wECPointElement
    lazy val sourceClassTag: ClassTag[ECPoint] = {
      classTag[ECPoint]
    }
    def lift(x: ECPoint): Rep[WECPoint] = WECPointConst(x)
    def unlift(w: Rep[WECPoint]): ECPoint = w match {
      case Def(WECPointConst(x: ECPoint))
            => x.asInstanceOf[ECPoint]
      case _ => unliftError(w)
    }
  }

  private val _ECPointWrapSpec = new ECPointWrapSpec
  // entityProxy: single proxy for each type family
  implicit def proxyWECPoint(p: Rep[WECPoint]): WECPoint = {
    if (p.rhs.isInstanceOf[WECPoint@unchecked]) p.rhs.asInstanceOf[WECPoint]
    else
      proxyOps[WECPoint](p)(scala.reflect.classTag[WECPoint])
  }

  // familyElem
  class WECPointElem[To <: WECPoint]
    extends EntityElem[To] {
    override val liftable = LiftableECPoint.asLiftable[ECPoint, To]

    override protected def collectMethods: Map[java.lang.reflect.Method, MethodDesc] = {
      super.collectMethods ++
        Elem.declaredWrapperMethods(_ECPointWrapSpec, classOf[WECPoint], Set(
        "add", "multiply", "getEncoded"
        ))
    }

    lazy val parent: Option[Elem[_]] = None
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
    override lazy val tag = {
      weakTypeTag[WECPoint].asInstanceOf[WeakTypeTag[To]]
    }
    override def convert(x: Rep[Def[_]]) = {
      val conv = fun {x: Rep[WECPoint] => convertWECPoint(x) }
      tryConvert(element[WECPoint], this, x, conv)
    }

    def convertWECPoint(x: Rep[WECPoint]): Rep[To] = {
      x.elem match {
        case _: WECPointElem[_] => asRep[To](x)
        case e => !!!(s"Expected $x to have WECPointElem[_], but got $e", x)
      }
    }
    override def getDefaultRep: Rep[To] = ???
  }

  implicit def wECPointElement: Elem[WECPoint] =
    cachedElem[WECPointElem[WECPoint]]()

  implicit case object WECPointCompanionElem extends CompanionElem[WECPointCompanionCtor] {
    lazy val tag = weakTypeTag[WECPointCompanionCtor]
    protected def getDefaultRep = RWECPoint
  }

  abstract class WECPointCompanionCtor extends CompanionDef[WECPointCompanionCtor] with WECPointCompanion {
    def selfType = WECPointCompanionElem
    override def toString = "WECPoint"
  }
  implicit def proxyWECPointCompanionCtor(p: Rep[WECPointCompanionCtor]): WECPointCompanionCtor =
    proxyOps[WECPointCompanionCtor](p)

  lazy val RWECPoint: Rep[WECPointCompanionCtor] = new WECPointCompanionCtor {
    private val thisClass = classOf[WECPointCompanion]
  }

  object WECPointMethods {
    object add {
      def unapply(d: Def[_]): Nullable[(Rep[WECPoint], Rep[WECPoint])] = d match {
        case MethodCall(receiver, method, args, _) if receiver.elem.isInstanceOf[WECPointElem[_]] && method.getName == "add" =>
          val res = (receiver, args(0))
          Nullable(res).asInstanceOf[Nullable[(Rep[WECPoint], Rep[WECPoint])]]
        case _ => Nullable.None
      }
      def unapply(exp: Sym): Nullable[(Rep[WECPoint], Rep[WECPoint])] = exp match {
        case Def(d) => unapply(d)
        case _ => Nullable.None
      }
    }

    object multiply {
      def unapply(d: Def[_]): Nullable[(Rep[WECPoint], Rep[WBigInteger])] = d match {
        case MethodCall(receiver, method, args, _) if receiver.elem.isInstanceOf[WECPointElem[_]] && method.getName == "multiply" =>
          val res = (receiver, args(0))
          Nullable(res).asInstanceOf[Nullable[(Rep[WECPoint], Rep[WBigInteger])]]
        case _ => Nullable.None
      }
      def unapply(exp: Sym): Nullable[(Rep[WECPoint], Rep[WBigInteger])] = exp match {
        case Def(d) => unapply(d)
        case _ => Nullable.None
      }
    }

    object getEncoded {
      def unapply(d: Def[_]): Nullable[(Rep[WECPoint], Rep[Boolean])] = d match {
        case MethodCall(receiver, method, args, _) if receiver.elem.isInstanceOf[WECPointElem[_]] && method.getName == "getEncoded" =>
          val res = (receiver, args(0))
          Nullable(res).asInstanceOf[Nullable[(Rep[WECPoint], Rep[Boolean])]]
        case _ => Nullable.None
      }
      def unapply(exp: Sym): Nullable[(Rep[WECPoint], Rep[Boolean])] = exp match {
        case Def(d) => unapply(d)
        case _ => Nullable.None
      }
    }
  }

  object WECPointCompanionMethods {
  }
} // of object WECPoint
  registerEntityObject("WECPoint", WECPoint)

  registerModule(WECPointsModule)
}

object WECPointsModule extends scalan.ModuleInfo("wrappers.org.bouncycastle.math.ec", "WECPoints")
}

trait WECPointsModule extends wrappers.org.bouncycastle.math.ec.impl.WECPointsDefs {self: WrappersModule =>}
