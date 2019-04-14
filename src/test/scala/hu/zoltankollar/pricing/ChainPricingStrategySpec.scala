package hu.zoltankollar.pricing

import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class ChainPricingStrategySpec
  extends FlatSpec
    with Matchers
    with PropertyChecks
    with Generators {

  "ChainPricingStrategy" should "invoke each strategies and sum up the results" in {
    forAll(
      Gen.nonEmptyListOf(strategyGen),
      cartGen,
    ) { (strategies, cart) =>
      val pricing = ChainPricingStrategy(
        strategies.head,
        strategies.tail: _*,
      )

      for (strategy <- strategies) {
        (strategy.apply _).expects(cart).returning(1).once
      }

      pricing(cart) shouldBe strategies.length

    }
  }

}
