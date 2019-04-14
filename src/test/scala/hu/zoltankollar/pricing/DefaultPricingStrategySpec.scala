package hu.zoltankollar.pricing

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.PropertyChecks

class DefaultPricingStrategySpec
  extends FlatSpec
    with Matchers
    with PropertyChecks
    with Generators {

  "DefaultPricingStrategy" should "sum up all products' price" in {
    forAll(cartGen) { cart =>
      val pricing = new DefaultPricingStrategy
      pricing(cart) shouldBe cart.map(_.price).sum
    }
  }

}
