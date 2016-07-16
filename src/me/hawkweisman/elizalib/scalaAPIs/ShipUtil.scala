package me.hawkweisman.elizalib.scalaAPIs

import com.fs.starfarer.api.combat.MutableShipStatsAPI
import com.fs.starfarer.api.combat.ShipAPI

import scala.language.implicitConversions

/**
  * Created by hawk on 6/28/16.
  */
object ShipUtil {

  @inline final implicit def mutStatsAsShip(s: MutableShipStatsAPI): ShipAPI
    = s.getEntity.asInstanceOf[ShipAPI]

}
