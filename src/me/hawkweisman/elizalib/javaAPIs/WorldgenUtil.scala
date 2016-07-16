package me.hawkweisman.elizalib
package javaAPIs

import com.fs.starfarer.api.campaign.SectorEntityToken
import com.fs.starfarer.api.campaign.econ.MarketAPI

import java.util

/**
  * Created by hawk on 7/16/16.
  */
object WorldgenUtil {

  import scalaAPIs.WorldgenUtil.{ addMarket => scalaAddMarket}

  def addMarket( planet: SectorEntityToken
               , faction: String, name: String, size: Int, tariff:  Float
               , conditions: Array[String], submarkets: Array[String]
               , connectedEntities: Array[SectorEntityToken]): MarketAPI
    = scalaAddMarket( planet
                    , faction, name, size, tariff
                    , conditions, submarkets
                    , connectedEntities:_*)

}
