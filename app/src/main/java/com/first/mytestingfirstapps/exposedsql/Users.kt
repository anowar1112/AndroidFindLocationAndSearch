package com.first.mytestingfirstapps.exposedsql

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Column

object Users : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val email: Column<String> = varchar("email", 50)
    override val primaryKey = PrimaryKey(id)
}