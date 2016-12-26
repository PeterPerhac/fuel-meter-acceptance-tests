import org.scalatest.Matchers
import org.scalatest.selenium.Chrome

trait FuelMeterAcceptanceTest extends Matchers with Chrome {

  lazy val baseUrl: String = System.getProperty("fuel-meter.base.url", "http://localhost:9000")

  val defaultReg = "TE5TR3G"

  implicit class TextMatcher(el: Option[Element]) {
    def textShouldBe(expectedString: String): Unit = el.map(_.text) shouldBe Some(expectedString)
  }

}
