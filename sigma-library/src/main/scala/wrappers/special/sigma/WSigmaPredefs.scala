package wrappers.special.sigma {
  import scalan._

  import impl._

  import special.sigma.wrappers.WrappersModule

  trait WSigmaPredefs extends Base { self: WrappersModule =>
    import WSigmaPredef._;
    @External("SigmaPredef") trait WSigmaPredef extends Def[WSigmaPredef];
    trait WSigmaPredefCompanion {
      @External def dataSize[T](v: Rep[T]): Rep[Long]
    }
  }
}