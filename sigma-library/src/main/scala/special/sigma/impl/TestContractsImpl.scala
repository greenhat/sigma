package special.sigma

import scalan._
import scala.reflect.runtime.universe._
import scala.reflect._

package impl {
// Abs -----------------------------------
trait TestContractsDefs extends scalan.Scalan with TestContracts {
  self: SigmaLibrary =>
import IsoUR._
import Converter._
import CrowdFunding._
import DefaultContract._
import DemurrageCurrency._
import CrowdFundingContract._
import DemurrageCurrencyContract._
import SigmaProp._ // manual fix

object CrowdFundingContract extends EntityObject("CrowdFundingContract") {
  case class CrowdFundingContractCtor
      (override val deadline: Rep[Long], override val minToRaise: Rep[Long], override val backerPubKey: Rep[SigmaProp], override val projectPubKey: Rep[SigmaProp])
    extends CrowdFundingContract(deadline, minToRaise, backerPubKey, projectPubKey) with Def[CrowdFundingContract] {
    lazy val selfType = element[CrowdFundingContract]
  }
  // elem for concrete class
  class CrowdFundingContractElem(val iso: Iso[CrowdFundingContractData, CrowdFundingContract])
    extends CrowdFundingElem[CrowdFundingContract]
    with ConcreteElem[CrowdFundingContractData, CrowdFundingContract] {
    override lazy val parent: Option[Elem[_]] = Some(crowdFundingElement)
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
    override def convertCrowdFunding(x: Rep[CrowdFunding]) = RCrowdFundingContract(x.deadline, x.minToRaise, x.backerPubKey, x.projectPubKey)
    override def getDefaultRep = RCrowdFundingContract(0l, 0l, element[SigmaProp].defaultRepValue, element[SigmaProp].defaultRepValue)
    override lazy val tag = {
      weakTypeTag[CrowdFundingContract]
    }
  }

  // state representation type
  type CrowdFundingContractData = (Long, (Long, (SigmaProp, SigmaProp)))

  // 3) Iso for concrete class
  class CrowdFundingContractIso
    extends EntityIso[CrowdFundingContractData, CrowdFundingContract] with Def[CrowdFundingContractIso] {
    private lazy val _safeFrom = fun { p: Rep[CrowdFundingContract] => (p.deadline, p.minToRaise, p.backerPubKey, p.projectPubKey) }
    override def from(p: Rep[CrowdFundingContract]) =
      tryConvert[CrowdFundingContract, (Long, (Long, (SigmaProp, SigmaProp)))](eTo, eFrom, p, _safeFrom)
    override def to(p: Rep[(Long, (Long, (SigmaProp, SigmaProp)))]) = {
      val Pair(deadline, Pair(minToRaise, Pair(backerPubKey, projectPubKey))) = p
      RCrowdFundingContract(deadline, minToRaise, backerPubKey, projectPubKey)
    }
    lazy val eFrom = pairElement(element[Long], pairElement(element[Long], pairElement(element[SigmaProp], element[SigmaProp])))
    lazy val eTo = new CrowdFundingContractElem(self)
    lazy val selfType = new CrowdFundingContractIsoElem
    def productArity = 0
    def productElement(n: Int) = ???
  }
  case class CrowdFundingContractIsoElem() extends Elem[CrowdFundingContractIso] {
    def getDefaultRep = reifyObject(new CrowdFundingContractIso())
    lazy val tag = {
      weakTypeTag[CrowdFundingContractIso]
    }
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
  }
  // 4) constructor and deconstructor
  class CrowdFundingContractCompanionCtor extends CompanionDef[CrowdFundingContractCompanionCtor] with CrowdFundingContractCompanion {
    def selfType = CrowdFundingContractCompanionElem
    override def toString = "CrowdFundingContractCompanion"
    @scalan.OverloadId("fromData")
    def apply(p: Rep[CrowdFundingContractData]): Rep[CrowdFundingContract] = {
      isoCrowdFundingContract.to(p)
    }

    @scalan.OverloadId("fromFields")
    def apply(deadline: Rep[Long], minToRaise: Rep[Long], backerPubKey: Rep[SigmaProp], projectPubKey: Rep[SigmaProp]): Rep[CrowdFundingContract] =
      mkCrowdFundingContract(deadline, minToRaise, backerPubKey, projectPubKey)

    def unapply(p: Rep[CrowdFunding]) = unmkCrowdFundingContract(p)
  }
  lazy val CrowdFundingContractRep: Rep[CrowdFundingContractCompanionCtor] = new CrowdFundingContractCompanionCtor
  lazy val RCrowdFundingContract: CrowdFundingContractCompanionCtor = proxyCrowdFundingContractCompanion(CrowdFundingContractRep)
  implicit def proxyCrowdFundingContractCompanion(p: Rep[CrowdFundingContractCompanionCtor]): CrowdFundingContractCompanionCtor = {
    if (p.rhs.isInstanceOf[CrowdFundingContractCompanionCtor])
      p.rhs.asInstanceOf[CrowdFundingContractCompanionCtor]
    else
      proxyOps[CrowdFundingContractCompanionCtor](p)
  }

  implicit case object CrowdFundingContractCompanionElem extends CompanionElem[CrowdFundingContractCompanionCtor] {
    lazy val tag = weakTypeTag[CrowdFundingContractCompanionCtor]
    protected def getDefaultRep = CrowdFundingContractRep
  }

  implicit def proxyCrowdFundingContract(p: Rep[CrowdFundingContract]): CrowdFundingContract =
    proxyOps[CrowdFundingContract](p)

  implicit class ExtendedCrowdFundingContract(p: Rep[CrowdFundingContract]) {
    def toData: Rep[CrowdFundingContractData] = {
      isoCrowdFundingContract.from(p)
    }
  }

  // 5) implicit resolution of Iso
  implicit def isoCrowdFundingContract: Iso[CrowdFundingContractData, CrowdFundingContract] =
    reifyObject(new CrowdFundingContractIso())

  def mkCrowdFundingContract
    (deadline: Rep[Long], minToRaise: Rep[Long], backerPubKey: Rep[SigmaProp], projectPubKey: Rep[SigmaProp]): Rep[CrowdFundingContract] = {
    new CrowdFundingContractCtor(deadline, minToRaise, backerPubKey, projectPubKey)
  }
  def unmkCrowdFundingContract(p: Rep[CrowdFunding]) = p.elem.asInstanceOf[Elem[_]] match {
    case _: CrowdFundingContractElem @unchecked =>
      Some((asRep[CrowdFundingContract](p).deadline, asRep[CrowdFundingContract](p).minToRaise, asRep[CrowdFundingContract](p).backerPubKey, asRep[CrowdFundingContract](p).projectPubKey))
    case _ =>
      None
  }

    object CrowdFundingContractMethods {
  }

  object CrowdFundingContractCompanionMethods {
  }
} // of object CrowdFundingContract
  registerEntityObject("CrowdFundingContract", CrowdFundingContract)

object DemurrageCurrencyContract extends EntityObject("DemurrageCurrencyContract") {
  case class DemurrageCurrencyContractCtor
      (override val demurragePeriod: Rep[Long], override val demurrageCost: Rep[Long], override val regScript: Rep[SigmaProp])
    extends DemurrageCurrencyContract(demurragePeriod, demurrageCost, regScript) with Def[DemurrageCurrencyContract] {
    lazy val selfType = element[DemurrageCurrencyContract]
  }
  // elem for concrete class
  class DemurrageCurrencyContractElem(val iso: Iso[DemurrageCurrencyContractData, DemurrageCurrencyContract])
    extends DemurrageCurrencyElem[DemurrageCurrencyContract]
    with ConcreteElem[DemurrageCurrencyContractData, DemurrageCurrencyContract] {
    override lazy val parent: Option[Elem[_]] = Some(demurrageCurrencyElement)
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
    override def convertDemurrageCurrency(x: Rep[DemurrageCurrency]) = RDemurrageCurrencyContract(x.demurragePeriod, x.demurrageCost, x.regScript)
    override def getDefaultRep = RDemurrageCurrencyContract(0l, 0l, element[SigmaProp].defaultRepValue)
    override lazy val tag = {
      weakTypeTag[DemurrageCurrencyContract]
    }
  }

  // state representation type
  type DemurrageCurrencyContractData = (Long, (Long, SigmaProp))

  // 3) Iso for concrete class
  class DemurrageCurrencyContractIso
    extends EntityIso[DemurrageCurrencyContractData, DemurrageCurrencyContract] with Def[DemurrageCurrencyContractIso] {
    private lazy val _safeFrom = fun { p: Rep[DemurrageCurrencyContract] => (p.demurragePeriod, p.demurrageCost, p.regScript) }
    override def from(p: Rep[DemurrageCurrencyContract]) =
      tryConvert[DemurrageCurrencyContract, (Long, (Long, SigmaProp))](eTo, eFrom, p, _safeFrom)
    override def to(p: Rep[(Long, (Long, SigmaProp))]) = {
      val Pair(demurragePeriod, Pair(demurrageCost, regScript)) = p
      RDemurrageCurrencyContract(demurragePeriod, demurrageCost, regScript)
    }
    lazy val eFrom = pairElement(element[Long], pairElement(element[Long], element[SigmaProp]))
    lazy val eTo = new DemurrageCurrencyContractElem(self)
    lazy val selfType = new DemurrageCurrencyContractIsoElem
    def productArity = 0
    def productElement(n: Int) = ???
  }
  case class DemurrageCurrencyContractIsoElem() extends Elem[DemurrageCurrencyContractIso] {
    def getDefaultRep = reifyObject(new DemurrageCurrencyContractIso())
    lazy val tag = {
      weakTypeTag[DemurrageCurrencyContractIso]
    }
    override def buildTypeArgs = super.buildTypeArgs ++ TypeArgs()
  }
  // 4) constructor and deconstructor
  class DemurrageCurrencyContractCompanionCtor extends CompanionDef[DemurrageCurrencyContractCompanionCtor] with DemurrageCurrencyContractCompanion {
    def selfType = DemurrageCurrencyContractCompanionElem
    override def toString = "DemurrageCurrencyContractCompanion"
    @scalan.OverloadId("fromData")
    def apply(p: Rep[DemurrageCurrencyContractData]): Rep[DemurrageCurrencyContract] = {
      isoDemurrageCurrencyContract.to(p)
    }

    @scalan.OverloadId("fromFields")
    def apply(demurragePeriod: Rep[Long], demurrageCost: Rep[Long], regScript: Rep[SigmaProp]): Rep[DemurrageCurrencyContract] =
      mkDemurrageCurrencyContract(demurragePeriod, demurrageCost, regScript)

    def unapply(p: Rep[DemurrageCurrency]) = unmkDemurrageCurrencyContract(p)
  }
  lazy val DemurrageCurrencyContractRep: Rep[DemurrageCurrencyContractCompanionCtor] = new DemurrageCurrencyContractCompanionCtor
  lazy val RDemurrageCurrencyContract: DemurrageCurrencyContractCompanionCtor = proxyDemurrageCurrencyContractCompanion(DemurrageCurrencyContractRep)
  implicit def proxyDemurrageCurrencyContractCompanion(p: Rep[DemurrageCurrencyContractCompanionCtor]): DemurrageCurrencyContractCompanionCtor = {
    if (p.rhs.isInstanceOf[DemurrageCurrencyContractCompanionCtor])
      p.rhs.asInstanceOf[DemurrageCurrencyContractCompanionCtor]
    else
      proxyOps[DemurrageCurrencyContractCompanionCtor](p)
  }

  implicit case object DemurrageCurrencyContractCompanionElem extends CompanionElem[DemurrageCurrencyContractCompanionCtor] {
    lazy val tag = weakTypeTag[DemurrageCurrencyContractCompanionCtor]
    protected def getDefaultRep = DemurrageCurrencyContractRep
  }

  implicit def proxyDemurrageCurrencyContract(p: Rep[DemurrageCurrencyContract]): DemurrageCurrencyContract =
    proxyOps[DemurrageCurrencyContract](p)

  implicit class ExtendedDemurrageCurrencyContract(p: Rep[DemurrageCurrencyContract]) {
    def toData: Rep[DemurrageCurrencyContractData] = {
      isoDemurrageCurrencyContract.from(p)
    }
  }

  // 5) implicit resolution of Iso
  implicit def isoDemurrageCurrencyContract: Iso[DemurrageCurrencyContractData, DemurrageCurrencyContract] =
    reifyObject(new DemurrageCurrencyContractIso())

  def mkDemurrageCurrencyContract
    (demurragePeriod: Rep[Long], demurrageCost: Rep[Long], regScript: Rep[SigmaProp]): Rep[DemurrageCurrencyContract] = {
    new DemurrageCurrencyContractCtor(demurragePeriod, demurrageCost, regScript)
  }
  def unmkDemurrageCurrencyContract(p: Rep[DemurrageCurrency]) = p.elem.asInstanceOf[Elem[_]] match {
    case _: DemurrageCurrencyContractElem @unchecked =>
      Some((asRep[DemurrageCurrencyContract](p).demurragePeriod, asRep[DemurrageCurrencyContract](p).demurrageCost, asRep[DemurrageCurrencyContract](p).regScript))
    case _ =>
      None
  }

    object DemurrageCurrencyContractMethods {
  }

  object DemurrageCurrencyContractCompanionMethods {
  }
} // of object DemurrageCurrencyContract
  registerEntityObject("DemurrageCurrencyContract", DemurrageCurrencyContract)

  registerModule(TestContractsModule)
}

object TestContractsModule extends scalan.ModuleInfo("special.sigma", "TestContracts")
}

trait TestContractsModule extends special.sigma.impl.TestContractsDefs {self: SigmaLibrary =>}
