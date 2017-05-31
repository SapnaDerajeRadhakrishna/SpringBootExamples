package org.maxwell.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


// Classes in Kotlin are declared using the keyword 'class':
// The open annotation on a class is the opposite of Java's final: it allows others to inherit from this class.
/*  if you declare a companion object inside your class,
you'll be able to call its members with the same syntax as calling static methods in Java/C#, using only the class name as a qualifier.
 */
// @JvmStatic : Kotlin can also generate static methods for functions defined in named objects or companion objects if you annotate those functions as @JvmStatic

@SpringBootApplication
open class Application {
	companion object {
		@JvmStatic fun main(args: Array<String>) {
			SpringApplication.run(Application::class.java, *args)
		}
	}
}