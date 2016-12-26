import org.scalatest.FlatSpec

class FirstTimeUserSpec extends FlatSpec with FuelMeterAcceptanceTest {

  behavior of "Homepage"

  it should "have the correct title" in {
    go to baseUrl
    pageTitle shouldBe "Fuel Meter"
  }

  it should "list vehicle readings if cookie present" in {
    add cookie("vreg", defaultReg)
    go to baseUrl
    find(tagName("h1")) textShouldBe defaultReg
  }

}