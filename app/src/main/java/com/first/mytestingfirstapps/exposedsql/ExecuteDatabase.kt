package com.first.mytestingfirstapps.exposedsql

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class ExecuteDatabase {
    ///create database
    var database : Database = Database.connect(
       url = "jdbc:mysql://localhost:3306/mydatabase",
       driver = "com.mysql.jdbc.Driver",
       user = "myuser",
       password = "mypassword"
   )

    fun insert() {
        transaction(database) {
            Users.insert {
                it[name] = "John"
                it[email] = "john@example.com"
            }
        }
    }

    fun  update(emails: String) {
        transaction (database) {
            Users.update({Users.email eq emails}) {
                it[email] = emails
            }
        }
    }
}