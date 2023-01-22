package co.zip.candidate.userapi.services
import co.zip.candidate.userapi.services.UserService
import co.zip.candidate.userapi.domain.User
import co.zip.candidate.userapi.domain.Account
import co.zip.candidate.userapi.repository.UserRepository
import co.zip.candidate.userapi.repository.AccountRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.*
import javax.validation.Valid
import org.springframework.validation.BindingResult
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import java.math.BigDecimal

@Service
class UserService(private val userRepository: UserRepository, private val accountRepository: AccountRepository) {

    fun createUser(user: User): User {
        val email = user.email ?: throw Exception("Email address is required.")
        val existingUser = userRepository.findByEmail(email)
        if (existingUser != null) {
            throw Exception("Email address already in use.")
        }
        val monthlySalary = user.monthlySalary
        val monthlyExpenses = user.monthlyExpenses
        if (monthlySalary != null && monthlyExpenses != null) {
            val credit = monthlySalary - monthlyExpenses
            if (credit < BigDecimal.valueOf(1000)) {
                throw Exception("User does not have enough credit")
            }
        }
        return userRepository.save(user)
    }


    fun listUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
    }

    fun createAccount(userId: Long, accountNumber: String, accountType: String): Account {
        val user = userRepository.findById(userId).orElseThrow { NoSuchElementException("User not found") }
        val account = Account(user = user, accountNumber = accountNumber, accountType = accountType)
        return accountRepository.save(account)
    }


    fun listAccountsByUserId(userId: Long): List<Account> {
        return accountRepository.findAllByUserId(userId)
    }


}
