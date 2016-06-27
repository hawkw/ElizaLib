package me.hawkweisman.elizalib

import org.lwjgl.util.vector.Vector2f

/** Idiomatic Scala APIs wrapping Starsector classes.
  *
  * @author Eliza Weisman
  *
  * Created by Eliza on 6/27/16.
  */
package object scalaAPIs {


  /** A nicer Scala API for [[org.lwjgl.util.vector.Vector2f]].
    * 
    * @author Eliza Weisman
    * 
    * Created by Eliza on 5/4/16.
    */
  implicit class Vector2fOps(val v: Vector2f) extends AnyVal {

    @inline final def * (u: Vector2f): Float
    = v dot u

    @inline final def dot (u: Vector2f): Float
    = Vector2f dot (v, u)

    @inline final def + (u: Vector2f): Vector2f
    = Vector2f add (v, u, new Vector2f)

    @inline final def += (u: Vector2f): Vector2f
    = { Vector2f add (v, u, v); v }

    @inline final def  - (u: Vector2f): Vector2f
    = Vector2f sub (v, u, new Vector2f)

    @inline final def -= (u: Vector2f): Vector2f
    = { Vector2f sub (v, u, v); v }

    @inline final def ° (u: Vector2f): Float
    = Vector2f angle (v, u)

    @inline final def x: Float = v getX
    @inline final def y: Float = v getY
    @inline final def x_=(x2: Float) = v setX x2
    @inline final def y_=(y2: Float) = v setY y2

  }

  implicit class LerpFloat(val from: Float) extends AnyVal {
    @inline def lerp(to: Float, amount: Float): Float
      = from + (to - from) * amount
  }

  implicit class LerpInt(val from: Int) extends AnyVal {
    @inline def lerp(to: Int, amount: Float): Int
      = Math.round(from + (to - from) * amount)
  }

}
