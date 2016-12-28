package com.perhac.fuelmeter.acceptancetests.pages

import org.openqa.selenium.WebDriver
import org.scalatest.selenium.{Page, WebBrowser}

abstract class FuelMeterPage(implicit driver: WebDriver) extends Page with WebBrowser {

  protected def fillForm(id: String)(fields: (String, Any)*): Unit = {
    fields foreach {
      case (name, textValue) => find(cssSelector(s"form#$id input[name=$name]")).foreach {
        _.asInstanceOf[TextField].value = textValue.toString
      }
    }
  }

  def errorOnPage(errorMessageSubstring: String): Boolean = {
    findAll(cssSelector(".error")).collectFirst {
      case e if e.text contains errorMessageSubstring => true
    } getOrElse false
  }

}
