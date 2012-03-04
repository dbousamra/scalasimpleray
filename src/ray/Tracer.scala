package ray
import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import scala.collection.mutable.HashMap

class Tracer() {

  class Screen(x: Int, y: Int) {
    val s = Array.ofDim[java.awt.Color](x, y)
    def apply(x: Int) = s(x)
    def update(x: Int, y: Int, value: java.awt.Color) { s(x)(y) = value }
  }

  def computeColor(hitAngle: Double, ray: Ray, sphere: Sphere, light: Light, ambientLight: Light): Int = {
    if (hitAngle == Double.PositiveInfinity) { //miss
      0
    } else {
      val vectorCoefficient = ray * hitAngle
      val shadedCoefficient = (light.position - sphere.position).norm *+ (vectorCoefficient - sphere.position).norm
      val finalColor = sphere.color * (light.color * Math.max(shadedCoefficient, 0) + ambientLight.color)
      finalColor.toInt
    }
  }

  def getObjectColor(scene: Scene, obj: Sphere, ray: Ray): Int = {
    val hitAngle = obj.intersectRay(ray)
    var shadedColor = computeColor(
      hitAngle = hitAngle,
      ray = ray,
      sphere = obj,
      light = scene.lights(0),
      ambientLight = scene.ambientLight)
    shadedColor
  }

  def getClosestSphere(ray: Ray, spheres: List[Sphere]) = spheres.minBy(_.intersectRay(ray))

  def trace(scene: Scene, width: Int, height: Int) = {
    var screen = new Screen(width, height)
    for {
      x <- screen.s.indices
      y <- screen.s(x).indices
    } {
      val ray = Ray(Vector(x, y, -1000), Vector(0, 0, 1).norm)
      val closestSphere = getClosestSphere(ray, scene.objects)
      val finalColor = getObjectColor(scene, closestSphere, ray)
      screen.s(x)(y) = new Color(finalColor)
    }
    screen
  }
}