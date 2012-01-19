package ray
import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

class Tracer() {

  val BACKGROUND = 0;

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

  def renderObject(width: Int, height: Int, x: Int, y: Int, scene: Scene) = {
    val ray = Ray(Vector(0, 0, 0), Vector(x - width / 2, height / 2 - y, width).norm)
    val hitAngle = scene.objects(0).intersectRay(ray)
    computeColor(
      hitAngle = hitAngle,
      ray = ray,
      sphere = scene.objects(0),
      light = scene.lights(0),
      ambientLight = scene.ambientLight)
  }

  def trace(scene: Scene, width: Int, height: Int) = {
    var screen = new Screen(width, height)
    for {
      x <- screen.s.indices
      y <- screen.s(x).indices
    } {
      val finalColor = renderObject(width, height, x, y, scene)
      screen.s(x)(y) = new Color(finalColor)
    }
    screen
  }
}