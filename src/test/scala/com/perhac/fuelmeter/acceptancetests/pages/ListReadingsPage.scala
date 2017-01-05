package com.perhac.fuelmeter.acceptancetests.pages

import com.perhac.fuelmeter.acceptancetests.BaseUrl
import org.openqa.selenium.WebDriver

class ListReadingsPage(val reg: String)(implicit val baseUrl: BaseUrl, driver: WebDriver) extends FuelMeterPage {

  def addNewVehicle(newVehicleReg: String): Unit = {
    textField(id("new-vehicle-input")).value = newVehicleReg
    click on cssSelector("#new-vehicle-input + a")
  }

  def clickAddReading(): Unit = click on id("btn-add-reading")

  def itemsInList: Int = findAll(cssSelector("tr.reading")).size

  def vehicleList(): Seq[String] = findAll(cssSelector("#vehicle-list li")).drop(1).map(_.text takeWhile (_.isLetterOrDigit)).toSeq

  override val url: String = baseUrl + s"/vehicles/$reg/readings/list"
}
