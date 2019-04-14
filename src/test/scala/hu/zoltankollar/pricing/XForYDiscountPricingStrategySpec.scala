package hu.zoltankollar.pricing

import org.scalacheck.Gen
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.PropertyChecks

class XForYDiscountPricingStrategySpec
  extends FlatSpec
    with Matchers
    with PropertyChecks
    with Generators {

  private val xyGen = for {
    x <- Gen.choose(2, Int.MaxValue)
    y <- Gen.choose(1, x - 1)
  } yield x -> y

  private val leXY = for {
    x <- Gen.choose(1, Int.MaxValue)
    y <- Gen.choose(x, Int.MaxValue)
  } yield x -> y

  "XForYDiscountPricingStrategy" should "give free item(s) every time " +
  "if more than x product is checked out" in {
    forAll(
      xyGen,
      productGen,
      cartGen,
    ) { case ((x, y), product, cart) =>
      val pricing = new XForYDiscountPricingStrategy(x, y, product)
      val c = cart.count(_ == product)
      val d = x - y
      val expected = -((c / x) * product.price * d)

      pricing(cart) shouldBe expected
    }
  }

  it should "throw AssertionError if x lower or equal than y" in {
    forAll(
      leXY,
      productGen,
    ) { case ((x, y), product) =>
      the [AssertionError] thrownBy {
        new XForYDiscountPricingStrategy(x, y, product)
      }
    }
  }

  it should "throw AssertionError y lower or equal than 0" in {
    forAll(
      Gen.choose(1, Int.MaxValue),
      Gen.choose(Int.MinValue, 0),
      productGen,
    ) { (x, y, product) =>
      the [AssertionError] thrownBy {
        new XForYDiscountPricingStrategy(x, y, product)
      }
    }
  }

}
