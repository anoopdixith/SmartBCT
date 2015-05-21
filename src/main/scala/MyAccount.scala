package root
object MyAccount {
  private var numberOfBitcoin:Long = 0
  private var maximumBudget:Long = 1000
  private var usedBudget:Double = 0
  private var minimumProfit:Double = 2
  private var chosenTradeOption:String = OptionsToTrade.greedy
  private var profit:Double = 0
}

class MyAccount (accountNumberSent:Long){
  private val allCoins:Map[Long,Bitcoin] = Map()
  private val accountNumber = accountNumberSent

  def addBitcoin(newBitcoin:Bitcoin) = {
    allCoins + (MyAccount.numberOfBitcoin -> newBitcoin)
    MyAccount.numberOfBitcoin+=1
  }

  def removeBitcoin(bitcoinId:Long):Boolean = {
    val bitCoinToRemove:Option[Bitcoin] = allCoins.get(bitcoinId)
    if(bitCoinToRemove == None)
       return false
    bitCoinToRemove.get.setSoldOrNot(true)
    allCoins + (bitcoinId -> bitCoinToRemove)
    MyAccount.numberOfBitcoin-=1
    return true
  }

  def getChosenTradeOption:String = MyAccount.chosenTradeOption
  def setChosenTradeOption(option:String) = {
    option match {
      case OptionsToTrade.greedy | OptionsToTrade.longTerm | OptionsToTrade.volatile => MyAccount.chosenTradeOption = option
      case _ =>
    }
  }

  def getProfit:Double = MyAccount.profit
  def setProfit(profit:Double) = MyAccount.profit = profit
  def setUsedBudget(priceToBuy:Double) = MyAccount.usedBudget = priceToBuy
  def getAccountNumber = accountNumber
  def setMinimumProfit(minimumProfit:Double) = MyAccount.minimumProfit = minimumProfit
  def getMinimumProfit:Double = MyAccount.minimumProfit
  def getUsedBudget:Double = MyAccount.usedBudget
  def getAllCoins:Map[Long,Bitcoin] = allCoins
  def numberOfTransactions:Long = allCoins.size
  def numberOfBitcoin:Long = MyAccount.numberOfBitcoin
  def getMaxBudget:Long = MyAccount.maximumBudget
  def setMaxBudget(newMaxBudget:Long) = MyAccount.maximumBudget = newMaxBudget
  def getAdvice(marketPrice:Double):(String,List[Long]) = new Decide(this, marketPrice).advise(getChosenTradeOption)
}
