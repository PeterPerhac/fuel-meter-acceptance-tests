package com.perhac.fuelmeter.acceptancetests.pages

import com.perhac.fuelmeter.acceptancetests.BaseUrl
import org.scalatest.selenium.Page

class DeleteReadingsPage(reg: String)(implicit baseUrl: BaseUrl) extends Page {
  override val url: String = baseUrl + s"/vehicles/$reg/delete"
}
