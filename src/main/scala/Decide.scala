/**
 * Created by adixith on 5/7/15.
 */
package root

import root.Advice._

class Decide(account:MyAccount, marketPrice:Double) {
  val maxBudget:Long = account.getMaxBudget
  val allCoins:Map[Long,Bitcoin] = account.getAllCoins
  val minKey = allCoins.minBy(_._2)._1
  //val newMap:Map[Long,Bitcoin] = ListMap(allCoins.toList.sortBy(_._2):_*)
  val totalSellingPrice = marketPrice + account.getMinimumProfit

  def advise(optionToTrade:String):(String, List[Long]) = {
    return matchApproach(optionToTrade)
  }

  private def decideGreedy: (String, List[Long]) = {
    if(totalSellingPrice < minKey) {
        if(budgetAvailable)  {
          return (BUY, List())
        }
        else return (STAND_BY, List())
    }
    return (SELL, getAllTradableBitcoins)
  }

  private def getAllTradableBitcoins:List[Long] = {
    val allTradableCoins:List[Long]= List()
    for((key, value) <- allCoins) {
      if(value.getBuyRate < totalSellingPrice) {
        allTradableCoins.::(key)
      }
    }
    allTradableCoins
  }

  def decideLongTerm: (String, List[Long]) = decideGreedy
  def decideVolatile: (String, List[Long]) = decideGreedy

  def matchApproach(optionToTrade:String): (String, List[Long]) = optionToTrade match {
    case OptionsToTrade.greedy => decideGreedy
    case OptionsToTrade.longTerm => decideLongTerm
    case OptionsToTrade.volatile => decideVolatile
  }

  private def budgetAvailable:Boolean = return account.getUsedBudget+marketPrice <= account.getMaxBudget

}
