package root
class BuyCoin(accountNumber:Long, buyRate:Double, buyDate:Long, seller:String) {
  val newBitcoin = new Bitcoin(buyRate, buyDate, seller, false, Double.NaN, Double.NaN)
  val account = new MyAccount(accountNumber)
  account.addBitcoin(newBitcoin)
  account.setUsedBudget(account.getUsedBudget + buyRate)
}
