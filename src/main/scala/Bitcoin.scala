package root
class Bitcoin(buyRate:Double, buyDate:Long, seller:String, var soldOrNot:Boolean, var sellPrice:Double, var profit:Double) extends Ordered[Bitcoin] {
  def getBuyRate:Double = buyRate
  def setSoldOrNot(sold:Boolean) = soldOrNot = sold
  def compare(that:Bitcoin) =  (this.buyRate - that.getBuyRate).toInt
}
