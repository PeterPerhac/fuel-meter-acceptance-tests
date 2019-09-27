package com.perhac.fuelmeter.acceptancetests.specs

import com.perhac.fuelmeter.acceptancetests.pages.HomePage
import com.perhac.fuelmeter.acceptancetests.{BaseUrl, FuelMeterSuite}
import org.openqa.selenium.WebDriver
import org.scalatest._
import org.scalatest.selenium.WebBrowser
import org.slf4j.{Logger, LoggerFactory}

abstract class FuelMeterAcceptanceTest extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
  with BeforeAndAfterEach with WebBrowser {

  type HtmlTag = String

  lazy val logger: Logger = LoggerFactory.getLogger(this.getClass)

  implicit lazy val baseUrl: BaseUrl = System.getProperty("fuel-meter.base.url", "http://localhost:9000/fuelmeter")

  implicit lazy val webDriver: WebDriver = FuelMeterSuite.driver

  lazy val homePage = new HomePage(baseUrl)

  val defaultReg = "TE5TR3G"

  implicit class TextMatcher(el: Option[Element]) {

    def textShouldBe(expectedString: String): Unit = el.map(_.text) shouldBe Some(expectedString)

    def textShouldContain(expectedSubstring: String): Unit = inside(el.map(_.text)) { case Some(text) => text should include(expectedSubstring) }

  }

  implicit class ClassSelector(tags: Seq[HtmlTag]) {
    def elementsOfClass(clazz: String): Seq[Element] = for {
      tag <- tags
      els <- findAll(cssSelector(s"$tag.$clazz"))
    } yield els
  }

  private def displayedErrors() = List[HtmlTag]("p", "span", "dd", "label").elementsOfClass("error")
    .withFilter(_.isDisplayed).map(_.text)

  def errorsOnPage(): List[String] = {
    displayedErrors().toList
  }

  def allErrorsAsString(): Option[String] = errorsOnPage().reduceOption(_ + _)

  def assertNoErrorsOnPage(): Unit = {
    displayedErrors() shouldBe empty
  }

}
