package hu.zoltankollar.pricing

import hu.zoltankollar.Product

class DefaultPricingStrategy extends PricingStrategy {

  override def apply(
    cart: Seq[Product],
  ): Double = cart
    .map(_.price)
    .sum

}
