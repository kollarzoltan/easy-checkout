package hu.zoltankollar

import hu.zoltankollar.Product._
import hu.zoltankollar.pricing._

object Main extends App {

  val pricing = ChainPricingStrategy(
    new DefaultPricingStrategy,
    new XForYDiscountPricingStrategy(2, 1, Voucher),
    new BulkDiscountPricingStrategy(3, TShirt, 19),
  )

  val cart1 = Seq(
    Voucher,
    TShirt,
    Mug,
  )
  val price1 = pricing(cart1)
  printOutput(cart1, price1)
  println()

  val cart2 = Seq(
    Voucher,
    TShirt,
    Voucher,
  )
  val price2 = pricing(cart2)
  printOutput(cart2, price2)
  println()

  val cart3 = Seq(
    TShirt,
    TShirt,
    TShirt,
    Voucher,
    TShirt,
  )
  val price3 = pricing(cart3)
  printOutput(cart3, price3)
  println()

  val cart4 = Seq(
    Voucher,
    TShirt,
    Voucher,
    Voucher,
    Mug,
    TShirt,
    TShirt,
  )
  val price4 = pricing(cart4)
  printOutput(cart4, price4)


  def printOutput(
    cart: Seq[Product],
    price: Double,
  ): Unit = {
    println(s"Items: ${cart.mkString(", ")}")
    println(f"Total: $price%.2f€")
  }

}
