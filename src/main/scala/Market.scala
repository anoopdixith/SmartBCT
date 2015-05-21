package root
import scalaj.http.{Http, HttpResponse}
import org.json4s._
import org.json4s.native.JsonMethods._
/**
 * Created by adixith on 5/13/15.
 */

class Market {
  def getBuyPrice:Double = {
    getExternalRates(Constants.BUY_URL)
  }
  def getSellPrice:Double = {
    getExternalRates(Constants.SELL_URL)
  }
  def getSpotPrice:Double = {
    getExternalRates(Constants.SPOT_PRICE_URL)
  }

  private def getExternalRates(url:String):Double = {
    val response: HttpResponse[String] = Http(url).asString
    if(response.code != 200) throw new Exception("Server error on the external website")
    val jsonRepresentation = parse(response.body)
    val jsonObject = jsonRepresentation.values.asInstanceOf[Map[String,String]]
    val returnAmount = jsonObject.get(Constants.COINBASE_PRICE_STRING).get
    returnAmount.toDouble
  }

}
