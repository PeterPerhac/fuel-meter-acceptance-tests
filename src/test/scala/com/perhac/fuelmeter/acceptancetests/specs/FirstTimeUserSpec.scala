package com.perhac.fuelmeter.acceptancetests.specs

class FirstTimeUserSpec extends FuelMeterAcceptanceTest {

  behavior of "Homepage"

  it should "have the correct title" in {
    go to homePage
    pageTitle shouldBe "Fuel Meter"
  }

  it should "list vehicle readings if cookie is present" in {
    add cookie("vreg", defaultReg)
    go to homePage
    find(tagName("h1")) textShouldBe defaultReg
  }

}