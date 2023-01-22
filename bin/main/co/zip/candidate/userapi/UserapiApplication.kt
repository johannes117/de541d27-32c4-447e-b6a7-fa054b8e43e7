package co.zip.candidate.userapi
import co.zip.candidate.userapi.services.UserService
import co.zip.candidate.userapi.domain.User
import co.zip.candidate.userapi.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication


@EnableJpaRepositories("co.zip.candidate.userapi.repository")
@EntityScan("co.zip.candidate.userapi.domain")
@SpringBootApplication
class UserapiApplication

fun main(args: Array<String>) {
	runApplication<UserapiApplication>(*args)
}