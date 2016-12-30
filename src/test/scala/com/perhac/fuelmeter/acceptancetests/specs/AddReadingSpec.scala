package com.perhac.fuelmeter.acceptancetests.specs

import com.perhac.fuelmeter.acceptancetests.pages.{AddReadingPage, DeleteReadingsPage, ListReadingsPage}
import org.scalatest.DoNotDiscover

@DoNotDiscover
class AddReadingSpec extends FuelMeterAcceptanceTest {

  val listReadingsPage = new ListReadingsPage(defaultReg)
  val addReadingPage = new AddReadingPage(defaultReg)

  override def beforeEach(): Unit = {
    logger.warn(s"clearing all readings for $defaultReg vehicle")
    go to new DeleteReadingsPage(defaultReg)
    go to listReadingsPage
    listReadingsPage.itemsInList shouldBe 0
    listReadingsPage.clickAddReading()
  }

  behavior of "Recording a reading"

  it should "save a new reading if all fields populated correctly" in {
    addReadingPage.prefillForm()
    addReadingPage.submit()

    assertNoErrorsOnPage()
    listReadingsPage.itemsInList shouldBe 1
  }

  it should "save a new reading if date field is left blank" in {
    addReadingPage.prefillForm()
    addReadingPage.fillForm("date" -> "")
    addReadingPage.submit()

    assertNoErrorsOnPage()
    listReadingsPage.itemsInList shouldBe 1
  }

  it should "fail to validate form if date field does not represent a valid date" in {
    addReadingPage.prefillForm()
    addReadingPage.fillForm("date" -> "2016/15/45")
    addReadingPage.submit()

    errorsOnPage should contain only "Invalid date"
  }

  it should "fail to validate form if user entered a huge number in the miles field" in {
    addReadingPage.prefillForm()
    addReadingPage.fillForm("mi" -> 1500)
    addReadingPage.submit()

    errorsOnPage should contain only "Too much"
  }

  it should "fail to validate form if user entered letters in a numeric field" in {
    addReadingPage.prefillForm()
    addReadingPage.fillForm("mi" -> "foofoo")
    addReadingPage.submit()

    errorsOnPage should contain only "Decimal number"
  }

  it should "fail to validate form when filled in by evil user" in {
    addReadingPage.fillForm("date" -> "foo", "mi" -> "foofoo", "total" -> "poopoo", "litres" -> 10000.12, "cost" -> -212.23)
    addReadingPage.submit()

    errorsOnPage should contain theSameElementsAs List("Decimal number", "Whole number", "Invalid date", "Too much", "Not enough")
  }

}