package hu.zoltankollar.pricing

import hu.zoltankollar.Product

class BulkDiscountPricingStrategy(
  n: Int,
  product: Product,
  newPrice: Double,
) extends PricingStrategy {

  assert(n > 0)
  assert(newPrice < product.price)

  override def apply(
    cart: Seq[Product],
  ): Double = {
    val c = cart.count(_ == product)
    if (c >= n) (product.price - newPrice) * -c
    else 0
  }

}
