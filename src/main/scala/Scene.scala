package main.scala

case class RichColor(red: Double, green: Double, blue: Double) {
    def *(c : RichColor) = RichColor(red * c.red, green * c.green, blue * c.blue)
    def *(v : Double) = RichColor(red * v, green * v, blue * v)
    def +(c : RichColor) = RichColor(red + c.red, green + c.green, blue + c.blue)
    def toInt : Int = ((red * 255).toInt << 16) | ((green * 255).toInt << 8) | (blue * 255).toInt
  }

case class Scene(eye: Vector, objects: List[Sphere], lights: List[Light], ambientLight: Light) 

case class Ray(position: Vector, direction: Vector) {
  def *(v: Double) = position + direction * v
}

case class Vector(x: Double, y: Double, z: Double) {

  def op(f:(Double, Double) => Double) = (v: Vector) => Vector(f(x, v.x), f(y, v.y), f(z, v.z))
  def + = op(_ + _)
  def - = op(_ - _)
  def * = op(_ * _)
  def / = op(_ / _)

  def *(v: Double) = Vector(x * v, y * v, z * v)
  def /(v: Double) = Vector(x / v, y / v, z / v)

  def dot(v: Vector) = x * v.x + y * v.y + z * v.z

  def *+(v: Vector) = {
    val t = this * v
    t.x + t.y + t.z
  }

  def lengthSquared = this *+ this
  def length = Math.sqrt(lengthSquared)
  def norm = this / length
}

case class Sphere(position: Vector, radius: Int, color: RichColor, diffuse: RichColor) {

  def intersectRay(ray: Ray): Double = {
    val v = position - ray.position
    val a = v *+ ray.direction
    val b = v.lengthSquared - (radius * radius)
    val c = a * a - b
    if (c >= 0) a - Math.sqrt(c) else Double.PositiveInfinity
  }
}

case class Light(position: Vector, color: RichColor)
