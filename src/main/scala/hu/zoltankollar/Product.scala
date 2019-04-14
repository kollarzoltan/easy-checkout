package hu.zoltankollar

abstract class Product(val price: Double)

object Product {

  case object Voucher extends Product(5)
  case object TShirt extends Product(20)
  case object Mug extends Product(7.5)

}
