package hu.zoltankollar.pricing

import hu.zoltankollar.Product

class XForYDiscountPricingStrategy(
  x: Int,
  y: Int,
  product: Product,
) extends PricingStrategy {

  assert(x > y)
  assert(y > 0)

  override def apply(
    cart: Seq[Product],
  ): Double = {
    val c = cart.count(_ == product)
    val d = x - y
    -((c / x) * product.price * d)
  }

}
