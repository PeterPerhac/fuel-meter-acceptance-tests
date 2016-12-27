package com.perhac.fuelmeter.acceptancetests.pages

import org.openqa.selenium.WebDriver

class AddReadingPage(val reg: String)(implicit val baseUrl: String, driver: WebDriver) extends FuelMeterPage {

  def fillForm(fields: (String, Any)*): Unit = super.fillForm("frm-add-reading")(fields: _*)

  def submit(): Unit = click on name("reg")

  override val url: String = baseUrl + s"/vehicles/$reg/readings/capture"
}
