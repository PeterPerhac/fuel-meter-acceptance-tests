import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.selenium.HtmlUnit

/**
  * Created by peterperhac on 25/12/2016.
  */
class FirstTimeUserSpec extends FlatSpec with Matchers with HtmlUnit with FuelMeterAcceptanceTest{

  "Homepage" should "have the correct title" in {
    go to baseUrl
    pageTitle should be ("Fuel Meter")
  }

}