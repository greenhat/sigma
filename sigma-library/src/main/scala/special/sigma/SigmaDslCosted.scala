package special.sigma {
  import scalan._

  trait SigmaDslCosted extends Base { self: SigmaLibrary =>
    import SigmaDslBuilder._;
    import TestSigmaDslBuilder._;
    import CostModel._;
    import CostedCol._;
    import Col._     // manual fix
    import ColBuilder._     // manual fix
    import Box._;
    import CostedOption._;
    import CostedNone._;
    import CostedSome._;
    import WOption._;  // manual fix
    import WSpecialPredef._;
    import ConcreteCosted._;
    import Costed._;
    import CostedBuilder._;  // manual fix
    import CostedPrim._;
    import CostedBox._;
    import CostedAvlTree._;
    import Context._;
    import CostedSigmaObject._;
    import AnyValue._;
    import AvlTree._;
    trait CostedSigmaObject[Val] extends ConcreteCosted[Val] {
      implicit def eVal: Elem[Val];
      def dsl: Rep[SigmaDslBuilder] = RTestSigmaDslBuilder();
      def Operations: Rep[CostModel] = CostedSigmaObject.this.dsl.CostModel;
      def costBoxes(bs: Rep[Col[Box]]): Rep[CostedCol[Box]] = {
        val len: Rep[Int] = bs.length;
        val perItemCost: Rep[Int] = CostedSigmaObject.this.Operations.AccessBox;
        val costs: Rep[Col[Int]] = CostedSigmaObject.this.dsl.Cols.replicate[Int](len, perItemCost);
        val sizes: Rep[Col[Long]] = bs.map[Long](fun(((b: Rep[Box]) => b.dataSize)));
        val valuesCost: Rep[Int] = CostedSigmaObject.this.Operations.CollectionConst;
        RCostedCol(bs, costs, sizes, valuesCost)
      };
      def costColWithConstSizedItem[T](xs: Rep[Col[T]], itemSize: Rep[Long]): Rep[CostedCol[T]] = {
        val len: Rep[Int] = xs.length;
        // manual fix (div)
        val perItemCost: Rep[Int] = len.*(itemSize.toInt).div(CostedSigmaObject.this.Operations.AccessKiloByteOfData);
        val costs: Rep[Col[Int]] = CostedSigmaObject.this.dsl.Cols.replicate[Int](len, perItemCost);
        val sizes: Rep[Col[Long]] = CostedSigmaObject.this.dsl.Cols.replicate[Long](len, itemSize);
        val valueCost: Rep[Int] = CostedSigmaObject.this.Operations.CollectionConst;
        RCostedCol(xs, costs, sizes, valueCost)
      };
      // manual fix
      def costOption[T](opt: Rep[WOption[T]], opCost: Rep[Int]): Rep[CostedOption[T]] = {
        implicit val cT: Elem[T] = opt.elem.eA
        opt.fold[CostedOption[T]](Thunk(RCostedNone(opCost)(cT)), fun(((x: Rep[T]) => RCostedSome(CostedSigmaObject.this.dsl.Costing.costedValue[T](x, RWSpecialPredef.some[Int](opCost))))))
      }
    };
    abstract class CostedContext(val ctx: Rep[Context]) extends ConcreteCosted[Context] with CostedSigmaObject[Context] {
      def OUTPUTS: Rep[CostedCol[Box]] = this.costBoxes(CostedContext.this.ctx.OUTPUTS);
      def INPUTS: Rep[CostedCol[Box]] = this.costBoxes(CostedContext.this.ctx.INPUTS);
      def HEIGHT: Rep[Costed[Long]] = {
        val cost: Rep[Int] = CostedContext.this.Operations.SelectField;
        RCostedPrim(CostedContext.this.ctx.HEIGHT, cost, toRep(8L.asInstanceOf[Long]))
      };
      def SELF: Rep[CostedBox] = RCostedBox(CostedContext.this.ctx.SELF, CostedContext.this.Operations.AccessBox);
      def LastBlockUtxoRootHash: Rep[CostedAvlTree] = RCostedAvlTree(CostedContext.this.ctx.LastBlockUtxoRootHash);
      def getVar[T](id: Rep[Byte])(implicit cT: Elem[T]): Rep[CostedOption[T]] = {
        val opt: Rep[WOption[T]] = CostedContext.this.ctx.getVar[T](id);
        CostedContext.this.costOption[T](opt, CostedContext.this.Operations.GetVar)
      };
      def deserialize[T](id: Rep[Byte])(implicit cT: Elem[T]): Rep[CostedOption[T]] = {
        val opt: Rep[WOption[T]] = CostedContext.this.ctx.deserialize[T](id);
        CostedContext.this.costOption[T](opt, CostedContext.this.Operations.DeserializeVar)
      };
      def value: Rep[Context] = CostedContext.this.ctx;
      def cost: Rep[Int] = CostedContext.this.ctx.cost;
      def dataSize: Rep[Long] = CostedContext.this.ctx.dataSize
    };
    abstract class CostedBox(val box: Rep[Box], val cost: Rep[Int]) extends ConcreteCosted[Box] with CostedSigmaObject[Box] {
      def id: Rep[CostedCol[Byte]] = CostedBox.this.costColWithConstSizedItem[Byte](CostedBox.this.box.id, toRep(1L.asInstanceOf[Long]));
      def valueCosted: Rep[Costed[Long]] = {
        val cost: Rep[Int] = CostedBox.this.Operations.SelectField;
        RCostedPrim(CostedBox.this.box.value, cost, toRep(8L.asInstanceOf[Long]))
      };
      def bytes: Rep[CostedCol[Byte]] = CostedBox.this.costColWithConstSizedItem[Byte](CostedBox.this.box.bytes, toRep(1L.asInstanceOf[Long]));
      def bytesWithoutRef: Rep[CostedCol[Byte]] = CostedBox.this.costColWithConstSizedItem[Byte](CostedBox.this.box.bytesWithoutRef, toRep(1L.asInstanceOf[Long]));
      def propositionBytes: Rep[CostedCol[Byte]] = CostedBox.this.costColWithConstSizedItem[Byte](CostedBox.this.box.propositionBytes, toRep(1L.asInstanceOf[Long]));
      def registers: Rep[CostedCol[AnyValue]] = {
        val len: Rep[Int] = CostedBox.this.box.registers.length;
        val costs: Rep[Col[Int]] = CostedBox.this.dsl.Cols.replicate[Int](len, CostedBox.this.Operations.AccessBox);
        val sizes: Rep[Col[Long]] = CostedBox.this.box.registers.map[Long](fun(((o: Rep[AnyValue]) => o.dataSize)));
        RCostedCol(CostedBox.this.box.registers, costs, sizes, CostedBox.this.Operations.CollectionConst)
      };
      def deserialize[T](id: Rep[Int])(implicit cT: Elem[T]): Rep[CostedOption[T]] = {
        val opt: Rep[WOption[T]] = CostedBox.this.box.deserialize[T](id);
        CostedBox.this.costOption[T](opt, CostedBox.this.Operations.DeserializeRegister)
      };
      def getReg[T](id: Rep[Int])(implicit cT: Elem[T]): Rep[CostedOption[T]] = {
        val opt: Rep[WOption[T]] = CostedBox.this.box.getReg[T](id);
        CostedBox.this.costOption[T](opt, CostedBox.this.Operations.GetRegister)
      };
      def value: Rep[Box] = CostedBox.this.box;
      def dataSize: Rep[Long] = CostedBox.this.box.dataSize
    };
    abstract class CostedAvlTree(val tree: Rep[AvlTree]) extends ConcreteCosted[AvlTree] with CostedSigmaObject[AvlTree] {
      def startingDigest: Rep[CostedCol[Byte]] = CostedAvlTree.this.costColWithConstSizedItem[Byte](CostedAvlTree.this.tree.startingDigest, toRep(1L.asInstanceOf[Long]));
      def keyLength: Rep[Costed[Int]] = RCostedPrim(CostedAvlTree.this.tree.keyLength, CostedAvlTree.this.Operations.SelectField, toRep(4L.asInstanceOf[Long]));
      def valueLengthOpt: Rep[CostedOption[Int]] = CostedAvlTree.this.costOption[Int](CostedAvlTree.this.tree.valueLengthOpt, CostedAvlTree.this.Operations.SelectField);
      def maxNumOperations: Rep[CostedOption[Int]] = CostedAvlTree.this.costOption[Int](CostedAvlTree.this.tree.maxNumOperations, CostedAvlTree.this.Operations.SelectField);
      def maxDeletes: Rep[CostedOption[Int]] = CostedAvlTree.this.costOption[Int](CostedAvlTree.this.tree.maxDeletes, CostedAvlTree.this.Operations.SelectField);
      def value: Rep[AvlTree] = CostedAvlTree.this.tree;
      def cost: Rep[Int] = CostedAvlTree.this.tree.cost;
      def dataSize: Rep[Long] = CostedAvlTree.this.tree.dataSize
    };
    trait CostedSigmaObjectCompanion;
    trait CostedContextCompanion;
    trait CostedBoxCompanion;
    trait CostedAvlTreeCompanion
  }
}