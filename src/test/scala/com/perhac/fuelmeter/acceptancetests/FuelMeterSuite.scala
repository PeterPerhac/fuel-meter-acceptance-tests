package com.perhac.fuelmeter.acceptancetests

import com.perhac.fuelmeter.acceptancetests.specs.{AddReadingSpec, AddVehicleSpec, FirstTimeUserSpec}
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.{BeforeAndAfterAll, Suites}

class FuelMeterSuite extends Suites(new FirstTimeUserSpec, new AddReadingSpec, new AddVehicleSpec) with BeforeAndAfterAll {

  override def suiteName: String = "Fuel Meter Acceptance Tests"

  override protected def afterAll(): Unit = FuelMeterSuite.driver.quit()

}

object FuelMeterSuite {
  val driver = new ChromeDriver()
}