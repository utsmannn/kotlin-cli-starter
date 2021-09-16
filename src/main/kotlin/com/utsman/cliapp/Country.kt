package com.utsman.cliapp

import com.github.ajalt.clikt.core.CliktCommand

class Country : CliktCommand() {
    override fun run() {
        echo("Indonesia")
    }
}