package chapter1

final case class Cat(name: String, age: Int, color: String)

trait Printable[A] {
  def format(a: A): String
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(a: String): String = a
  }

  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    override def format(cat: Cat): String = s"${cat.name} is a ${cat.age} year old ${cat.color} cat."
  }

  def format[A](a: A)(implicit p: Printable[A]) = {
    p.format(a)
  }
}


object Printable {
  def format[A](a: A)(implicit p: Printable[A]) = {
    p.format(a)
  }

  def print[A](a: A)(implicit p: Printable[A]) = {
    println(format(a))
  }
}

object PrintableSyntax {
  implicit class PrintableOps[A] (a: A) {
    def format(implicit p: Printable[A]): String = {
      p.format(a)
    }

    def print(implicit p: Printable[A]): Unit = {
      println(a.format)
    }
  }
}

object PrintableApp extends App {
  import PrintableInstances.catPrintable

  println(Printable.format[Cat](Cat("Holly", 3, "Blonde")))
}