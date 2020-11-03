package com.erya.application

interface Diffable<in T> {
    fun isSame(other: T): Boolean
    fun isContentsSame(other: T): Boolean
}
