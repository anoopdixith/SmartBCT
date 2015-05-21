package root
/**
 * Created by adixith on 5/19/15.
 */
class Action(account:MyAccount) {
  def takeAction:Integer = {
    val marketPrice:Double = new Market().getBuyPrice
    val (advice:String, bitcoins:List[Long]) = account.getAdvice(marketPrice)
    advice match  {
      case Advice.BUY => {
        new BuyCoin(account.getAccountNumber, marketPrice, System.currentTimeMillis(), Constants.coinbase)
        ActionReturnValues.bitcoinBought
      }
      case Advice.STAND_BY => ActionReturnValues.takingNoAction
      case Advice.SELL => {
        for(bitcoinId <- bitcoins) {
          new SellCoin(account.getAccountNumber, bitcoinId, marketPrice)
        }
        if(bitcoins.size == 1) return ActionReturnValues.oneBitcoinSold else return ActionReturnValues.multipleBitcoinsSold
      }
      case _ => ActionReturnValues.serverError
    }
  }
}
