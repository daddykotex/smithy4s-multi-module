package smithy4s.hello.refined

import smithy4s._
import smithy4s.hello.EmailFormat

case class Email(value: String)
object Email {

  private def isValidEmail(value: String): Boolean = value.nonEmpty

  def apply(value: String): Either[String, Email] =
    if (isValidEmail(value)) Right(new Email(value))
    else Left("Email is not valid")

  implicit val provider = Refinement.drivenBy[EmailFormat](
    Email.apply, // Tells smithy4s how to create an Email (or get an error message) given a string
    (e: Email) => e.value // Tells smithy4s how to get a string from an Email
  )
}
