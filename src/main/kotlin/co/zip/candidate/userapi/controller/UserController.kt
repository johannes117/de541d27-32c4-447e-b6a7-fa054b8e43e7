package co.zip.candidate.userapi.controller
import co.zip.candidate.userapi.services.UserService
import co.zip.candidate.userapi.domain.User
import co.zip.candidate.userapi.domain.Account
import co.zip.candidate.userapi.repository.UserRepository
import co.zip.candidate.userapi.repository.AccountRepository
import javax.validation.Valid
import org.springframework.validation.BindingResult
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.PathVariable


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@Valid @RequestBody user: User, bindingResult: BindingResult): User {
        if(bindingResult.hasErrors()){
        throw Exception("Invalid user data")
        }
        return userService.createUser(user)
    }

    @GetMapping
    fun listUsers(): List<User> {
        return userService.listUsers()
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User {
        return userService.getUser(id)
    }
    
    data class AccountRequest(val userId: Long, val accountNumber: String, val accountType: String)

    @PostMapping("/createAccount")
    fun createAccount(@RequestBody accountRequest: AccountRequest): Account {
        return userService.createAccount(accountRequest.userId, accountRequest.accountNumber, accountRequest.accountType)
    }

    @GetMapping("/{userId}/accounts")
    fun listAccountsByUserId(@PathVariable userId: Long): List<Account> {
        return userService.listAccountsByUserId(userId)
    }

}
