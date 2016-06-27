package me.hawkweisman.elizalib.scalaAPIs

import scala.language.implicitConversions

import java.awt
/** Scala-idiomatic accessors, mutators, and extractors for [[java.awt.Color]].
  *
  * @author Eliza Weisman
  *
  * Created by Eliza on 6/27/16.
  */
object ColorUtil {

  /** A nicer Scala API for [[java.awt.Color]]
    *
    * @author Eliza Weisman
    *
    * Created by Eliza on 6/27/16.
    */
  implicit class Color(private[ColorUtil] val c: awt.Color) {
    lazy private[this] val HSB(h, s, b) = c

    @inline def red: Int = c.getRed
    @inline def green: Int = c.getGreen
    @inline def blue: Int = c.getBlue
    @inline def alpha: Int = c.getAlpha

    @inline def hue: Float = h
    @inline def saturation: Float = s
    @inline def brightness: Float = b

    @inline def lerpHSB (b: Color, amount: Float): Color
      = HSB.lerp(c, b, amount)
  }

  implicit def color2awtColor(color: Color): awt.Color = color.c

  object RGB {
    @inline def unapply(c: awt.Color): Option[(Int, Int, Int, Int)]
     = Some((c.getRed, c.getGreen, c.getBlue, c.getAlpha))

    @inline def apply(r: Int, g: Int, b: Int): awt.Color
      = new awt.Color(r, g, b)

    @inline def apply(r: Int, g: Int, b: Int, a: Int): awt.Color
      = new awt.Color(r, g, b, a)

    /**
      * Linear interpolate between two [[Color]]s through RGB
      * color space.
      *
      * Interpolating the R, G and B components independently offers no
      * guarantee on the hue of the intermediate colors. Most of the time
      * you'll generally want to use [[ColorUtil#lerpHSV()]] instead.
      *
      * @param a the first color to lerp between
      * @param b the second color to lerp between
      * @param amount the amount to lerp (must be between 0 and 1)
      * @return [description]
      */
    def lerp(a: Color, b: Color, amount: Float): Color
      = new awt.Color( a.red lerp (b.getRed, amount)
                     , a.green lerp (b.green, amount)
                     , a.blue lerp (b.blue, amount)
                     , a.alpha lerp (b.alpha, amount)
                     )
  }

  object HSB {

    @inline def unapply(c: awt.Color): Option[(Float, Float, Float)] = {
      val Array(h, s, b) = awt.Color.RGBtoHSB(c.red, c.green, c.blue, null)
      Some((h, s, b))
    }

    @inline def apply(h: Float, s: Float, b: Float): awt.Color
      = awt.Color.getHSBColor(h, s, b)

    /**
      * Linear interpolate between two [[Color]]s through HSB
      * color space.
      *
      * Hue interpolation is "corrected" so that we don't pass through
      * strange-looking intermediate hues. This code is based on code by
      * Alan Zucconi in his
      * <a href="http://www.alanzucconi.com/2016/01/06/colour-interpolation/2/">
      * article</a> on color interpolation.
      *
      * @param  a the first color to lerp between
      * @param  b the second color to lerp between
      * @param  amount the amount to lerp (must be between 0 and 1)
      * @return [description]
      */
    def lerp(a: awt.Color, b: awt.Color, amount: Float): awt.Color = {
      var HSB(aH, aS, aB) = a
      var HSB(bH, bS, bB) = b

      val (t, degrees) = if (aH > bH) { // swap aH and bH
      val tempH = bH
        bH = aH
        aH = tempH
        (1 - amount, -(aH - bH))
      } else {
        (amount, aH - bH)
      }

      val hue = if (degrees > 0.5) { // 180 degrees
        aH += 1
        aH lerp (bH, t)
      } else {
        aH + t * degrees
      }

      HSB(hue, aS lerp (bS, t), aB lerp (bH, t))
    }

  }

}
