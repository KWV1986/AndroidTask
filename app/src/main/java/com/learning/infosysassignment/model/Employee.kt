package com.learning.infosysassignment.model

 class Employee {

    var name:String=""
    var add:String=""

    constructor(name: String, add: String) {
        this.name = name
        this.add = add
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}