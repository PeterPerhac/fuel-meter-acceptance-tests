package com.perhac.fuelmeter.acceptancetests.specs

import com.perhac.fuelmeter.acceptancetests.pages.{AddReadingPage, DeleteReadingsPage, ListReadingsPage}
import org.scalatest.DoNotDiscover

@DoNotDiscover
class AddVehicleSpec extends FuelMeterAcceptanceTest {

  val newVehicleReg = "NOOV3H"

  val listReadingsPage = new ListReadingsPage(defaultReg)
  val addReadingPage = new AddReadingPage(defaultReg)

  override def beforeEach(): Unit = {
    logger.warn(s"clearing all readings for $newVehicleReg vehicle")
    go to new DeleteReadingsPage(newVehicleReg)
    go to listReadingsPage
    listReadingsPage.itemsInList shouldBe 0
    listReadingsPage.vehicleList() should not be empty
  }

  behavior of "Creating a new vehicle"

  it should "save a new vehicle if registration number is of acceptable format" in {
    listReadingsPage.addNewVehicle(newVehicleReg)

    addReadingPage.prefillForm()
    addReadingPage.submit()

    assertNoErrorsOnPage()
    listReadingsPage.itemsInList shouldBe 1
    listReadingsPage.vehicleList() should contain(newVehicleReg)
  }

}