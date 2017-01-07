package com.perhac.fuelmeter.acceptancetests.pages

import org.openqa.selenium.WebDriver
import org.scalatest.selenium.{Page, WebBrowser}

abstract class FuelMeterPage(implicit driver: WebDriver) extends Page with WebBrowser {

  protected def fillForm(id: String)(fields: (String, Any)*): Unit = for {
    (name, any) <- fields
    e <- find(cssSelector(s"form#$id input[name=$name]"))
  } e.asInstanceOf[TextField].value = any.toString

}
