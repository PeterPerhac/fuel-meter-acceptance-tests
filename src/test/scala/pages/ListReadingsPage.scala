package pages

import org.scalatest.selenium.Page

class ListReadingsPage(val reg: String)(implicit val baseUrl: String) extends Page {
  override val url: String = baseUrl + s"/vehicles/$reg/readings/list"
}
