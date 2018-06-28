package special.sigma {
  import scalan.OverloadHack.Overloaded1
  import scalan._

  trait SigmaDsl extends Base { self: SigmaLibrary =>
    @sigmalang trait Sigma extends Def[Sigma] {
      def isValid: Rep[Boolean];
      @OverloadId(value = "and_sigma") def &&(other: Rep[Sigma]): Rep[Sigma];
      @OverloadId(value = "and_bool") def &&(other: Rep[Boolean])(implicit o: Overloaded1): Rep[Sigma];
      @OverloadId(value = "or_sigma") def ||(other: Rep[Sigma]): Rep[Sigma];
      @OverloadId(value = "or_bool") def ||(other: Rep[Boolean])(implicit o: Overloaded1): Rep[Sigma]
    };
    @sigmalang trait ProveDlog extends Sigma {
      def propBytes: Rep[Col[Byte]]
    };
    @sigmalang trait Box extends Def[Box] {
      def id: Rep[Col[Byte]];
      def value: Rep[Long];
      def propositionBytes: Rep[Col[Byte]];
      def cost: Rep[Int];
      def registers: Rep[Col[Any]];
      def R1[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(0.asInstanceOf[Int])));
      def R2[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(1.asInstanceOf[Int])));
      def R3[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(2.asInstanceOf[Int])));
      def R4[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(3.asInstanceOf[Int])));
      def R5[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(4.asInstanceOf[Int])));
      def R6[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(5.asInstanceOf[Int])));
      def R7[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(6.asInstanceOf[Int])));
      def R8[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(7.asInstanceOf[Int])));
      def R9[T:Elem]: Rep[WOption[T]] = WSpecialPredef.cast[T](Box.this.registers.apply(toRep(8.asInstanceOf[Int])))
    };
    trait Context extends Def[Context] {
      def builder: Rep[ContextBuilder];
      def OUTPUTS: Rep[Col[Box]];
      def INPUTS: Rep[Col[Box]];
      def HEIGHT: Rep[Long];
      def SELF: Rep[Box]
    };
    trait ContextBuilder extends Def[ContextBuilder] {
      def Collections: Rep[ColBuilder]
    };
    @sigmalang trait SigmaContract extends Def[SigmaContract] {
      def verify(cond: Rep[Boolean]): Rep[Boolean];
      def verifyZK(cond: Rep[Sigma]): Rep[Boolean];
      def allOf(conditions: Rep[Col[Boolean]]): Rep[Boolean];
      def allZK(conditions: Rep[Col[Sigma]]): Rep[Sigma];
      def anyOf(conditions: Rep[Col[Boolean]]): Rep[Boolean];
      def anyZK(conditions: Rep[Col[Sigma]]): Rep[Sigma];
      @clause def canOpen(ctx: Rep[Context]): Rep[Boolean]
    };
    trait SigmaCompanion;
    trait ProveDlogCompanion;
    trait BoxCompanion;
    trait ContextCompanion;
    trait ContextBuilderCompanion;
    trait SigmaContractCompanion
  }
}