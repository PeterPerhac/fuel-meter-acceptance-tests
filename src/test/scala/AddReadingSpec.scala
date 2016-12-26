import org.scalatest.FlatSpec
import pages.ListReadingsPage

class AddReadingSpec extends FlatSpec with FuelMeterAcceptanceTest {

  behavior of "Recording a reading"


  it should "save a new reading if all fields populated correctly" in {
    val page = new ListReadingsPage(defaultReg)
    go to page
    //TODO move knowledge of IDs and other such detail to the Page Object
    click on id("btn-add-reading")
    fillForm("frm-add-reading")("date" -> "2016/12/26", "mi" -> 10.0, "total" -> 1000, "litres" -> 1.1, "cost" -> 1.32)
    click on name("reg")
    findAll(cssSelector("#readings-list li")).size should be > 0
  }

  override protected def beforeEach(): Unit = {
    "flushing documents from mongo collection"
  }
}