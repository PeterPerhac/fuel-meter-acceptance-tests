package com.perhac.fuelmeter

import com.perhac.fuelmeter.acceptancetests.specs.FuelMeterAcceptanceTest
import org.scalatest.BeforeAndAfterEach

package object acceptancetests {

  type BaseUrl = String

  trait CookieClearing extends BeforeAndAfterEach {
    this: FuelMeterAcceptanceTest =>
    override protected def beforeEach(): Unit = {
      logger.info("clearing all cookies")
      deleteAllCookies()
      super.beforeEach()
    }
  }

}
