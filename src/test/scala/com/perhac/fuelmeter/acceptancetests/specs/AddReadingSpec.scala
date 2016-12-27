package com.perhac.fuelmeter.acceptancetests.specs

import com.perhac.fuelmeter.acceptancetests.pages.{AddReadingPage, ListReadingsPage}
import org.scalatest.FlatSpec

class AddReadingSpec extends FlatSpec with FuelMeterAcceptanceTest {

  behavior of "Recording a reading"

  it should "save a new reading if all fields populated correctly" in {
    val listReadingsPage = new ListReadingsPage(defaultReg)
    go to listReadingsPage
    listReadingsPage.clickAddReading()

    val addReadingPage = new AddReadingPage(defaultReg)
    addReadingPage.fillForm("date" -> "2016/12/26", "mi" -> 10.0, "total" -> 1000, "litres" -> 1.1, "cost" -> 1.32)
    addReadingPage.submit()

    listReadingsPage.itemsInList should be > 0
  }

  override protected def beforeEach(): Unit = {
    "flushing documents from mongo collection"
  }
}