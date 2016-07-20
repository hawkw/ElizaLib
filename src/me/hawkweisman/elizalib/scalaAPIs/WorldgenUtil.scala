package me.hawkweisman.elizalib.scalaAPIs

import java.awt.Color

import com.fs.starfarer.api.{Global, SpriteId}
import com.fs.starfarer.api.campaign.{PlanetAPI, PlanetSpecAPI, SectorEntityToken}
import com.fs.starfarer.api.campaign.econ.MarketAPI



/**
  * Created by Eliza on 7/16/16.
  */
object WorldgenUtil {

  def addMarket( planet: SectorEntityToken
               , faction: String, name: String, size: Int, tariff:  Float
               , conditions: Seq[String], submarkets: Seq[String]
               , connectedEntities: SectorEntityToken* ): MarketAPI = {
    val market
      = Global.getFactory.createMarket(planet.getId + "_market", name, size)

    market.setFactionId(faction)
    market.setPrimaryEntity(planet)
    market.setBaseSmugglingStabilityValue(0)
    market.getTariff.modifyFlat("generator", tariff)

    for (submarket <- submarkets) { market addSubmarket submarket }

    for (condition <- conditions) { market addCondition condition }

    for (entity <- connectedEntities) {
      market.getConnectedEntities add entity
      entity setMarket market
      entity setFaction faction
    }

    Global.getSector.getEconomy addMarket market
    planet setMarket market
    planet setFaction faction

    market
  }

  implicit class AddMarket(val planet: SectorEntityToken)
  extends AnyVal {

    def addMarket( faction: String, name: String, size: Int, tariff: Float
                 , conditions: Seq[String], submarkets: Seq[String]
                 , connectedEntities: SectorEntityToken* ): MarketAPI
      = WorldgenUtil.addMarket( planet, faction, name, size, tariff
                              , conditions, submarkets
                              , connectedEntities:_*
                              )
  }

  implicit class RichPlanet(val planet: PlanetAPI)
  extends AnyVal {
    import graphics.SpriteUtil._

    def setSpec( planetColor: Option[Color] = None
               , atmosphereColor: Option[Color] = None
               , atmosphereThickness: Option[Float] = None
               , atmosphereThicknessMin: Option[Float] = None
               , cloudColor: Option[Color] = None
               , cloudRotation:  Option[Float] = None
               , cloudTexture: Option[SpriteId] = None
               , glowTexture: Option[SpriteId] = None
               , glowColor: Option[Color] = None
               , iconColor: Option[Color] = None
               , reverseLightForGlow: Option[Boolean] = None): Unit = {
      val spec = planet.getSpec

      planetColor foreach { spec.setPlanetColor }

      atmosphereColor foreach { spec.setAtmosphereColor }
      atmosphereThickness foreach { spec.setAtmosphereThickness }
      atmosphereThicknessMin foreach { spec.setAtmosphereThicknessMin }

      cloudColor foreach { spec.setCloudColor }
      cloudRotation foreach { spec.setCloudRotation }
      cloudTexture foreach { id => spec.setCloudTexture(id.spriteName) }

      glowColor foreach { spec.setGlowColor }
      glowTexture foreach { id => spec.setGlowTexture(id.spriteName) }
      reverseLightForGlow foreach { spec.setUseReverseLightForGlow }

      iconColor foreach { spec.setIconColor }

      planet.applySpecChanges()
    }
  }
}
