package com.perhac.fuelmeter.acceptancetests.pages

import com.perhac.fuelmeter.acceptancetests.BaseUrl
import org.openqa.selenium.WebDriver

class ListReadingsPage(val reg: String)(implicit val baseUrl: BaseUrl, driver: WebDriver) extends FuelMeterPage {

  def clickAddReading(): Unit = click on id("btn-add-reading")

  def itemsInList: Int = findAll(cssSelector("#readings-list li")).size

  override val url: String = baseUrl + s"/vehicles/$reg/readings/list"
}
