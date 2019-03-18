package ch.tim

object Gitlin {
    fun parseArguments(args: Array<String>) {
        if (args.isEmpty()) {
            help()
            return
        }

        try {
            val command = Arguments.valueOf(args[0].toUpperCase())
            when (command) {
                Gitlin.Arguments.INIT -> init()
                Gitlin.Arguments.ADD -> TODO()
                Gitlin.Arguments.RM -> TODO()
                Gitlin.Arguments.PUSH -> TODO()
                Gitlin.Arguments.HELP -> help()
            }
        } catch (e: IllegalArgumentException) {
            // if the argument could not be parsed, display a help text
            help()
        }
    }

    private fun init() {
        println("Initialized empty Gitlin repository in .gitlin/")
    }

    private fun help() {
        println("${Arguments.HELP.name}: How to use Gitlin:\n")
        val printFormat = "%-10s %-40s\n"
        Arguments.values().forEach {
            System.out.printf(printFormat, it.name.toLowerCase(), it.description)
        }
    }

    enum class Arguments(val description: String) {
        INIT("Creates an empty Gitlin repository"),
        ADD("Adds a file to the index"),
        RM("Removes a file from the working tree and the index"),
        PUSH("Updates remote refs along with associated object"),
        HELP("Displays help for Gitlin")
    }
}

fun main(args: Array<String>) {
    Gitlin.parseArguments(args)
}