package com.perhac.fuelmeter.acceptancetests.specs

import com.perhac.fuelmeter.acceptancetests.BaseUrl
import com.perhac.fuelmeter.acceptancetests.pages.HomePage
import org.scalatest.selenium.Chrome
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FlatSpec, Matchers}
import org.slf4j.{Logger, LoggerFactory}

trait FuelMeterAcceptanceTest extends FlatSpec with BeforeAndAfterAll with BeforeAndAfterEach with Matchers with Chrome {

  lazy val logger: Logger = LoggerFactory.getLogger(this.getClass)

  implicit lazy val baseUrl: BaseUrl = System.getProperty("fuel-meter.base.url", "http://localhost:9000")

  lazy val homePage = new HomePage(baseUrl)

  val defaultReg = "TE5TR3G"

  implicit class TextMatcher(el: Option[Element]) {
    def textShouldBe(expectedString: String): Unit = el.map(_.text) shouldBe Some(expectedString)
  }

  private def displayedErrors() = findAll(cssSelector("span.error")).withFilter(_.isDisplayed)

  def errorsOnPage(): List[String] = {
    displayedErrors().map(_.text).toList
  }

  def assertNoErrorsOnPage(): Unit = {
    displayedErrors() shouldBe empty
  }

  override protected def afterAll(): Unit = quit()

}
