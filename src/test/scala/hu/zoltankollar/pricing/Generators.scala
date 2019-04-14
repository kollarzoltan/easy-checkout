package hu.zoltankollar.pricing

import hu.zoltankollar.Product
import hu.zoltankollar.Product.{Mug, TShirt, Voucher}
import org.scalacheck.Gen
import org.scalamock.scalatest.MockFactory

trait Generators extends MockFactory {

  val strategyGen: Gen[PricingStrategy] = Gen.delay(
    mock[PricingStrategy]
  )

  val productGen: Gen[Product] = Gen.oneOf(
    Voucher,
    TShirt,
    Mug,
  )

  val cartGen: Gen[List[Product]] = Gen.nonEmptyListOf(productGen)

}
