# scalaray
scalaray is a very basic raytracer used as a learning exercise. It's limitations:

* No proper lighting.
* No reflection or refraction implemented
* Only objects to be handled are spheres since the maths for sphere -> ray intersection is very simple

## Scenes:
scalaray uses JSON to describe scenes. Scenes are composed of the following:

* ``eye`` - the cameras position
* ``objects`` - a list of objects (spheres)
* ``lights`` - a list of lights
* ``ambientLight`` - the global illumination (a light)
 

Spheres contain:

* a position ``(x, y, z)``, a radius ``(r)``, a color ``(r, g, b)`` and a diffuse component ``(a, b, c)``

Lights contain:

* A light contains a position ``(x, y, z)`` and a color ``(r, g, b)``

Scenes can also be describe as Scene objects in scala, then exported as a scene using the ``generate`` method in ``SceneParser.scala``

This is a **VERY** basic raytracer, but i think it's quite clean, and could be used by someone to learn.


## Example output:
.. image:: raytracer.png

## Resources
* Codemind - C++ - What is ray tracing?: http://www.codermind.com/articles/Raytracer-in-C++-Introduction-What-is-ray-tracing.html
