package com.utsman.cliapp

import com.github.ajalt.clikt.core.CliktCommand

class Author : CliktCommand() {
    override fun run() {
        echo("utsman")
    }
}