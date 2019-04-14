package hu.zoltankollar

import hu.zoltankollar.Product._
import org.scalatest.{FlatSpec, Matchers}

class ProductSpec extends FlatSpec with Matchers {

  "Voucher" should "have a price" in {
    Voucher.price shouldBe 5
  }

  "TShirt" should "have a price" in {
    TShirt.price shouldBe 20
  }

  "Mug" should "have a price" in {
    Mug.price shouldBe 7.5
  }


}
