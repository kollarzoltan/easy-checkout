package hu.zoltankollar.pricing

import hu.zoltankollar.Product

trait PricingStrategy {

  def apply(cart: Seq[Product]): Double

}
