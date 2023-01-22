package co.zip.candidate.userapi.repository
import co.zip.candidate.userapi.services.UserService
import co.zip.candidate.userapi.domain.User
import co.zip.candidate.userapi.domain.Account
import co.zip.candidate.userapi.repository.UserRepository
import co.zip.candidate.userapi.repository.AccountRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun findAllByUserId(userId: Long): List<Account>
}

