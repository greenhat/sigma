package scala {
  import scalan._

  import impl._

  import special.sigma.wrappers.WrappersModule

  trait WOptions extends Base { self: WrappersModule =>
    @External("Option") @ContainerType @FunctorType trait WOption[A] extends Def[WOption[A]] {
      implicit def eA: Elem[A];
      @External def map[B](f: Rep[scala.Function1[A, B]]): Rep[WOption[B]];
      @External def get: Rep[A]
    };
    trait WOptionCompanion
  }
}