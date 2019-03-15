package ch.tim

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

object GitlinSpec : Spek({
    describe("Gitlin") {
        val gitlin = Gitlin

        describe("init") {
            beforeEach {
                // clean up => remove .gitlin folder with its content
                // then create the folder again
                gitlin.parseArguments(arrayOf("init"))
            }

            it("creates a .gitlin folder") {
                assertEquals(2, 1 + 1)
            }
        }

        describe("help") {

            val outContent = ByteArrayOutputStream()
            val errContent = ByteArrayOutputStream()
            val originalOut = System.out
            val originalErr = System.err

            beforeEach {
                System.setOut(PrintStream(outContent))
                System.setErr(PrintStream(errContent))

                // reset the content of the content
                outContent.reset()
                errContent.reset()
            }

            afterEach {
                // restore the original state
                System.setOut(originalOut)
                System.setErr(originalErr)
            }

            it("should print a help") {
                gitlin.parseArguments(arrayOf("help"))
                assertTrue {
                    outContent.toString().startsWith(Gitlin.Arguments.HELP.name) &&
                        errContent.toString().isEmpty()
                }
            }

            it("should print help on non-existing command") {
                gitlin.parseArguments(arrayOf("kajsdlkfjaslkdjföas"))
                assertTrue {
                    outContent.toString().startsWith(Gitlin.Arguments.HELP.name) &&
                        errContent.toString().isEmpty()
                }
            }

            it("should print help on no argument") {
                gitlin.parseArguments(arrayOf())
                assertTrue {
                    outContent.toString().startsWith(Gitlin.Arguments.HELP.name) &&
                        errContent.toString().isEmpty()
                }
            }
        }
    }
})