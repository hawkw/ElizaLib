package me.hawkweisman.elizalib.javaAPIs

import java.awt.Color

import me.hawkweisman.elizalib.scalaAPIs

/**
  * Utilities for working with [[java.awt.Color]].
  *
  * @author Eliza Weisman
  *
  *         Created by Eliza on 6/27/16.
  */
object ColorUtil {
  import scalaAPIs.ColorUtil.{HSB, RGB}
  import scalaAPIs.{LerpFloat, LerpInt}

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
  @inline def lerpRGB(a: Color, b: Color, amount: Float): Color
    = RGB.lerp(a, b, amount)

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
  @inline def lerpHSB(a: Color, b: Color, amount: Float): Color
    = HSB.lerp(a, b, amount)

}
