package chapter1

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.kernel.Eq
import cats.syntax.show._
import cats.syntax.eq._

object CatShowInstances {

  implicit val showCat: Show[Cat] = Show[Cat](cat => {
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show

    s"$name is a $age year old $color cat."
  })

  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      cat1.name === cat2.name &&
      cat1.color === cat2.color &&
      cat1.age === cat2.age
    }
}


object CatShowApp extends App {

  import CatShowInstances._

  val holly = Cat("Holly", 3, "Blonde")
  val ayesha = Cat("Ayesha", 4, "BBrown")
  println(holly.show)

  println(holly === ayesha)
  println(holly =!= ayesha)
}