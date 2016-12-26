import org.scalatest.selenium.{Chrome, Page}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FlatSpec, Matchers}

trait FuelMeterAcceptanceTest extends FlatSpec with BeforeAndAfterAll with BeforeAndAfterEach with Matchers with Chrome {

  implicit lazy val baseUrl: String = System.getProperty("fuel-meter.base.url", "http://localhost:9000")

  lazy val homePage = new HomePage(baseUrl)

  val defaultReg = "TE5TR3G"

  implicit class TextMatcher(el: Option[Element]) {
    def textShouldBe(expectedString: String): Unit = el.map(_.text) shouldBe Some(expectedString)
  }

  override protected def afterAll(): Unit = quit()

  def fillForm(id: String)(fields: (String, Any)*): Unit = {
    fields foreach {
      case (name, textValue) =>
        find(cssSelector(s"form#$id input[name=$name]")).foreach(el =>
          el.asInstanceOf[TextField].value = textValue.toString
        )
    }
  }
}

class HomePage(val url: String) extends Page {}
