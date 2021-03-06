package com.perhac.fuelmeter.acceptancetests
package pages

import org.openqa.selenium.WebDriver

class AddReadingPage(val reg: String)(implicit val baseUrl: BaseUrl, driver: WebDriver) extends FuelMeterPage {

  val defaultFormData: Seq[(String, Any)] = Seq("date" -> "2016-12-26", "miles" -> 10.0, "mileage" -> 1000, "liters" -> 1.1, "cost" -> 1.32)

  def fillForm(fields: (String, Any)*): Unit = super.fillForm("frm-add-reading")(fields: _*)

  def prefillForm(): Unit = fillForm(defaultFormData: _*)

  def submit(): Unit = click on name("reg")

  override val url: String = baseUrl + s"/vehicles/$reg/readings/capture"
}
