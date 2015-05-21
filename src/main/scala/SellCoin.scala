package root
class SellCoin(accountNumber:Long, bitcoinId:Long, sellingPrice:Double) {
  val account = new MyAccount(accountNumber)
  account.removeBitcoin(bitcoinId)
  account.setUsedBudget(account.getUsedBudget - sellingPrice)
}