package org.maxwell.demo

/*
 We frequently create a class to do nothing but hold data.
 In such a class some standard functionality is often mechanically derivable from the data.
 In Kotlin, this is called a data class and is marked as data:
 */

// The properties are declared as 'var' if mutable
// read-only using the 'val' keyword.


// Syntax
// var <propertyName>[: <PropertyType>] [= <property_initializer>]

data class Greeting(val id: Long, val content: String)