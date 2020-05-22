package com.learning.infosysassignment.model

import android.util.Log

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
     fun getName ()
     {
         
     }

     fun addName(){

         Log.i("I am in add name()","")
     }

     fun saveData(){


     }
 }