package com.utsman.cliapp

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class Application : CliktCommand() {
    override fun run() {}
}

fun main(args: Array<String>) {
    return Application()
        .subcommands(Author())
        .subcommands(Country())
        .main(args)
}