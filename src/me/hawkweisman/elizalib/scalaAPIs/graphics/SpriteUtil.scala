package me.hawkweisman.elizalib.scalaAPIs.graphics

import com.fs.starfarer.api.{Global, SpriteId}
import com.fs.starfarer.api.graphics.SpriteAPI

/**
  * Created by hawk on 7/20/16.
  */
object SpriteUtil {
  implicit def stringTuple2SpriteId(tuple: (String, String)): SpriteId
    = tuple match { case ((category, id)) => new SpriteId(category, id) }

  implicit class RichSpriteId(val id: SpriteId)
  extends AnyVal {
    def sprite: SpriteAPI
      = Global.getSettings.getSprite(id)
    def spriteName: String
      = Global.getSettings getSpriteName(id.getCategory, id.getKey)
  }
}
