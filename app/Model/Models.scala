package Model

import scala.concurrent.{ExecutionContext, Future}

sealed trait Role
case object Administrator extends Role
case object NormalUser extends Role
case object GuestUser extends Role

case class Account(id: String, role: Role, name:String)

object Account{
	def authenticate: (String, String) => Option[Account] = {
		(id, name) => {
			accounts.find(_.id == id)
		}
	}


	val accounts = Seq(Account("a", Administrator, "admin"),
		Account("b", NormalUser, "Bob"),
		Account("c", NormalUser, "Carie")
	)
	def findById(id: String)(implicit ctx: ExecutionContext):Future[Option[Account]] = {
		Future{
			accounts.find(_.id == id)
		}
	}
}