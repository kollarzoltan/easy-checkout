package hu.zoltankollar.pricing

import org.scalacheck.Gen
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.PropertyChecks

class BulkDiscountPricingStrategySpec
  extends FlatSpec
    with Matchers
    with PropertyChecks
    with Generators {

  private val productWithNewPriceGen = for {
    product <- productGen
    newPrice <- Gen.choose(1, product.price) suchThat(_ != product.price)
  } yield product -> newPrice

  private val productWithHigherNewPriceGen = for {
    product <- productGen
    newPrice <- Gen.choose(product.price, Double.MaxValue)
  } yield product -> newPrice

  "BulkDiscountPricingStrategy" should "apply new price on each item on the given product " +
  "if there are more product on the checkout than n" in {
    forAll(
      Gen.posNum[Int],
      productWithNewPriceGen,
      cartGen,
    ) { case (n, (product, newPrice), cart) =>
      val pricing = new BulkDiscountPricingStrategy(
        n,
        product,
        newPrice,
      )

      val d = product.price - newPrice
      val c = cart.count(_ == product)
      val expected = if (c >= n) d * -c else 0

      pricing(cart) shouldBe expected
    }
  }

  it should "throw AssertionError if n <= 0" in {
    forAll(
      Gen.choose(Int.MinValue, 0),
      productWithNewPriceGen,
    ) { case (n, (product, newPrice)) =>
      the [AssertionError] thrownBy {
        new BulkDiscountPricingStrategy(n, product, newPrice)
      }
    }
  }

  it should "throw AssertionError if newPrice is greater or equal than the old one" in {
    forAll(
      Gen.posNum[Int],
      productWithHigherNewPriceGen,
    ) { case (n, (product, newPrice)) =>
      the [AssertionError] thrownBy {
        new BulkDiscountPricingStrategy(n, product, newPrice)
      }
    }
  }

}
