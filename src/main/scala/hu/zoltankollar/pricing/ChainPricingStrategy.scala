package hu.zoltankollar.pricing

import hu.zoltankollar.Product

class ChainPricingStrategy private (
  strategies: Seq[PricingStrategy],
) extends PricingStrategy {

  override def apply(
    cart: Seq[Product],
  ): Double = strategies
    .map(_(cart))
    .sum

}

object ChainPricingStrategy {

  def apply(
    strategy: PricingStrategy,
    strategies: PricingStrategy*,
  ): ChainPricingStrategy = new ChainPricingStrategy(strategy +: strategies)

}
