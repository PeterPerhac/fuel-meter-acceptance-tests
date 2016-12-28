package com.perhac.fuelmeter.acceptancetests.specs

import com.perhac.fuelmeter.acceptancetests.pages.{AddReadingPage, DeleteReadingsPage, ListReadingsPage}
import org.scalatest.FlatSpec

class AddReadingSpec extends FlatSpec with FuelMeterAcceptanceTest {

  behavior of "Recording a reading"

  val listReadingsPage = new ListReadingsPage(defaultReg)

  it should "save a new reading if all fields populated correctly" in {
    go to listReadingsPage
    listReadingsPage.clickAddReading()

    val addReadingPage = new AddReadingPage(defaultReg)
    addReadingPage.prefillForm()
    addReadingPage.submit()

    listReadingsPage.itemsInList shouldBe 1
  }

  it should "save a new reading if date field is left blank" in {
    go to listReadingsPage
    listReadingsPage.clickAddReading()

    val addReadingPage = new AddReadingPage(defaultReg)
    addReadingPage.prefillForm()
    addReadingPage.fillForm("date" -> "")
    addReadingPage.submit()

    listReadingsPage.itemsInList shouldBe 1
  }

  it should "fail to validate form if date field does not represent a valid date" in {
    go to listReadingsPage
    listReadingsPage.clickAddReading()

    val addReadingPage = new AddReadingPage(defaultReg)
    addReadingPage.prefillForm()
    addReadingPage.fillForm("date" -> "2016/15/45")
    addReadingPage.submit()

    addReadingPage.errorOnPage("FOO FOO this is false alarm") shouldBe false
    addReadingPage.errorOnPage("Invalid date") shouldBe true
  }

  override def beforeEach(): Unit = {
    logger.warn(s"clearing all readings for $defaultReg vehicle")
    go to new DeleteReadingsPage(defaultReg)
  }

}