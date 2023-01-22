package co.zip.candidate.userapi.domain
import co.zip.candidate.userapi.services.UserService
import co.zip.candidate.userapi.domain.User
import co.zip.candidate.userapi.domain.Account
import co.zip.candidate.userapi.repository.UserRepository
import co.zip.candidate.userapi.repository.AccountRepository

import java.math.BigDecimal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import lombok.NoArgsConstructor

@Entity
@Table(name = "app_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Email
    @NotBlank
    @Column(unique = true)
    var email: String? = null,

    @NotBlank
    var name: String? = null,

    @Min(value = 0)
    var monthlySalary: BigDecimal? = null,

    @Min(value = 0)
    var monthlyExpenses: BigDecimal? = null,
) {
    constructor() : this(id = 0, email = "", name = "", monthlySalary = BigDecimal.ZERO, monthlyExpenses = BigDecimal.ZERO)
    constructor(name: String, email: String, monthlySalary: BigDecimal, monthlyExpenses: BigDecimal) :
            this(id = 0, name = name, email = email, monthlySalary = monthlySalary, monthlyExpenses = monthlyExpenses)
}

@Entity
@Table(name = "accounts")
@NoArgsConstructor
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val accountNumber: String,

    val accountType: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
) {
    constructor(): this(id = 0, accountNumber = "", accountType = "", user = User())
    constructor(user: User, accountNumber: String, accountType: String) : this(
        id = 0,
        user = user,
        accountNumber = accountNumber,
        accountType = accountType
    )
}
